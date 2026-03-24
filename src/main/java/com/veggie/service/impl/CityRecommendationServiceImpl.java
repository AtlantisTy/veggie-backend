package com.veggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.veggie.common.Result;
import com.veggie.entity.CityRecommendation;
import com.veggie.mapper.CityRecommendationMapper;
import com.veggie.service.CityRecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityRecommendationServiceImpl extends ServiceImpl<CityRecommendationMapper, CityRecommendation> implements CityRecommendationService {

    @Override
    public List<CityRecommendation> getByCityName(String cityName) {
        LambdaQueryWrapper<CityRecommendation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CityRecommendation::getDeleted, 0)
               .eq(CityRecommendation::getCityName, cityName)
               .orderByAsc(CityRecommendation::getSortOrder)
               .orderByDesc(CityRecommendation::getCreateTime);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public Result<CityRecommendation> getRecommendationById(Long id) {
        CityRecommendation recommendation = getById(id);
        if (recommendation != null) {
            return Result.success("查询成功", recommendation);
        } else {
            return Result.error(404, "未找到该推荐");
        }
    }

    @Override
    public Result<String> addRecommendation(CityRecommendation recommendation) {
        boolean saved = save(recommendation);
        if (saved) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @Override
    public Result<String> updateRecommendation(CityRecommendation recommendation) {
        boolean updated = updateById(recommendation);
        if (updated) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    @Override
    public Result<String> deleteRecommendation(Long id) {
        boolean removed = removeById(id);
        if (removed) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}
