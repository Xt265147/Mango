package com.example.demo.service.impl;

import com.evan.mangocore.page.MybatisPageHelper;
import com.evan.mangocore.page.PageRequest;
import com.evan.mangocore.page.PageResult;
import com.example.demo.model.User;
import com.example.demo.dao.UserMapper;
import com.example.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.example.demo.utils.PoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, userMapper);
    }

    @Override
    public List<User> findAll() {
        return list();
    }

    @SuppressWarnings("unchecked")
    public File createUserExcelFile(PageRequest pageRequest) {
        PageResult pageResult = findPage(pageRequest);
        return createUserExcelFile((List<User>) pageResult.getContent());
    }

    public static File createUserExcelFile(List<User> list) {
        if (list == null) {
            list = new ArrayList<>();
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("用户列表");

        // 表头
        Row header = sheet.createRow(0);
        String[] headers = {
                "No", "ID", "用户名", "昵称", "机构", "邮箱",
                "手机号", "状态", "头像", "创建人", "创建时间", "最后更新人", "最后更新时间"
        };

        for (int i = 0; i < headers.length; i++) {
            header.createCell(i).setCellValue(headers[i]);
        }

        // 数据行
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(i);
            Row row = sheet.createRow(i + 1);

            int col = 0;
            row.createCell(col++).setCellValue(i + 1);
            row.createCell(col++).setCellValue(u.getId());
            row.createCell(col++).setCellValue(u.getName());
            row.createCell(col++).setCellValue(u.getNickName());
            row.createCell(col++).setCellValue(u.getDeptId());
            row.createCell(col++).setCellValue(u.getEmail());
            row.createCell(col++).setCellValue(u.getMobile());
            row.createCell(col++).setCellValue(u.getStatus());
            row.createCell(col++).setCellValue(u.getAvatar());
            row.createCell(col++).setCellValue(u.getCreateBy());
            row.createCell(col++).setCellValue(
                    u.getCreateTime() != null ? u.getCreateTime().toString() : ""
            );
            row.createCell(col++).setCellValue(u.getLastUpdateBy());
            row.createCell(col++).setCellValue(
                    u.getLastUpdateTime() != null ? u.getLastUpdateTime().toString() : ""
            );
        }

        return PoiUtils.createExcelFile(workbook, "download_user");
    }
}