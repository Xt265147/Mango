package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Role;

import java.util.List;

/**
 * <p>
 * 角色管理 Mapper 接口
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 分页查询
     */
    List<Role> findPage();
}
