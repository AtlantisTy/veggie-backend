package com.veggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.veggie.common.Result;
import com.veggie.entity.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    List<SysRole> getUserRoles(Long userId);

    void assignRoleMenus(Long roleId, List<Long> menuIds);

    Result<List<SysRole>> getRoleList();

    Result<SysRole> getRoleById(Long id);

    Result<String> addRole(SysRole role);

    Result<String> updateRole(SysRole role);

    Result<String> deleteRole(Long id);
}
