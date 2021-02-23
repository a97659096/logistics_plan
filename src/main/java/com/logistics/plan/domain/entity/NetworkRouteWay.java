package com.logistics.plan.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tianshihao
 * @since 2021-01-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("network_route_way")
public class NetworkRouteWay implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    /**
     * a点名称
     */
    private String aName;

    /**
     * b点名称
     */
    private String bName;

    /**
     * a点code
     */
    private String aCode;

    /**
     * b点code
     */
    private String bCode;

    /**
     * a点经度
     */
    private String aLon;

    /**
     * a点纬度
     */
    private String aLat;

    /**
     * b点经度
     */
    private String bLon;

    /**
     * b点纬度
     */
    private String bLat;


}
