package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Dept;
import com.example.demo.model.Dict;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * 数典表 Mapper 接口
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 分页查询
     * @ param label
     */
    List<Dict> findPage();

    /**
     * 根据标签名称查询
     * @ param label
     */
    List<Dict> findByLabel(@Param(value = "label") String label);


    /**
     * 根据标签名称分页查询
     * @ param label
     */
    List<Dict> findByLabelPage(@Param(value = "label") String label);

}
