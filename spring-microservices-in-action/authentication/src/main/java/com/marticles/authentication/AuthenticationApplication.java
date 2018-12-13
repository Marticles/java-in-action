package com.marticles.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@RestController
@EnableResourceServer
@EnableAuthorizationServer
public class AuthenticationApplication {
    // 映射到"/auth/user"
    @RequestMapping(value = { "/user" }, produces = "application/json")
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<String, Object>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));

        HashMap<String,String> map = new HashMap<String, String>();
        for(Map.Entry<String,String> entry : map.entrySet()){
        }
        ArrayList<String> arr = new ArrayList<String>();
        for(String ar:arr){

        }

        return userInfo;


    }


    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }


}