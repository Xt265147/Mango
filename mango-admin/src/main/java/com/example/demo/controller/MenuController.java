package com.example.demo.controller;

import com.evan.mangocore.http.HttpResult;
import com.evan.mangocore.page.PageRequest;
import com.example.demo.model.Menu;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PreAuthorize("hasAuthority('sys:menu:add') or hasAuthority('sys:menu:edit')")
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody Menu record) {
        return HttpResult.ok(menuService.saveOrUpdate(record));
    }

    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody Map<String, String> params) {
        String ids = params.get("ids");
        return HttpResult.ok(menuService.removeByIds(Arrays.stream(ids.split(","))
                .map(Long::valueOf).collect(Collectors.toList())));
    }

    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(menuService.findPage(pageRequest));
    }

    @GetMapping(value = "/findAll")
    public List<Menu> findAll() {
        return menuService.list();
    }

    @GetMapping(value = "/findNavTree")
    public HttpResult findNavTree(@RequestParam String userName) {
        List<Menu> allMenus = menuService.list();
        if (allMenus == null || allMenus.isEmpty()) {
            allMenus = getDefaultMenus();
        }
        return HttpResult.ok(buildTree(allMenus, 0L));
    }

    /**
     * 将平铺菜单列表构建为树形结构
     */
    private List<Map<String, Object>> buildTree(List<Menu> menus, Long parentId) {
        List<Map<String, Object>> tree = new ArrayList<>();
        for (Menu menu : menus) {
            if (parentId.equals(menu.getParentId())) {
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("id", menu.getId());
                node.put("name", menu.getName());
                node.put("icon", menu.getIcon());
                node.put("url", menu.getUrl());
                node.put("type", menu.getType());
                node.put("parentId", menu.getParentId());
                List<Map<String, Object>> children = buildTree(menus, menu.getId());
                if (!children.isEmpty()) {
                    node.put("children", children);
                }
                tree.add(node);
            }
        }
        return tree;
    }

    /**
     * 数据库无菜单数据时返回默认菜单结构
     */
    private List<Menu> getDefaultMenus() {
        List<Menu> menus = new ArrayList<>();

        Menu sysDir = new Menu();
        sysDir.setId(1L);
        sysDir.setName("系统管理");
        sysDir.setIcon("fa fa-cog");
        sysDir.setParentId(0L);
        sysDir.setType(0);
        sysDir.setOrderNum(1);
        menus.add(sysDir);

        Menu userMenu = new Menu();
        userMenu.setId(11L);
        userMenu.setName("用户管理");
        userMenu.setIcon("fa fa-user");
        userMenu.setParentId(1L);
        userMenu.setType(1);
        userMenu.setUrl("system/user");
        userMenu.setOrderNum(1);
        menus.add(userMenu);

        Menu deptMenu = new Menu();
        deptMenu.setId(12L);
        deptMenu.setName("机构管理");
        deptMenu.setIcon("fa fa-sitemap");
        deptMenu.setParentId(1L);
        deptMenu.setType(1);
        deptMenu.setUrl("system/dept");
        deptMenu.setOrderNum(2);
        menus.add(deptMenu);

        Menu roleMenu = new Menu();
        roleMenu.setId(13L);
        roleMenu.setName("角色管理");
        roleMenu.setIcon("fa fa-users");
        roleMenu.setParentId(1L);
        roleMenu.setType(1);
        roleMenu.setUrl("system/role");
        roleMenu.setOrderNum(3);
        menus.add(roleMenu);

        Menu menuMenu = new Menu();
        menuMenu.setId(14L);
        menuMenu.setName("菜单管理");
        menuMenu.setIcon("fa fa-th-list");
        menuMenu.setParentId(1L);
        menuMenu.setType(1);
        menuMenu.setUrl("system/menu");
        menuMenu.setOrderNum(4);
        menus.add(menuMenu);

        return menus;
    }
}
