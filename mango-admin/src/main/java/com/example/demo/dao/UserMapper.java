package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
/**
 * 分页查询
 * @return
 */
List<User> findPage();
}
