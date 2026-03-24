package com.veggie.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.veggie.common.Result;
import com.veggie.entity.SysRole;
import com.veggie.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys-role")
@Api(tags = "角色管理接口")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/list")
    @ApiOperation("查询所有角色")
    @SaCheckPermission("role:view")
    public Result<List<SysRole>> list() {
        return sysRoleService.getRoleList();
    }

    @GetMapping("/getById")
    @ApiOperation("根据ID查询角色")
    @SaCheckPermission("role:view")
    public Result<SysRole> getById(@ApiParam("角色ID") @RequestParam Long id) {
        return sysRoleService.getRoleById(id);
    }

    @PostMapping("/add")
    @ApiOperation("添加角色")
    @SaCheckPermission("role:add")
    public Result<String> add(@ApiParam("角色信息") @RequestBody SysRole role) {
        return sysRoleService.addRole(role);
    }

    @PostMapping("/update")
    @ApiOperation("更新角色")
    @SaCheckPermission("role:edit")
    public Result<String> update(@ApiParam("角色信息") @RequestBody SysRole role) {
        return sysRoleService.updateRole(role);
    }

    @PostMapping("/delete")
    @ApiOperation("删除角色")
    @SaCheckPermission("role:delete")
    public Result<String> delete(@ApiParam("角色ID") @RequestParam Long id) {
        return sysRoleService.deleteRole(id);
    }

    @PostMapping("/assignMenus")
    @ApiOperation("分配角色菜单")
    @SaCheckPermission("role:edit")
    public Result<String> assignMenus(
            @ApiParam("角色ID") @RequestParam Long roleId,
            @ApiParam("菜单ID列表") @RequestBody List<Long> menuIds) {
        sysRoleService.assignRoleMenus(roleId, menuIds);
        return Result.success("分配成功");
    }
}
