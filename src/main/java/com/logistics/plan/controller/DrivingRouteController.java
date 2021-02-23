package com.logistics.plan.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.logistics.plan.domain.entity.DrivingRoute;
import com.logistics.plan.service.DrivingRouteService;
import com.logistics.plan.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/driving-route")
public class DrivingRouteController {

    @Autowired
    private DrivingRouteService drivingRouteService;

    /**
     * 根据省份代码和城市代码获取可选值列表
     * @param provinceCode 省份代码
     * @param cityCode 城市代码
     * @return
     */
    @GetMapping("selectBox")
    public R getDrivingRoute(@RequestParam String provinceCode,
                             @RequestParam String cityCode){
        LambdaQueryWrapper<DrivingRoute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DrivingRoute::getProvinceCode, provinceCode);
        queryWrapper.eq(DrivingRoute::getCityCode, cityCode);
        List<DrivingRoute> list = drivingRouteService.list(queryWrapper);
        List<Map> mapList = new ArrayList<>();
        for(DrivingRoute drivingRoute:list){
            Map<String, Object> map = new HashMap<>();
            map.put("key", drivingRoute.getRouteId());
            map.put("value", drivingRoute.getProvince() + drivingRoute.getCity() + "&" + drivingRoute.getGmtCreate());
            mapList.add(map);
        }
        return R.ok(mapList);
    }

    /**
     * 刷取高的数据并写入数据库
     * @param params
     * @return
     */
    @PostMapping("gaodeData")
    public R realTimeGetGaodeData(@RequestBody Map<String, String> params){
        drivingRouteService.realTimeGetGaodeData(params.get("pCode"), params.get("cCode"));
        return R.ok();
    }

}
