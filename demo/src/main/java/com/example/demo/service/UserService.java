package com.example.demo.service;

import com.example.demo.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户管理 服务类
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
public interface UserService extends IService<User> {
    /**
     * 查找所有用户
     *
     * @retrun
     */
    List<User> findAll();
}
