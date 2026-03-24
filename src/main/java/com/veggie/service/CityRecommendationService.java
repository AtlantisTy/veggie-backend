package com.veggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.veggie.common.Result;
import com.veggie.entity.CityRecommendation;

import java.util.List;

public interface CityRecommendationService extends IService<CityRecommendation> {

    List<CityRecommendation> getByCityName(String cityName);

    Result<CityRecommendation> getRecommendationById(Long id);

    Result<String> addRecommendation(CityRecommendation recommendation);

    Result<String> updateRecommendation(CityRecommendation recommendation);

    Result<String> deleteRecommendation(Long id);
}
