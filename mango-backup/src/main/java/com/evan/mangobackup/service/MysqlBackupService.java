package com.evan.mangobackup.service;

/**
 * mysql备份服务
 *
 * @author Qianxun
 * @ date 2023/7/23
 */

public interface MysqlBackupService {
    /**
     * 备份数据库
     * @param host 数据库地址
     * @param userName 数据库用户名
     * @param password 数据库密码
     * @param backupFilePath 备份文件路径
     * @param filmName 备份文件名
     * @param database 数据库名
     * @ return
     * @ throws Exception
     */

    boolean backup(String host, String userName, String password,
                   String backupFilePath, String filmName, String database) throws Exception;

    /**
     * 还原数据库
     * @param host 数据库地址
     * @param userName 数据库用户名
     * @param password 数据库密码
     * @param backupFilePath 备份文件路径
     * @param database 数据库名
     * @ return
     * @ throws Exception
     */
    boolean restore(String backupFilePath,String host, String userName, String password,
                    String database)throws Exception;
 }
