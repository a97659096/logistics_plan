package com.logistics.plan.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("plan_node")
@ApiModel("节点实体")
public class Node implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private Long nodeId;

    /**
     * 地区编码
     */
    @ApiModelProperty("地区编码")
    private String code;

    /**
     * 地区名称
     */
    @ApiModelProperty("地区名称")
    private String name;

    /**
     * 省份代码
     */
    @ApiModelProperty("省份代码")
    private String provinceCode;

    /**
     * 省份名称
     */
    @ApiModelProperty("省份名称")
    private String province;

    /**
     * 城市代码
     */
    @ApiModelProperty("城市代码")
    private String cityCode;

    /**
     * 城市名称
     */
    @ApiModelProperty("城市名称")
    private String city;

    /**
     * 地区地址
     */
    @ApiModelProperty("地区地址")
    private String address;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private String lng;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private String lat;

    /**
     * 开始访问时间
     */
    @ApiModelProperty("开始访问时间")
    private String tw1;

    /**
     * 截止访问时间
     */
    @ApiModelProperty("截止访问时间")
    private String tw2;

    /**
     * 允许停靠最大车辆长度(毫米)
     */
    @ApiModelProperty("允许停靠最大车辆长度(毫米)")
    private Integer maxVLength;

    /**
     * 可用卸车位数量
     */
    @ApiModelProperty("可用卸车位数量")
    private Integer numServersUnload;

    /**
     * 是否启用（1启用，0停用）
     */
    @ApiModelProperty("是否启用（1启用，0停用）")
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;


}
