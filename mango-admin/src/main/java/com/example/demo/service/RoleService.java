package com.example.demo.service;

import com.evan.mangocore.page.PageRequest;
import com.evan.mangocore.page.PageResult;
import com.example.demo.model.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色管理 服务类
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
public interface RoleService extends IService<Role> {

    PageResult findPage(PageRequest pageRequest);
}
