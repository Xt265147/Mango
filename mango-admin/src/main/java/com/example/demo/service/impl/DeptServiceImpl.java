package com.example.demo.service.impl;

import com.example.demo.model.Dept;
import com.example.demo.dao.DeptMapper;
import com.example.demo.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 机构管理 服务实现类
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

}
