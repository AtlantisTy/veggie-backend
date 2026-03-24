package com.veggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.veggie.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
