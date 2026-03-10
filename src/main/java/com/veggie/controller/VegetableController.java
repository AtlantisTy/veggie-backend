package com.veggie.controller;

import com.veggie.entity.Vegetable;
import com.veggie.service.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vegetable")
public class VegetableController {

    @Autowired
    private VegetableService vegetableService;

    @GetMapping("/list")
    public Map<String, Object> list() {
        List<Vegetable> list = vegetableService.getAllVegetables();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "查询成功");
        result.put("data", list);
        return result;
    }

    @GetMapping("/category/{category}")
    public Map<String, Object> getByCategory(@PathVariable String category) {
        List<Vegetable> list = vegetableService.getVegetablesByCategory(category);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "查询成功");
        result.put("data", list);
        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) {
        Vegetable vegetable = vegetableService.getById(id);
        Map<String, Object> result = new HashMap<>();
        if (vegetable != null) {
            result.put("code", 200);
            result.put("message", "查询成功");
            result.put("data", vegetable);
        } else {
            result.put("code", 404);
            result.put("message", "未找到该蔬菜");
        }
        return result;
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Vegetable vegetable) {
        boolean saved = vegetableService.save(vegetable);
        Map<String, Object> result = new HashMap<>();
        if (saved) {
            result.put("code", 200);
            result.put("message", "添加成功");
        } else {
            result.put("code", 500);
            result.put("message", "添加失败");
        }
        return result;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody Vegetable vegetable) {
        boolean updated = vegetableService.updateById(vegetable);
        Map<String, Object> result = new HashMap<>();
        if (updated) {
            result.put("code", 200);
            result.put("message", "更新成功");
        } else {
            result.put("code", 500);
            result.put("message", "更新失败");
        }
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        boolean removed = vegetableService.removeById(id);
        Map<String, Object> result = new HashMap<>();
        if (removed) {
            result.put("code", 200);
            result.put("message", "删除成功");
        } else {
            result.put("code", 500);
            result.put("message", "删除失败");
        }
        return result;
    }

    @GetMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "服务运行正常");
        result.put("data", "Vegetable Service is running!");
        return result;
    }
}
