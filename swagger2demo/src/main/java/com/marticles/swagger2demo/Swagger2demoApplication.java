package com.marticles.swagger2demo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.marticles.swagger2demo.dao")
@Slf4j
public class Swagger2demoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Swagger2demoApplication.class, args);
        log.info("***********************************************************************************");
        log.info("project is running, see api doc at http://localhost:8080/swagger-ui.html");
        log.info("***********************************************************************************");
    }
}
