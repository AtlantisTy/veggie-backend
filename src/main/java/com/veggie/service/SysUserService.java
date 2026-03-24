package com.veggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.veggie.common.Result;
import com.veggie.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService extends IService<SysUser> {

    SysUser login(String username, String password);

    SysUser getUserByUsername(String username);

    List<String> getUserRoles(Long userId);

    List<String> getUserPermissions(Long userId);

    Result<List<SysUser>> getUserList();

    Result<SysUser> getUserById(Long id);

    Result<String> addUser(SysUser user);

    Result<String> updateUser(SysUser user);

    Result<String> deleteUser(Long id);

    Result<Map<String, Object>> doLogin(String username, String password);

    Result<String> doLogout();

    Result<Map<String, Object>> getLoginUserInfo(Long userId);
}
