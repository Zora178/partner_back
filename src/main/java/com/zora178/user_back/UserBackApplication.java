package com.zora178.user_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.zora178.user_back.mapper")
public class UserBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserBackApplication.class, args);
    }

}
