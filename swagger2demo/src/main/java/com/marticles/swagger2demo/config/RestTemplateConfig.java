package com.marticles.swagger2demo.config;

import com.marticles.swagger2demo.interceptor.CustomClientHttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


import java.util.Collections;



@Configuration
public class RestTemplateConfig {

    @Autowired
    CustomClientHttpRequestInterceptor customClientHttpRequestInterceptor;

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setInterceptors(Collections.singletonList(customClientHttpRequestInterceptor));
        return restTemplate;
    }


}
