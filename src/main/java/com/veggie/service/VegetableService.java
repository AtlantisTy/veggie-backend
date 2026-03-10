package com.veggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.veggie.entity.Vegetable;

import java.util.List;

public interface VegetableService extends IService<Vegetable> {

    List<Vegetable> getAllVegetables();

    List<Vegetable> getVegetablesByCategory(String category);
}
