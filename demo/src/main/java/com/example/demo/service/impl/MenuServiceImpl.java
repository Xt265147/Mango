package com.example.demo.service.impl;

import com.example.demo.model.Menu;
import com.example.demo.dao.MenuMapper;
import com.example.demo.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
