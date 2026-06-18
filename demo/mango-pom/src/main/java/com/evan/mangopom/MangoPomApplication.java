package com.evan.mangopom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MangoPomApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoPomApplication.class, args);
    }

}
