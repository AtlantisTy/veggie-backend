package com.veggie.controller;

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
@RequestMapping("/veggie-fruit")
@Api(tags = "蔬果管理接口")
public class VeggieFruitController {

    @Autowired
    private VeggieFruitService veggieFruitService;

    @GetMapping("/list")
    @ApiOperation("查询所有蔬果")
    public Result<List<VeggieFruit>> list() {
        List<VeggieFruit> list = veggieFruitService.getAllVeggieFruits();
        return Result.success("查询成功", list);
    }

    @GetMapping("/listByType")
    @ApiOperation("根据类型查询（vegetable-蔬菜，fruit-水果）")
    public Result<List<VeggieFruit>> getByType(
            @ApiParam("类型：vegetable或fruit") @RequestParam String type) {
        List<VeggieFruit> list = veggieFruitService.getByType(type);
        return Result.success("查询成功", list);
    }

    @GetMapping("/listLocal")
    @ApiOperation("查询本地蔬果")
    public Result<List<VeggieFruit>> getLocal() {
        List<VeggieFruit> list = veggieFruitService.getLocalVeggies();
        return Result.success("查询成功", list);
    }

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

    @GetMapping("/getById")
    @ApiOperation("根据ID查询")
    public Result<VeggieFruit> getById(
            @ApiParam("蔬果ID") @RequestParam Long id) {
        return veggieFruitService.getVeggieFruitById(id);
    }

    @PostMapping("/add")
    @ApiOperation("添加蔬果")
    public Result<String> add(
            @ApiParam("蔬果信息") @RequestBody VeggieFruit veggieFruit) {
        return veggieFruitService.addVeggieFruit(veggieFruit);
    }

    @PostMapping("/update")
    @ApiOperation("更新蔬果")
    public Result<String> update(
            @ApiParam("蔬果信息") @RequestBody VeggieFruit veggieFruit) {
        return veggieFruitService.updateVeggieFruit(veggieFruit);
    }

    @PostMapping("/delete")
    @ApiOperation("删除蔬果")
    public Result<String> delete(
            @ApiParam("蔬果ID") @RequestParam Long id) {
        return veggieFruitService.deleteVeggieFruit(id);
    }
}
