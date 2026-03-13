package com.veggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.veggie.entity.VeggieFruit;

import java.util.List;

public interface VeggieFruitService extends IService<VeggieFruit> {

    List<VeggieFruit> getAllVeggieFruits();

    List<VeggieFruit> getByType(String type);

    List<VeggieFruit> getLocalVeggies();

    List<VeggieFruit> getFruits();

    List<VeggieFruit> getVegetables();

    List<VeggieFruit> getCurrentSeasonVegetables();

    List<VeggieFruit> getCurrentSeasonFruits();

    List<VeggieFruit> getCurrentSeasonByType(String type);
}
