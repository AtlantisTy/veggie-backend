package com.veggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.veggie.common.Result;
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

    Result<VeggieFruit> getVeggieFruitById(Long id);

    Result<String> addVeggieFruit(VeggieFruit veggieFruit);

    Result<String> updateVeggieFruit(VeggieFruit veggieFruit);

    Result<String> deleteVeggieFruit(Long id);
}
