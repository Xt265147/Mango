package com.example.demo.service.impl;

import com.example.demo.model.Config;
import com.example.demo.dao.ConfigMapper;
import com.example.demo.service.ConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

}
