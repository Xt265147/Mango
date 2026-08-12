package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Dept;

import java.util.List;

/**
 * <p>
 * 机构管理 Mapper 接口
 * </p>
 * @author evan
 * @since 2026-05-16
 */
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 分页查询
     */
    List<Dept> findPage();
}
