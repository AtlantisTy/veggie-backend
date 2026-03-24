package com.veggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.veggie.common.Result;
import com.veggie.entity.SysMenu;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> getUserMenus(Long userId);

    List<SysMenu> buildMenuTree(List<SysMenu> menus);

    Result<List<SysMenu>> getMenuTreeList();

    Result<SysMenu> getMenuById(Long id);

    Result<String> addMenu(SysMenu menu);

    Result<String> updateMenu(SysMenu menu);

    Result<String> deleteMenu(Long id);

    Result<List<SysMenu>> getUserMenuTree(Long userId);
}
