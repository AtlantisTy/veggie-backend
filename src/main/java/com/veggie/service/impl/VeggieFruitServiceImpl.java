package com.veggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.veggie.entity.VeggieFruit;
import com.veggie.mapper.VeggieFruitMapper;
import com.veggie.service.VeggieFruitService;
import org.springframework.stereotype.Service;

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
}
