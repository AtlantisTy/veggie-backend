package com.veggie.controller.api;

import com.veggie.common.Result;
import com.veggie.entity.CityRecommendation;
import com.veggie.service.CityRecommendationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@Api(tags = "城市推荐接口")
public class ApiCityRecommendationController {

    @Autowired
    private CityRecommendationService cityRecommendationService;

    @GetMapping("/listByCity")
    @ApiOperation("根据城市查询推荐列表")
    public Result<List<CityRecommendation>> getByCity(
            @ApiParam("城市名称，如：上海") @RequestParam String cityName) {
        List<CityRecommendation> list = cityRecommendationService.getByCityName(cityName);
        return Result.success("查询成功", list);
    }
}
