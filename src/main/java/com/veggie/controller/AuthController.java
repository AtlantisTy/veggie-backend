package com.veggie.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.veggie.common.Result;
import com.veggie.entity.SysMenu;
import com.veggie.service.SysMenuService;
import com.veggie.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Api(tags = "认证接口")
public class AuthController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<Map<String, Object>> login(
            @ApiParam("用户名") @RequestParam String username,
            @ApiParam("密码") @RequestParam String password) {
        return sysUserService.doLogin(username, password);
    }

    @PostMapping("/logout")
    @ApiOperation("用户登出")
    public Result<String> logout() {
        return sysUserService.doLogout();
    }

    @GetMapping("/info")
    @ApiOperation("获取当前登录用户信息")
    public Result<Map<String, Object>> getInfo() {
        Long userId = StpUtil.getLoginIdAsLong();
        return sysUserService.getLoginUserInfo(userId);
    }

    @GetMapping("/menus")
    @ApiOperation("获取当前登录用户的菜单列表")
    public Result<List<SysMenu>> getMenus() {
        Long userId = StpUtil.getLoginIdAsLong();
        return sysMenuService.getUserMenuTree(userId);
    }
}
