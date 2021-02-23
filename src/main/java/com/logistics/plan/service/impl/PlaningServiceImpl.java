//package com.logistics.plan.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.logistics.plan.redis.RedisService;
//import com.logistics.plan.service.PlaningService;
//import com.logistics.plan.utils.CSVUtils;
//import com.logistics.plan.utils.ComUtil;
//import com.logistics.plan.utils.HttpClientUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class PlaningServiceImpl implements PlaningService {
//
//    @Autowired
//    private RedisService redisService;
//
//    private List<String> distDataList;
//
//    private List<String> dataList;
//
//    @Override
//    @Async
//    public void routeAndDistCreate(String path) throws Exception {
//        try {
//            Boolean lock = redisService.tryLock("file-lock", "lock", 6000);
//            System.out.println("加锁成功");
//            if(lock) {
//                System.out.println("执行任务开始");
////                Thread.sleep(60000);
//                File file = new File(path+"node_进1.csv");
//                dataList = CSVUtils.importCsv(file);
//                String[] titles = dataList.get(0).split(",");
//                Map<String, Integer> map = new HashMap<>();
//                for (int i = 0; i < titles.length; i++) {
//                    map.put(titles[i], i);
//                }
//                //way表生成
//                List<String> wayDataList = new ArrayList<>();
//                wayDataList.add("a_address,b_address,a_code,b_code,a_lng,a_lat,b_lng,b_lat,driving_dist,driving_time,method,driving_route");
//                //dist表数据 同时生成两张csv表
////                List<String> distDataList = new ArrayList<>();
//                distDataList = new ArrayList<>();
//                distDataList.add("code1,code2,driving_dist,driving_time");
//                for (int i = 1; i < dataList.size(); i++) {
//                    String[] oneSplit = dataList.get(i).split(",");
//                    for (int j = 1; j < dataList.size(); j++) {
//                        String[] twoSplit = dataList.get(j).split(",");
//                        String aName = oneSplit[map.get("address")];
//                        String bName = twoSplit[map.get("address")];
//                        String aCode = oneSplit[map.get("code")];
//                        String bCode = twoSplit[map.get("code")];
//                        String aLon = oneSplit[map.get("lng")];
//                        String aLat = oneSplit[map.get("lat")];
//                        String bLon = twoSplit[map.get("lng")];
//                        String bLat = twoSplit[map.get("lat")];
//
//                        System.out.println("开始");
//                        long start = System.currentTimeMillis();
//                        //distDataListSb
//                        StringBuilder distDataListSb = new StringBuilder();
//                        String aLonLat = aLon + "," + aLat;
//                        String bLonLat = bLon + "," + bLat;
//                        StringBuilder routeSb = new StringBuilder();
//                        routeSb.append(aName).append(",").append(bName).append(",")
//                                .append(aCode).append(",").append(bCode).append(",")
//                                .append(aLon).append(",").append(aLat).append(",")
//                                .append(bLon).append(",").append(bLat);
//                        distDataListSb.append(aCode).append(",").append(bCode);
//                        //如果起点终点相同时直接返回
//                        if (aCode.equals(bCode)) {
//                            routeSb.append(",").append("0km,0s,默认");
//                            wayDataList.add(routeSb.toString());
//                            distDataListSb.append(",").append("0,0");
//                            distDataList.add(distDataListSb.toString());
//                            continue;
//                        }
//                        //去高德查询距离时间
//                        Map<String, Object> param = new HashMap<>();
//                        param.put("key", "87080558e69d3b2e7aaaf2e912ffa551");
//                        param.put("origin", aLonLat);
//                        param.put("destination", bLonLat);
//                        param.put("strategy", "2");
//                        String result = "";
//                        String url = "http://restapi.amap.com/v3/direction/driving";
//                        try {
//                            result = HttpClientUtil.httpPostRequest500(url, param);
//                            Map<String, Map<String, JSONArray>> resultMap = JSON.parseObject(result, Map.class);
//                            JSONObject jsonObject = resultMap.get("route").get("paths").getJSONObject(0);
//                            //距离
//                            String distanceKm = new BigDecimal(String.valueOf(jsonObject.get("distance")))
//                                    .divide(BigDecimal.valueOf(1000))
//                                    .setScale(3, RoundingMode.HALF_UP).toString();
//                            //耗时
//                            String duration = String.valueOf(jsonObject.get("duration"));
//
//                            //向dist文件中添加数据
//                            distDataListSb.append(",").append(distanceKm).append(",").append(duration);
//                            distDataList.add(distDataListSb.toString());
//
//                            //向way中添加数据
//                            routeSb.append(",").append(distanceKm + "km").append(",")
//                                    .append(duration + "s").append(",")
//                                    .append("时间最短");
//                            //添加路径
//                            JSONArray array = jsonObject.getJSONArray("steps");
//                            List<Map> mapList = JSONArray.parseArray(array.toJSONString(), Map.class);
//                            StringBuilder wayRouteSb = new StringBuilder();
//                            mapList.stream().filter(item -> !ComUtil.isEmpty(item.get("road")) && wayRouteSb.indexOf(String.valueOf(item.get("road"))) == -1)
//                                    .forEach(item -> wayRouteSb.append(item.get("road")).append("|"));
//                            if (!ComUtil.isEmpty(wayRouteSb) && !ComUtil.isEmpty(wayRouteSb.toString())) {
//                                routeSb.append(",").append(wayRouteSb.toString());
//                            }
//                            wayDataList.add(routeSb.toString());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            System.out.println(aName + "->" + bName + "查询失败,继续进行");
//                        }
//                        System.out.println("结束，花费时间：" + (System.currentTimeMillis() - start));
//                    }
//                }
//                //写入way文件
//                File newFile = new File(path + "driving_route.csv");
//                CSVUtils.exportCsvByPlanning(newFile, wayDataList);
////                FileUploadUtils.creatTxtFile(path + "/route##.txt");
////                FileUploadUtils.writeTxtFile(path + "/route##.txt", "way.csv");
//                //写入dist文件
//                File newDistFile = new File(path + "dist.csv");
//                CSVUtils.exportCsvByPlanning(newDistFile, distDataList);
////                FileUploadUtils.creatTxtFile(path + "/dist##.txt");
////                FileUploadUtils.writeTxtFile(path + "/dist##.txt", "dist.csv");
//            }
//        }finally {
//            redisService.unlock("file-lock");
//            System.out.println("解锁成功");
//        }
//
//    }
//
//    @Override
//    public BigDecimal getSchedule() {
//        if(ComUtil.isEmpty(dataList)){
//            return BigDecimal.valueOf(0);
//        }
//        //总数
//        Integer total = dataList.size() * dataList.size();
//        //完成数
//        Integer completeCount = distDataList.size();
//        BigDecimal schedule = BigDecimal.valueOf(completeCount).divide(BigDecimal.valueOf(total),2, RoundingMode.HALF_UP);
//        return schedule.multiply(BigDecimal.valueOf(100));
//    }
//
//}
