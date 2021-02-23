package com.logistics.plan.domain.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
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
@TableName("plan_mail")
@ApiModel("邮件实体")
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private Long mailId;

    /**
     * 地区代码
     */
    @ApiModelProperty("地区代码")
    private String code;

    /**
     * 地区名称
     */
    @ApiModelProperty("地区名称")
    private String name;

    /**
     * 日期
     */
    @ApiModelProperty("日期")
    private LocalDate date;

    /**
     * 频次
     */
    @ApiModelProperty("频次")
    private Integer freq;

    /**
     * 进口/出口 (1->进口, 2->出口)
     */
    @ApiModelProperty("进口/出口 (1->进口, 2->出口)")
    private String inOut;

    /**
     * 邮件量
     */
    @ApiModelProperty("邮件量")
    private Integer num;

    /**
     * 装载邮件种类(1-标快|快包,2-标快,3-快包)
     */
    @ApiModelProperty("装载邮件种类(1-标快|快包,2-标快,3-快包)")
    private String mailType;

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
     * 创建时间
     */
    @JSONField(serialize = false)
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @JSONField(serialize = false)
    private LocalDateTime gmtModified;


}
