package com.example.demo.controller;

import com.evan.mangocore.http.HttpResult;
import com.evan.mangocore.page.PageRequest;
import com.example.demo.model.Dict;
import com.example.demo.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 数典表 前端控制器
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
@RestController
@RequestMapping("/dict")
public class DictController {
    @Autowired
    private DictService dictService;

    @PreAuthorize("hasAuthority('dict:add')AND hasAuthority('sys:dict:edit')")
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody Dict record) {
        return HttpResult.ok(dictService.save(record));
    }

    @PreAuthorize("hasAuthority('dict:delete')")
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<Dict> record) {
        return HttpResult.ok(dictService.removeByIds(record));
    }

    @PreAuthorize("hasAuthority('dict:view')")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(dictService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('dict:view')")
    @GetMapping(value = "/findById")
    public HttpResult findById(@RequestParam String label) {
        return HttpResult.ok(dictService.findByLabel(label));
    }


}
