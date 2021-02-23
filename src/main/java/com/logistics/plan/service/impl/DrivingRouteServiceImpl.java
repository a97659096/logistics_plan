package com.logistics.plan.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.logistics.plan.constant.CommonConstants;
import com.logistics.plan.domain.entity.DrivingRoute;
import com.logistics.plan.domain.entity.DrivingRouteDetail;
import com.logistics.plan.domain.entity.Node;
import com.logistics.plan.mapper.DrivingRouteMapper;
import com.logistics.plan.redis.RedisService;
import com.logistics.plan.service.DrivingRouteDetailService;
import com.logistics.plan.service.DrivingRouteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.logistics.plan.service.NodeService;
import com.logistics.plan.utils.CSVUtils;
import com.logistics.plan.utils.ComUtil;
import com.logistics.plan.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tianshihao
 * @since 2021-02-22
 */
@Service
public class DrivingRouteServiceImpl extends ServiceImpl<DrivingRouteMapper, DrivingRoute> implements DrivingRouteService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private DrivingRouteService routeService;

    @Autowired
    private DrivingRouteDetailService routeDetailService;


    /**
     * 获取高的数据并存储
     * @param provinceCode 省份代码
     * @param cityCode 城市代码
     */
    @Override
    public void realTimeGetGaodeData(String provinceCode, String cityCode) {
        try {
            Boolean lock = redisService.tryLock("plan-lock", "lock", 6000);
            System.out.println("加锁成功");
            if(lock) {
                System.out.println("执行任务开始");
                LambdaQueryWrapper<Node> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Node::getProvinceCode, provinceCode);
                queryWrapper.eq(Node::getCityCode, cityCode);
                //只查询启用的
                queryWrapper.eq(Node::getStatus, "1");
                List<Node> nodes = nodeService.list(queryWrapper);
                Node node = nodes.get(0);
                DrivingRoute drivingRoute = new DrivingRoute();
                drivingRoute.setProvinceCode(provinceCode);
                drivingRoute.setCityCode(cityCode);
                drivingRoute.setProvince(node.getProvince());
                drivingRoute.setCity(node.getCity());
                routeService.save(drivingRoute);
                List<DrivingRouteDetail> drivingRouteDetails = new ArrayList<>();
                for (int i = 0; i < nodes.size(); i++) {
                    Node startNode = nodes.get(i);
                    for (int j = 0; j < nodes.size(); j++) {
                        Node endNode = nodes.get(j);
                        String aCode = startNode.getCode();
                        String bCode = endNode.getCode();
                        String aLon = startNode.getLng();
                        String aLat = startNode.getLat();
                        String bLon = endNode.getLng();
                        String bLat = endNode.getLat();

                        System.out.println("开始");
                        long start = System.currentTimeMillis();

                        DrivingRouteDetail routeDetail = new DrivingRouteDetail();
                        routeDetail.setACode(aCode);
                        routeDetail.setBCode(bCode);
                        routeDetail.setRouteId(drivingRoute.getRouteId());
                        //如果起点终点相同时直接返回
                        if (aCode.equals(bCode)) {
                            routeDetail.setDrivingDist("0km");
                            routeDetail.setDrivingTime("0s");
                            routeDetail.setMethod("默认");
                            drivingRouteDetails.add(routeDetail);
                            continue;
                        }
                        String aLonLat = aLon + "," + aLat;
                        String bLonLat = bLon + "," + bLat;
                        //去高德查询距离时间
                        Map<String, Object> param = new HashMap<>();
                        param.put("key", CommonConstants.GAODE_KEY);
                        param.put("origin", aLonLat);
                        param.put("destination", bLonLat);
                        param.put("strategy", "2");
                        String result = "";
                        String url = "http://restapi.amap.com/v3/direction/driving";
                        try {
                            result = HttpClientUtil.httpPostRequest500(url, param);
                            Map<String, Map<String, JSONArray>> resultMap = JSON.parseObject(result, Map.class);
                            JSONObject jsonObject = resultMap.get("route").get("paths").getJSONObject(0);
                            //距离
                            String distanceKm = new BigDecimal(String.valueOf(jsonObject.get("distance")))
                                    .divide(BigDecimal.valueOf(1000))
                                    .setScale(3, RoundingMode.HALF_UP).toString();
                            //耗时
                            String duration = String.valueOf(jsonObject.get("duration"));

                            routeDetail.setDrivingDist(distanceKm + "km");
                            routeDetail.setDrivingTime(duration + "s");
                            routeDetail.setMethod("时间最短");
                            //添加路径
                            JSONArray array = jsonObject.getJSONArray("steps");
                            List<Map> mapList = JSONArray.parseArray(array.toJSONString(), Map.class);
                            StringBuilder wayRouteSb = new StringBuilder();
                            mapList.stream().filter(item -> !ComUtil.isEmpty(item.get("road")) && wayRouteSb.indexOf(String.valueOf(item.get("road"))) == -1)
                                    .forEach(item -> wayRouteSb.append(item.get("road")).append("|"));
                            if (!ComUtil.isEmpty(wayRouteSb) && !ComUtil.isEmpty(wayRouteSb.toString())) {
                                routeDetail.setDrivingRoute(wayRouteSb.toString());
                            }

                            drivingRouteDetails.add(routeDetail);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println(aCode + "->" + bCode + "查询失败,继续进行");
                        }
                        System.out.println("结束，花费时间：" + (System.currentTimeMillis() - start));
                    }
                }

                //批量插入
                System.out.println("插入数据库开始");
                long start1 = System.currentTimeMillis();
                routeDetailService.saveBatch(drivingRouteDetails);
                System.out.println("插入数据库结束，耗时：" + (System.currentTimeMillis() - start1)/1000);
            }
        }finally {
            redisService.unlock("plan-lock");
            System.out.println("解锁成功");
        }
    }
}
