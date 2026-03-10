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

    @GetMapping("/getById")
    @ApiOperation("根据ID查询")
    public Result<VeggieFruit> getById(
            @ApiParam("蔬果ID") @RequestParam Long id) {
        VeggieFruit veggieFruit = veggieFruitService.getById(id);
        if (veggieFruit != null) {
            return Result.success("查询成功", veggieFruit);
        } else {
            return Result.error(404, "未找到该蔬果");
        }
    }

    @PostMapping("/add")
    @ApiOperation("添加蔬果")
    public Result<String> add(
            @ApiParam("蔬果信息") @RequestBody VeggieFruit veggieFruit) {
        boolean saved = veggieFruitService.save(veggieFruit);
        if (saved) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @PostMapping("/update")
    @ApiOperation("更新蔬果")
    public Result<String> update(
            @ApiParam("蔬果信息") @RequestBody VeggieFruit veggieFruit) {
        boolean updated = veggieFruitService.updateById(veggieFruit);
        if (updated) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("/delete")
    @ApiOperation("删除蔬果")
    public Result<String> delete(
            @ApiParam("蔬果ID") @RequestParam Long id) {
        boolean removed = veggieFruitService.removeById(id);
        if (removed) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}
