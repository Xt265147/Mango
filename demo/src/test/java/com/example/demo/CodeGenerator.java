package com.example.demo;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mango?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai",
                        "root",
                        "root")
                .globalConfig(builder -> builder
                        .author("evan")
                        .outputDir("D:/Mango/demo/src/main/java")
                        .commentDate("yyyy-MM-dd")
                        .disableOpenDir()
                )
                .packageConfig(builder -> builder
                        .parent("com.example.demo")
                        .entity("model")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("dao")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, "D:/Mango/demo/src/main/resources/sqlmap"))
                )
                .strategyConfig(builder -> builder
                        .addInclude(
                                "sys_user", "sys_role", "sys_dept", "sys_menu",
                                "sys_user_role", "sys_role_menu", "sys_role_dept",
                                "sys_dict", "sys_config", "sys_log", "sys_login_log"
                        )
                        .addTablePrefix("sys_")

                        .entityBuilder()
                        .enableLombok()
                        .enableTableFieldAnnotation()
                        .logicDeleteColumnName("del_flag")

                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()

                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}