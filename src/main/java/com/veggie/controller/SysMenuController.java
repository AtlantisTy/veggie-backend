package com.veggie.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.veggie.common.Result;
import com.veggie.entity.SysMenu;
import com.veggie.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys-menu")
@Api(tags = "菜单管理接口")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/list")
    @ApiOperation("查询所有菜单")
    @SaCheckPermission("menu:view")
    public Result<List<SysMenu>> list() {
        return sysMenuService.getMenuTreeList();
    }

    @GetMapping("/getById")
    @ApiOperation("根据ID查询菜单")
    @SaCheckPermission("menu:view")
    public Result<SysMenu> getById(@ApiParam("菜单ID") @RequestParam Long id) {
        return sysMenuService.getMenuById(id);
    }

    @PostMapping("/add")
    @ApiOperation("添加菜单")
    @SaCheckPermission("menu:add")
    public Result<String> add(@ApiParam("菜单信息") @RequestBody SysMenu menu) {
        return sysMenuService.addMenu(menu);
    }

    @PostMapping("/update")
    @ApiOperation("更新菜单")
    @SaCheckPermission("menu:edit")
    public Result<String> update(@ApiParam("菜单信息") @RequestBody SysMenu menu) {
        return sysMenuService.updateMenu(menu);
    }

    @PostMapping("/delete")
    @ApiOperation("删除菜单")
    @SaCheckPermission("menu:delete")
    public Result<String> delete(@ApiParam("菜单ID") @RequestParam Long id) {
        return sysMenuService.deleteMenu(id);
    }
}
