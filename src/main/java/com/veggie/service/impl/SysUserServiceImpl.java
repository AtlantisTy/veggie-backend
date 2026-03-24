package com.veggie.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.veggie.common.Result;
import com.veggie.common.exception.BusinessException;
import com.veggie.entity.SysRole;
import com.veggie.entity.SysUser;
import com.veggie.mapper.SysRoleMapper;
import com.veggie.mapper.SysUserMapper;
import com.veggie.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysUser login(String username, String password) {
        SysUser user = getUserByUsername(username);
        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }
        if (user.getStatus() == 0) {
            throw new BusinessException("账号已被禁用");
        }
        String encryptPassword = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if (!encryptPassword.equals(user.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }
        StpUtil.login(user.getId());
        return user;
    }

    @Override
    public SysUser getUserByUsername(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username)
               .eq(SysUser::getDeleted, 0);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<String> getUserRoles(Long userId) {
        List<SysRole> roles = sysRoleMapper.selectRolesByUserId(userId);
        return roles.stream().map(SysRole::getRoleCode).collect(Collectors.toList());
    }

    @Override
    public List<String> getUserPermissions(Long userId) {
        return StpUtil.getPermissionList();
    }

    @Override
    public Result<List<SysUser>> getUserList() {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getDeleted, 0);
        List<SysUser> list = list(wrapper);
        list.forEach(user -> user.setPassword(null));
        return Result.success("查询成功", list);
    }

    @Override
    public Result<SysUser> getUserById(Long id) {
        SysUser user = getById(id);
        if (user != null) {
            user.setPassword(null);
            return Result.success("查询成功", user);
        } else {
            return Result.error(404, "用户不存在");
        }
    }

    @Override
    public Result<String> addUser(SysUser user) {
        String encryptPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(encryptPassword);
        boolean saved = save(user);
        if (saved) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @Override
    public Result<String> updateUser(SysUser user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String encryptPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
            user.setPassword(encryptPassword);
        } else {
            user.setPassword(null);
        }
        boolean updated = updateById(user);
        if (updated) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    @Override
    public Result<String> deleteUser(Long id) {
        boolean removed = removeById(id);
        if (removed) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    @Override
    public Result<Map<String, Object>> doLogin(String username, String password) {
        SysUser user = login(username, password);
        String token = StpUtil.getTokenValue();
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("userId", user.getId());
        result.put("username", user.getUsername());
        result.put("nickname", user.getNickname());
        return Result.success("登录成功", result);
    }

    @Override
    public Result<String> doLogout() {
        StpUtil.logout();
        return Result.success("登出成功");
    }

    @Override
    public Result<Map<String, Object>> getLoginUserInfo(Long userId) {
        SysUser user = getById(userId);
        List<String> roles = getUserRoles(userId);
        List<String> permissions = getUserPermissions(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("userId", user.getId());
        result.put("username", user.getUsername());
        result.put("nickname", user.getNickname());
        result.put("avatar", user.getAvatar());
        result.put("roles", roles);
        result.put("permissions", permissions);
        return Result.success("查询成功", result);
    }
}
