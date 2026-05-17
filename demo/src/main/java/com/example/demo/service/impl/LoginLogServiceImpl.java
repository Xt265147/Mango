package com.example.demo.service.impl;

import com.example.demo.model.LoginLog;
import com.example.demo.dao.LoginLogMapper;
import com.example.demo.service.LoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统登录日志表 服务实现类
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}
