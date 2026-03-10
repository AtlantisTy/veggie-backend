package com.veggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.veggie.entity.VeggieFruit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VeggieFruitMapper extends BaseMapper<VeggieFruit> {
    // 使用MyBatis-Plus Lambda查询，无需自定义SQL
}
