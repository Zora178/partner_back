package com.zora178.user_back;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@SpringBootApplication
@Slf4j
@MapperScan("com.zora178.user_back.mapper")
public class UserBackApplication {

    public static void main(String[] args) {
        log.info(" start...--------------------------------------------------------------------");
        SpringApplication.run(UserBackApplication.class, args);
        log.info(" started successfully.-----------------------------------------------------");
    }

}
