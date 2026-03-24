package com.veggie.controller;

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
@RequestMapping("/recommendation")
@Api(tags = "城市推荐接口")
public class CityRecommendationController {

    @Autowired
    private CityRecommendationService cityRecommendationService;

    @GetMapping("/listByCity")
    @ApiOperation("根据城市查询推荐列表")
    public Result<List<CityRecommendation>> getByCity(
            @ApiParam("城市名称，如：上海") @RequestParam String cityName) {
        List<CityRecommendation> list = cityRecommendationService.getByCityName(cityName);
        return Result.success("查询成功", list);
    }

    @GetMapping("/getById")
    @ApiOperation("根据ID查询推荐")
    public Result<CityRecommendation> getById(
            @ApiParam("推荐ID") @RequestParam Long id) {
        return cityRecommendationService.getRecommendationById(id);
    }

    @PostMapping("/add")
    @ApiOperation("添加城市推荐")
    public Result<String> add(
            @ApiParam("推荐信息") @RequestBody CityRecommendation recommendation) {
        return cityRecommendationService.addRecommendation(recommendation);
    }

    @PostMapping("/update")
    @ApiOperation("更新城市推荐")
    public Result<String> update(
            @ApiParam("推荐信息") @RequestBody CityRecommendation recommendation) {
        return cityRecommendationService.updateRecommendation(recommendation);
    }

    @PostMapping("/delete")
    @ApiOperation("删除城市推荐")
    public Result<String> delete(
            @ApiParam("推荐ID") @RequestParam Long id) {
        return cityRecommendationService.deleteRecommendation(id);
    }
}
