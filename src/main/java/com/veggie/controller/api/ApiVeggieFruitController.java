package com.veggie.controller.api;

import com.veggie.common.Result;
import com.veggie.entity.VeggieFruit;
import com.veggie.service.VeggieFruitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veggie-fruit")
@Api(tags = "蔬果管理接口")
public class ApiVeggieFruitController {

    @Autowired
    private VeggieFruitService veggieFruitService;

    @GetMapping("/listCurrentSeasonVegetables")
    @ApiOperation("查询当前应季蔬菜")
    public Result<List<VeggieFruit>> getCurrentSeasonVegetables() {
        List<VeggieFruit> list = veggieFruitService.getCurrentSeasonVegetables();
        return Result.success("查询成功", list);
    }

    @GetMapping("/listCurrentSeasonFruits")
    @ApiOperation("查询当前应季水果")
    public Result<List<VeggieFruit>> getCurrentSeasonFruits() {
        List<VeggieFruit> list = veggieFruitService.getCurrentSeasonFruits();
        return Result.success("查询成功", list);
    }
}
