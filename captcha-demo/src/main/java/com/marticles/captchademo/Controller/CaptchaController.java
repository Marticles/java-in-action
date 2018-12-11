package com.marticles.captchademo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.google.code.kaptcha.impl.DefaultKaptcha;

@Slf4j
@Controller
public class CaptchaController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        // 产生验证码并保存到session中
        String createText = defaultKaptcha.createText();
        httpServletRequest.getSession().setAttribute("rightCode", createText);
        // 产生验证码图片
        BufferedImage bufferedImage = defaultKaptcha.createImage(createText);
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


    @PostMapping("/vrify")
    @ResponseBody
    public HashMap vrify(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String rightCode = (String) httpServletRequest.getSession().getAttribute("rightCode");
        String tryCode = httpServletRequest.getParameter("tryCode");
        log.info("-------生成验证码:【" + rightCode + "】 用户输入验证码:【" + tryCode + "】-------");
        HashMap<String,Integer> vrifyMap = new HashMap<String,Integer>();
        if (!rightCode.equals(tryCode)) {
            // 1为输入正确
            vrifyMap.put("vrify_result",1);
        } else {
            vrifyMap.put("vrify_result",0);
        }
        return vrifyMap;
    }


    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
