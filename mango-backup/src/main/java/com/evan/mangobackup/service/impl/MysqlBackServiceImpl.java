package com.evan.mangobackup.service.impl;

import com.evan.mangobackup.service.MysqlBackupService;
import com.evan.mangobackup.utils.MySqlBackupRestoreUtils;
import org.springframework.stereotype.Service;

@Service
public class MysqlBackServiceImpl implements MysqlBackupService {

    @Override
    public boolean backup(String host, String userName, String password,
                          String backupFilePath, String filmName, String database) throws Exception {
        return MySqlBackupRestoreUtils.backup(host, userName, password, backupFilePath, filmName, database);
    }

    @Override
    public boolean restore(String backupFilePath, String host, String userName, String password,
                           String database) throws Exception {
        return MySqlBackupRestoreUtils.restore(backupFilePath, host, userName, password, "", "", database);
    }
}
