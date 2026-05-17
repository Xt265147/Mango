package com.example.demo.service.impl;

import com.example.demo.model.Log;
import com.example.demo.dao.LogMapper;
import com.example.demo.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
