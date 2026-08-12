package com.example.demo.controller;

import com.evan.mangocore.http.HttpResult;
import com.evan.mangocore.page.PageRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.FileUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户管理 前端控制器
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('sys:user:add') or hasAuthority('sys:user:edit')")
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody User record) {
        return HttpResult.ok(userService.saveOrUpdate(record));
    }

    @PreAuthorize("hasAuthority('sys:user:delete')")
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody Map<String, String> params) {
        String ids = params.get("ids");
        return HttpResult.ok(userService.removeByIds(Arrays.stream(ids.split(","))
                .map(Long::valueOf).collect(Collectors.toList())));
    }

    @GetMapping(value = "/findAll")
    public Object findAll() {
        return userService.findAll();
    }

    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(userService.findPage(pageRequest));
    }

    @PostMapping(value = "/exportExcelUser")
    public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) {
        File file = userService.createUserExcelFile(pageRequest);
        FileUtils.downloadFile(res, file, file.getName());
    }
}
