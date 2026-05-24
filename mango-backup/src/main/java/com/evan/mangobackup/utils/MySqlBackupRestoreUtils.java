package com.evan.mangobackup.utils;

import java.io.File;
import java.io.IOException;

public class MySqlBackupRestoreUtils {
    /**
     * MySql数据库备份、还原工具类
     *
     * @param host     数据库服务器地址
     * @param userName 数据库用户名
     * @param password 数据库密码
     * @param savePath 数据库备份文件保存路径
     * @param fileName 数据库备份文件名
     * @ throws IOException
     */

    public static boolean backup(String host, String userName, String password, String savePath, String fileName, String database) throws InterruptedException, IOException {
        File backupFolderFile = new File(savePath);
        if (!backupFolderFile.exists()) {
            // 创建目录
            backupFolderFile.mkdirs();
        }
        if (!savePath.endsWith(File.separator) && !savePath.endsWith("/")) {
            backupFolderFile = new File(savePath + File.separator);
        }
        // 拼接命令
        String backupFilePath = backupFolderFile + fileName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysqldump --opt").append(" --add-drop-database ")
                .append(" --add-drop-table");
        stringBuilder.append(" -h").append(host).append(" -u").append(userName).append(
                " -p").append(password);
        stringBuilder.append(" --result-file=").append(backupFilePath).append(
                "--default-character-set=utf8 ").append(database);

        // 执行命令
        Process process = Runtime.getRuntime().exec(stringBuilder.toString());
        if (process.waitFor() == 0) {
            // 成功
            System.out.println("备份成功！在" + backupFilePath + "目录下找到备份文件");
            return true;
        }
        return false;
    }

    /**
     * MySql数据库备份、还原工具类
     *
     * @param host         数据库服务器地址
     * @param userName     数据库用户名
     * @param password     数据库密码
     * @param savePath     数据库备份文件保存路径
     * @param fileName     数据库备份文件名
     * @param databaseName 数据库名称
     * @ throws IOException
     */

    public static boolean restore(String restoreFilePath, String host, String userName, String password, String savePath, String fileName, String databaseName) throws IOException, InterruptedException {
        File restoreFile = new File(restoreFilePath);
        if (restoreFile.isDirectory()) {
            for (File file : restoreFile.listFiles()) {
                if (file.exists() && file.getPath().endsWith(".sql")) {
                    restoreFilePath = file.getAbsolutePath();
                    break;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysql -h").append(host).append(" -u").append(userName).append(
                " -p").append(password).append(" ").append(databaseName);
        try {
            Process process = Runtime.getRuntime().exec(stringBuilder.toString());
            if (process.waitFor() == 0) {
                System.out.println("数据已经从 " + restoreFilePath + " 文件中导入成功！");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
