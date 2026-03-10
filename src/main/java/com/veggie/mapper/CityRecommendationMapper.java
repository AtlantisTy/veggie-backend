package com.veggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.veggie.entity.CityRecommendation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityRecommendationMapper extends BaseMapper<CityRecommendation> {
    // 使用MyBatis-Plus Lambda查询，无需自定义SQL
}
