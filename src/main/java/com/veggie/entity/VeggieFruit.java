package com.veggie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("veggie_fruit")
@ApiModel("蔬果信息表")
public class VeggieFruit {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("类型：vegetable-蔬菜，fruit-水果")
    private String type;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("是否本地")
    private Boolean isLocal;

    @ApiModelProperty("季节信息")
    private String seasonInfo;

    @ApiModelProperty("烹饪建议")
    private String cookingTip;

    @ApiModelProperty("营养价值")
    private String nutrition;

    @ApiModelProperty("甜度等级")
    private String sweetLevel;

    @ApiModelProperty("图片URL")
    private String image;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除标志")
    private Integer deleted;
}
