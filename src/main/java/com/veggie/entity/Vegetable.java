package com.veggie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("vegetable")
public class Vegetable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String category;

    private BigDecimal price;

    private Integer stock;

    private String description;

    private String origin;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer deleted;
}
