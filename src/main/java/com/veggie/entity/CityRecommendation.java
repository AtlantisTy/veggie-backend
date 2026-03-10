package com.veggie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("city_recommendation")
@ApiModel("城市蔬果推荐表")
public class CityRecommendation {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("城市名称")
    private String cityName;

    @ApiModelProperty("蔬果ID")
    private Long veggieFruitId;

    @ApiModelProperty("推荐原因")
    private String reason;

    @ApiModelProperty("排序")
    private Integer sortOrder;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除标志")
    private Integer deleted;
}
