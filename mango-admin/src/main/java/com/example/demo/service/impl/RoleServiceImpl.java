package com.example.demo.service.impl;

import com.example.demo.model.Role;
import com.example.demo.dao.RoleMapper;
import com.example.demo.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色管理 服务实现类
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
