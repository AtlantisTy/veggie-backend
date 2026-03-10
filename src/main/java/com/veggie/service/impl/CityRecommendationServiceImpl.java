package com.veggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.veggie.entity.CityRecommendation;
import com.veggie.mapper.CityRecommendationMapper;
import com.veggie.service.CityRecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityRecommendationServiceImpl extends ServiceImpl<CityRecommendationMapper, CityRecommendation> implements CityRecommendationService {

    @Override
    public List<CityRecommendation> getByCityName(String cityName) {
        LambdaQueryWrapper<CityRecommendation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CityRecommendation::getDeleted, 0)
               .eq(CityRecommendation::getCityName, cityName)
               .orderByAsc(CityRecommendation::getSortOrder)
               .orderByDesc(CityRecommendation::getCreateTime);
        return baseMapper.selectList(wrapper);
    }
}
