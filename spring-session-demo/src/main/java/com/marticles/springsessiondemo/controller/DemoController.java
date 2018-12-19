package com.marticles.springsessiondemo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.marticles.springsessiondemo.model.User;
import com.marticles.springsessiondemo.service.DemoService;
import com.marticles.springsessiondemo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.code.kaptcha.impl.DefaultKaptcha;

@Slf4j
@RestController
public class DemoController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    DemoService demoService;

    @Autowired
    RedisUtils redisUtils;

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        String captchaId = "CAPTCHA_" + UUID.randomUUID().toString();
        String captchaValue = defaultKaptcha.createText();
        Cookie captchaCookie = new Cookie("captchaId", captchaId);

        captchaCookie.setPath("/");

        httpServletResponse.addCookie(captchaCookie);

        // 验证码存入Redis
        demoService.saveCaptcha(captchaId, captchaValue);
        // 产生验证码图片
        BufferedImage bufferedImage = defaultKaptcha.createImage(captchaValue);
        // 写入imgOutputStream中
        ImageIO.write(bufferedImage, "jpg", imgOutputStream);
        // 转为byte数组
        byte[] captchaBytes = imgOutputStream.toByteArray();
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        // 在response中写入byte数组
        responseOutputStream.write(captchaBytes);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @PostMapping("/checklogin")
    public HashMap<String, String> checkLogin(String token, HttpServletResponse httpServletResponse) {
        if (token == "") {
            token = UUID.randomUUID().toString();
            Cookie tokenCookie = new Cookie("token", token);
            tokenCookie.setPath("/");
            httpServletResponse.addCookie(tokenCookie);
            return null;
        } else {
            HashMap<String, String> userMap = new HashMap<String, String>();
            String name = redisUtils.get(token);
            if (name == null) {
                userMap.put("vrify_result", "0");
                return userMap;
            } else {
                userMap.put("vrify_result", "1");
                userMap.put("user_name", name);
                return userMap;
            }
        }
    }

    @PostMapping("/login")
    public HashMap login(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response) {
        String name = user.getName();
        String password = user.getPassword();
        String tryCapture = user.getCaptchaValue();
        String captureId = user.getCaptchaId();
        String rightCapture = demoService.getCapture(captureId);
        if (rightCapture == null) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        String token = "";
        for (Cookie cookie : cookies) {
            switch (cookie.getName()) {
                case "token":
                    token = cookie.getValue();
                    break;
                default:
                    break;
            }
        }
        log.info("-------生成验证码:【" + rightCapture + "】 用户输入验证码:【" + tryCapture + "】-------");
        HashMap<String, String> loginMap = new HashMap<String, String>();
        if (rightCapture.equals(tryCapture)) {
            if (demoService.vrifyUser(name, password)) {
                loginMap.put("vrify_result", "1");
                loginMap.put("user_name", name);
                redisUtils.setex(token, name, 60 * 5);
            } else {
                loginMap.put("vrify_result", "0");
            }
        } else {
            loginMap.put("vrify_result", "0");
        }

        return loginMap;
    }


    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
