package com.example.demo.controller;

import com.evan.mangocore.http.HttpResult;
import com.evan.mangocore.page.PageRequest;
import com.example.demo.service.UserService;
import com.example.demo.utils.FileUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

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

    @GetMapping(value = "/findAll")
    public Object findAll() {
        return userService.findAll();
    }

    @PostMapping(value = "/findPage")
    public HttpResult findPage(PageRequest pageRequest) {
        return HttpResult.ok(userService.findPage(pageRequest));
    }

    @PostMapping(value = "/exportExcelUser")
    public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) {
        File file = userService.createUserExcelFile(pageRequest);
        FileUtils.downloadFile(res, file, file.getName());
    }
}
