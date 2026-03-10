package com.veggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.veggie.entity.Vegetable;
import com.veggie.mapper.VegetableMapper;
import com.veggie.service.VegetableService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VegetableServiceImpl extends ServiceImpl<VegetableMapper, Vegetable> implements VegetableService {

    @Override
    public List<Vegetable> getAllVegetables() {
        return baseMapper.selectAllActive();
    }

    @Override
    public List<Vegetable> getVegetablesByCategory(String category) {
        QueryWrapper<Vegetable> wrapper = new QueryWrapper<>();
        wrapper.eq("category", category)
               .eq("deleted", 0)
               .orderByDesc("create_time");
        return baseMapper.selectList(wrapper);
    }
}
