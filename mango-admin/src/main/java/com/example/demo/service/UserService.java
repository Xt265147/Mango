package com.example.demo.service;

import com.evan.mangocore.page.PageRequest;
import com.evan.mangocore.page.PageResult;
import com.example.demo.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.File;
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
    PageResult findPage(PageRequest pageRequest);

    /**
     * 查找所有用户
     * @ return
     */
    List<User> findAll();

    File createUserExcelFile(PageRequest pageRequest);
}
