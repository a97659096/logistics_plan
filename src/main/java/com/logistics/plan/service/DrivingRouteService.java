package com.logistics.plan.service;

import com.logistics.plan.domain.entity.DrivingRoute;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tianshihao
 * @since 2021-02-22
 */
public interface DrivingRouteService extends IService<DrivingRoute> {

    /**
     * 获取高的数据并存储
     * @param provinceCode 省份代码
     * @param cityCode 城市代码
     */
    void realTimeGetGaodeData(String provinceCode, String cityCode);

}
