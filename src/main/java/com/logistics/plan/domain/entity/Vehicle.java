package com.logistics.plan.domain.entity;

import java.math.BigDecimal;

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
@TableName("plan_vehicle")
@ApiModel("车辆实体")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private Long vehicleId;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String number;

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
     * 自有/外包
     */
    @ApiModelProperty("自有/外包")
    private String ownership;

    /**
     * 车型
     */
    @ApiModelProperty("车型")
    private String type;

    /**
     * 装载量-载重(千克)
     */
    @ApiModelProperty("装载量-载重(千克)")
    private Integer capacityWeight;

    /**
     * 装载量-车厢容积(立方米) 保留三位小数
     */
    @ApiModelProperty("装载量-车厢容积(立方米) 保留三位小数")
    private BigDecimal capacityVolume;

    /**
     * 装载量-可装载邮件数量
     */
    @ApiModelProperty("装载量-可装载邮件数量")
    private Integer capacityNum;

    /**
     * 车厢长度(毫米)
     */
    @ApiModelProperty("车厢长度(毫米)")
    private Integer length;

    /**
     * 车厢宽度(毫米)
     */
    @ApiModelProperty("车厢宽度(毫米)")
    private Integer width;

    /**
     * 车厢高度(毫米)
     */
    @ApiModelProperty("车厢高度(毫米)")
    private Integer height;

    /**
     * 车辆长度(毫米)
     */
    @ApiModelProperty("车辆长度(毫米)")
    private Integer vLength;

    /**
     * 车辆宽度(毫米)
     */
    @ApiModelProperty("车辆宽度(毫米)")
    private Integer vWidth;

    /**
     * 车辆高度(毫米)
     */
    @ApiModelProperty("车辆高度(毫米)")
    private Integer vHeight;

    /**
     * 油耗(毫升/公里)
     */
    @ApiModelProperty("油耗(毫升/公里)")
    private Integer fuelCost;

    /**
     * 司机绩效
     */
    @ApiModelProperty("司机绩效")
    private Integer meritPay;

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
