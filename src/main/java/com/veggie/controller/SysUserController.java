package com.veggie.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.veggie.common.Result;
import com.veggie.entity.SysUser;
import com.veggie.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys-user")
@Api(tags = "用户管理接口")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/list")
    @ApiOperation("查询所有用户")
    @SaCheckPermission("user:view")
    public Result<List<SysUser>> list() {
        return sysUserService.getUserList();
    }

    @GetMapping("/getById")
    @ApiOperation("根据ID查询用户")
    @SaCheckPermission("user:view")
    public Result<SysUser> getById(@ApiParam("用户ID") @RequestParam Long id) {
        return sysUserService.getUserById(id);
    }

    @PostMapping("/add")
    @ApiOperation("添加用户")
    @SaCheckPermission("user:add")
    public Result<String> add(@ApiParam("用户信息") @RequestBody SysUser user) {
        return sysUserService.addUser(user);
    }

    @PostMapping("/update")
    @ApiOperation("更新用户")
    @SaCheckPermission("user:edit")
    public Result<String> update(@ApiParam("用户信息") @RequestBody SysUser user) {
        return sysUserService.updateUser(user);
    }

    @PostMapping("/delete")
    @ApiOperation("删除用户")
    @SaCheckPermission("user:delete")
    public Result<String> delete(@ApiParam("用户ID") @RequestParam Long id) {
        return sysUserService.deleteUser(id);
    }
}
