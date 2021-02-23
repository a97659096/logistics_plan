package com.logistics.plan.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tianshihao
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("plan_driving_route_detail")
public class DrivingRouteDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long detailId;

    private Long routeId;

    /**
     * 起点城市代码
     */
    private String aCode;

    /**
     * 终点城市代码
     */
    private String bCode;

    /**
     * 距离
     */
    private String drivingDist;

    /**
     * 行驶时长
     */
    private String drivingTime;

    /**
     * 方式
     */
    private String method;

    /**
     * 路径
     */
    private String drivingRoute;


}
