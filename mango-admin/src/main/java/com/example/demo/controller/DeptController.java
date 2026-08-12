package com.example.demo.controller;

import com.evan.mangocore.http.HttpResult;
import com.evan.mangocore.page.PageRequest;
import com.example.demo.model.Dept;
import com.example.demo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 机构管理 前端控制器
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @PreAuthorize("hasAuthority('sys:dept:add') or hasAuthority('sys:dept:edit')")
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody Dept record) {
        return HttpResult.ok(deptService.saveOrUpdate(record));
    }

    @PreAuthorize("hasAuthority('sys:dept:delete')")
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody Map<String, String> params) {
        String ids = params.get("ids");
        return HttpResult.ok(deptService.removeByIds(Arrays.stream(ids.split(","))
                .map(Long::valueOf).collect(Collectors.toList())));
    }

    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(deptService.findPage(pageRequest));
    }

    @GetMapping(value = "/findAll")
    public List<Dept> findAll() {
        return deptService.list();
    }
}
