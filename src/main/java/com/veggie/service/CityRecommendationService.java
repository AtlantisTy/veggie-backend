package com.veggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.veggie.entity.CityRecommendation;

import java.util.List;

public interface CityRecommendationService extends IService<CityRecommendation> {

    List<CityRecommendation> getByCityName(String cityName);
}
