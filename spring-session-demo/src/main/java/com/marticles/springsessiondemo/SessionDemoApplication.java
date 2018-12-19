package com.marticles.springsessiondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@SpringBootApplication
public class SessionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionDemoApplication.class, args);
    }
}
