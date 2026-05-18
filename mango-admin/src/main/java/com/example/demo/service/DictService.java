package com.example.demo.service;

import com.evan.mangocore.page.PageRequest;
import com.evan.mangocore.page.PageResult;
import com.example.demo.model.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 数典表 服务类
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
public interface DictService extends IService<Dict> {
    int saveDict(Dict record);

    int delete(Dict record);

    int delete(List<Dict> records);

    Dict findById(Long id);

    PageResult findPage(PageRequest pageRequest);

    /**
     * 根据名称查询
     * @ param label
     * @ return
     */
    List<Dict> findByLabel(String label);
}
