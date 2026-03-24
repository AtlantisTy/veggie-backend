package com.veggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.veggie.common.Result;
import com.veggie.entity.VeggieFruit;
import com.veggie.mapper.VeggieFruitMapper;
import com.veggie.service.VeggieFruitService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VeggieFruitServiceImpl extends ServiceImpl<VeggieFruitMapper, VeggieFruit> implements VeggieFruitService {

    @Override
    public List<VeggieFruit> getAllVeggieFruits() {
        LambdaQueryWrapper<VeggieFruit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VeggieFruit::getDeleted, 0)
               .orderByDesc(VeggieFruit::getCreateTime);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<VeggieFruit> getByType(String type) {
        LambdaQueryWrapper<VeggieFruit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VeggieFruit::getDeleted, 0)
               .eq(VeggieFruit::getType, type)
               .orderByDesc(VeggieFruit::getCreateTime);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<VeggieFruit> getLocalVeggies() {
        LambdaQueryWrapper<VeggieFruit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VeggieFruit::getDeleted, 0)
               .eq(VeggieFruit::getIsLocal, true)
               .orderByDesc(VeggieFruit::getCreateTime);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<VeggieFruit> getFruits() {
        return getByType("fruit");
    }

    @Override
    public List<VeggieFruit> getVegetables() {
        return getByType("vegetable");
    }

    @Override
    public List<VeggieFruit> getCurrentSeasonVegetables() {
        return getCurrentSeasonByType("vegetable");
    }

    @Override
    public List<VeggieFruit> getCurrentSeasonFruits() {
        return getCurrentSeasonByType("fruit");
    }

    @Override
    public List<VeggieFruit> getCurrentSeasonByType(String type) {
        int currentMonth = LocalDate.now().getMonthValue();
        LambdaQueryWrapper<VeggieFruit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VeggieFruit::getDeleted, 0)
               .eq(VeggieFruit::getType, type)
               .and(w -> w.le(VeggieFruit::getSeasonStart, currentMonth)
                          .ge(VeggieFruit::getSeasonEnd, currentMonth))
               .orderByDesc(VeggieFruit::getCreateTime);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public Result<VeggieFruit> getVeggieFruitById(Long id) {
        VeggieFruit veggieFruit = getById(id);
        if (veggieFruit != null) {
            return Result.success("查询成功", veggieFruit);
        } else {
            return Result.error(404, "未找到该蔬果");
        }
    }

    @Override
    public Result<String> addVeggieFruit(VeggieFruit veggieFruit) {
        boolean saved = save(veggieFruit);
        if (saved) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @Override
    public Result<String> updateVeggieFruit(VeggieFruit veggieFruit) {
        boolean updated = updateById(veggieFruit);
        if (updated) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    @Override
    public Result<String> deleteVeggieFruit(Long id) {
        boolean removed = removeById(id);
        if (removed) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}
