package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="spring.datasource.druid")
public class DruidDataSourceProperties {
    //jdbc
    @Setter@Getter
    private String driverClassName;
    @Setter@Getter
    private String url;
    @Setter@Getter
    private String username;
    @Setter@Getter
    private String password;

    //jdbc connection pool
    @Setter@Getter
    private int initialSize;
    @Setter@Getter
    private int minIdle;
    @Setter@Getter
    private int maxActive =100;
    @Setter@Getter
    private int maxWait;
    @Setter@Getter
    private int timeBetweenEvictionRunsMillis;
    @Setter@Getter
    private int minEvictableIdleTimeMillis;
    @Setter@Getter
    private String validationQuery;
    @Setter@Getter
    private boolean testWhileIdle;
    @Setter@Getter
    private boolean testOnBorrow;
    @Setter@Getter
    private boolean testOnReturn;
    @Setter@Getter
    private boolean poolPreparedStatements;
    @Setter@Getter
    private int maxPoolPreparedStatementPerConnectionSize;

    //filter
    @Setter@Getter
    private String filters;

}
