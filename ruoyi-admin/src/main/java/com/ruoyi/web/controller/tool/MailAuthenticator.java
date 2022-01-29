package com.ruoyi.web.controller.tool;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/** 
 * 
 * 
 * 邮箱验证器，java服务端实现
 * 
 * @author 
 * 
 * @version
 * 
 * 
 */
@Component
public class MailAuthenticator {


    @Resource
    private RedisTemplate redisTemplate;

    public  final int MINUTES = 2;

//    @Value("${mail.host}")
//    private  String host;
//    @Value("${mail.username}")
//    private  String username;
//    @Value("${mail.password}")
//    private  String password;

    public  Boolean sendMail(String host,String username,String password,String mail,String captchaCode) throws Exception {
        //发送验证码
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            SimpleEmail email = new SimpleEmail();
            email.setHostName(host);
            email.setSSLOnConnect(true);
            System.setProperty("mail.smtp.ssl.enable", "true");
            email.setAuthentication(username, password);//邮件服务器验证：用户名/密码
            email.setCharset("UTF-8");// 必须放在前面，否则乱码
            email.addTo(mail);
            email.setFrom(username, "后台运营团队");
            email.setSubject("邮箱验证码邮件");
            StringBuilder msgInfo = new StringBuilder();
            msgInfo.append("邮箱验证码如下(请勿泄露)：").append("\r\n\t");
            msgInfo.append(captchaCode).append("\r\n\t");
            msgInfo.append("请在两分钟之内输入验证码并登录系统").append("\r\n\t");
            msgInfo.append("发送时间：").append(sdf.format(new Date()));
            email.setMsg(msgInfo.toString());
            String result = email.send();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public  String genMailCode() {
        Random random = new Random();
        Integer captcha = random.nextInt(8999)+1000;
        return captcha.toString();
    }

}
