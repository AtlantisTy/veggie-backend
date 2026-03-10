package com.veggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.veggie.entity.Vegetable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VegetableMapper extends BaseMapper<Vegetable> {

    @Select("SELECT * FROM vegetable WHERE deleted = 0 ORDER BY create_time DESC")
    List<Vegetable> selectAllActive();
}
