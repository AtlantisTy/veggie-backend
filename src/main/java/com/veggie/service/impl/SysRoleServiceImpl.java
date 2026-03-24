package com.veggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.veggie.common.Result;
import com.veggie.entity.RoleMenu;
import com.veggie.entity.SysRole;
import com.veggie.mapper.RoleMenuMapper;
import com.veggie.mapper.SysRoleMapper;
import com.veggie.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<SysRole> getUserRoles(Long userId) {
        return baseMapper.selectRolesByUserId(userId);
    }

    @Override
    @Transactional
    public void assignRoleMenus(Long roleId, List<Long> menuIds) {
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getRoleId, roleId);
        roleMenuMapper.delete(wrapper);
        if (menuIds != null && !menuIds.isEmpty()) {
            List<RoleMenu> list = new ArrayList<>();
            for (Long menuId : menuIds) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                list.add(roleMenu);
            }
            for (RoleMenu rm : list) {
                roleMenuMapper.insert(rm);
            }
        }
    }

    @Override
    public Result<List<SysRole>> getRoleList() {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getDeleted, 0);
        List<SysRole> list = list(wrapper);
        return Result.success("查询成功", list);
    }

    @Override
    public Result<SysRole> getRoleById(Long id) {
        SysRole role = getById(id);
        if (role != null) {
            return Result.success("查询成功", role);
        } else {
            return Result.error(404, "角色不存在");
        }
    }

    @Override
    public Result<String> addRole(SysRole role) {
        boolean saved = save(role);
        if (saved) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @Override
    public Result<String> updateRole(SysRole role) {
        boolean updated = updateById(role);
        if (updated) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    @Override
    public Result<String> deleteRole(Long id) {
        boolean removed = removeById(id);
        if (removed) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}
