package com.marticles.springsessiondemo.service;

import com.marticles.springsessiondemo.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Li Jianhua
 * @description SessionService
 * @date 2018/12/18
 */

@Service
public class DemoService {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    RedisUtils redisUtils;

    public void saveCaptcha(String captchaId, String captchaValue) {
        int captchaExpiredSeconds = 60 * 5;
        // 生成验证码唯一ID与对应的值
        // 存入Redis，5分钟过期
        redisUtils.setex(captchaId, captchaValue, 60 * 5);
    }

    public String getCapture(String captureId) {
        return redisUtils.get(captureId);
    }

    public boolean vrifyUser(String name, String password) {
        String getName = redisUtils.get(name);
        if(getName!=null){
            return getName.equals(password) ? true : false;
        }
        else {
            return false;
        }
    }

    public String getRandomAlphanumeric(int length) {
        StringBuffer sb = new StringBuffer(length);
        Random random = new Random();
        while (length-- != 0) {
            // random.nextInt(2) & 1 = random.nextInt(2) % 2 ,The previous way is more efficient obviously.
            if ((random.nextInt(2) & 1) == 0) {
                int temp = (random.nextInt(2) & 1) == 0 ? 65 : 97;
                int ascii = random.nextInt(26);
                // use ascii to generate random character
                sb.append((char) (ascii + temp));
            } else {
                // generate random number
                sb.append(String.valueOf(random.nextInt(10)));
            }
        }
        return sb.toString();
    }

}
