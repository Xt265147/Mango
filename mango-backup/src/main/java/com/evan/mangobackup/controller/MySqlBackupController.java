package com.evan.mangobackup.controller;

import com.evan.mangobackup.BackupDataSourceProperties;
import com.evan.mangobackup.service.MysqlBackupService;
import com.evan.mangocore.http.HttpResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MySqlBackupController {

    private static final String DEFAULT_BACKUP_FOLDER = "backup";
    private static final String RESTORE_FOLDER = "restore";
    private static final String DEFAULT_BACKUP_NAME = "mango_default.sql";
    private static final String BACKUP_FILE_NAME = "mango_backup.sql";

    @Autowired
    private BackupDataSourceProperties properties;

    @Autowired
    private MysqlBackupService mysqlBackupService;

    @GetMapping(value = "/backup")
    public HttpResult backup() {
        String host = properties.getHost();
        String userName = properties.getUserName();
        String password = properties.getPassword();
        String database = properties.getDatabase();
        String backupFolderName = DEFAULT_BACKUP_FOLDER + File.separator;
        try {
            boolean success = mysqlBackupService.backup(host, userName, password,
                    backupFolderName, BACKUP_FILE_NAME, database);
            if (!success) {
                return HttpResult.error("数据备份失败");
            }
        } catch (Exception e) {
            return HttpResult.error(500, e.getMessage());
        }
        return HttpResult.ok("数据备份成功");
    }

    @GetMapping(value = "/restore")
    public HttpResult restore(@RequestParam String fileName) throws IOException {
        String host = properties.getHost();
        String userName = properties.getUserName();
        String password = properties.getPassword();
        String database = properties.getDatabase();
        String restoreFilePath = RESTORE_FOLDER + File.separator + fileName;
        try {
            mysqlBackupService.restore(restoreFilePath, host, userName, password, database);
        } catch (Exception e) {
            return HttpResult.error(500, e.getMessage());
        }
        return HttpResult.ok("数据还原成功");
    }

    @GetMapping(value = "/findRecords")
    public HttpResult findRecords() {
        List<Map<String, String>> backupRecords = new ArrayList<>();
        File restoreFolderFile = new File(RESTORE_FOLDER);
        if (restoreFolderFile.exists()) {
            for (File file : restoreFolderFile.listFiles()) {
                Map<String, String> backupRecord = new HashMap<>();
                backupRecord.put("name", file.getName());
                backupRecord.put("title", file.getName());
                if (DEFAULT_BACKUP_NAME.equalsIgnoreCase(file.getName())) {
                    backupRecord.put("title", "系统默认备份");
                }
                backupRecords.add(backupRecord);
            }
        }
        backupRecords.sort((o1, o2) -> {
            boolean o1IsDefault = DEFAULT_BACKUP_NAME.equalsIgnoreCase(o1.get("name"));
            boolean o2IsDefault = DEFAULT_BACKUP_NAME.equalsIgnoreCase(o2.get("name"));
            if (o1IsDefault) {
                return -1;
            }
            if (o2IsDefault) {
                return 1;
            }
            return o2.get("name").compareTo(o1.get("name"));
        });
        return HttpResult.ok(backupRecords);
    }

    @GetMapping(value = "/delete")
    public HttpResult deleteBackupRecord(@RequestParam String fileName) {
        if (DEFAULT_BACKUP_NAME.equalsIgnoreCase(fileName)) {
            return HttpResult.error("默认备份不能删除");
        }
        String restoreFilePath = RESTORE_FOLDER + File.separator + fileName;
        File file = new File(restoreFilePath);
        if (file.exists()) {
            file.delete();
        }
        return HttpResult.ok("删除成功");
    }
}
