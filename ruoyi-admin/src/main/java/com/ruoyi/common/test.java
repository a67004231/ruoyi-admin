package com.ruoyi.common;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Description:发送邮件测试
 * @Company:biyao
 * @author:ZhouJiangxiao
 * @date:2016/9/27 14:55
 */
public class test {
    public static void main(String[] args) throws EmailException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        SimpleEmail email = new SimpleEmail();
        email.setHostName("SMTP.126.com");
        email.setAuthentication("liang6163068@126.com", "LRTCWVLSFPULJCUA");//邮件服务器验证：用户名/密码
        email.setCharset("UTF-8");// 必须放在前面，否则乱码
        email.addTo("289477426@qq.com");
        email.setFrom("liang6163068@126.com", "后台登录运营团队");
        email.setSubject("邮箱验证码邮件");
        StringBuilder msgInfo = new StringBuilder();
        Random random = new Random();
        Integer ss = random.nextInt(9999);
        msgInfo.append("邮箱验证码如下：").append("\r\n\t");
        msgInfo.append(""+ss+"").append("\r\n\t");
        msgInfo.append("请在两分钟之内输入验证码并登录系统").append("\r\n\t");
        msgInfo.append("发送时间：").append(sdf.format(new Date()));
        email.setMsg(msgInfo.toString());
//        String result = email.send();
//        System.out.println(result);
    }
}

