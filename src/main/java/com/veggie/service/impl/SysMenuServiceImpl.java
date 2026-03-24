package com.veggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.veggie.common.Result;
import com.veggie.entity.SysMenu;
import com.veggie.mapper.SysMenuMapper;
import com.veggie.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> getUserMenus(Long userId) {
        return baseMapper.selectMenusByUserId(userId);
    }

    @Override
    public List<SysMenu> buildMenuTree(List<SysMenu> menus) {
        Map<Long, SysMenu> menuMap = menus.stream()
                .collect(Collectors.toMap(SysMenu::getId, menu -> menu));
        List<SysMenu> tree = new ArrayList<>();
        for (SysMenu menu : menus) {
            if (menu.getParentId() == 0) {
                tree.add(menu);
            } else {
                SysMenu parent = menuMap.get(menu.getParentId());
                if (parent != null) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(menu);
                }
            }
        }
        return tree;
    }

    @Override
    public Result<List<SysMenu>> getMenuTreeList() {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getDeleted, 0)
               .orderByAsc(SysMenu::getSortOrder);
        List<SysMenu> list = list(wrapper);
        List<SysMenu> menuTree = buildMenuTree(list);
        return Result.success("查询成功", menuTree);
    }

    @Override
    public Result<SysMenu> getMenuById(Long id) {
        SysMenu menu = getById(id);
        if (menu != null) {
            return Result.success("查询成功", menu);
        } else {
            return Result.error(404, "菜单不存在");
        }
    }

    @Override
    public Result<String> addMenu(SysMenu menu) {
        boolean saved = save(menu);
        if (saved) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @Override
    public Result<String> updateMenu(SysMenu menu) {
        boolean updated = updateById(menu);
        if (updated) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    @Override
    public Result<String> deleteMenu(Long id) {
        boolean removed = removeById(id);
        if (removed) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    @Override
    public Result<List<SysMenu>> getUserMenuTree(Long userId) {
        List<SysMenu> menus = getUserMenus(userId);
        List<SysMenu> menuTree = buildMenuTree(menus);
        return Result.success("查询成功", menuTree);
    }
}
