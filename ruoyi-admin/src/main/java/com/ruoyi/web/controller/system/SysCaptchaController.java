package com.ruoyi.web.controller.system;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import com.ruoyi.web.controller.tool.MailAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.ruoyi.common.core.controller.BaseController;

/**
 * 图片验证码（支持算术形式）
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/captcha")
public class SysCaptchaController extends BaseController
{
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;
    @Autowired
    private ISysUserService sysUserService;

    @Resource
    private RedisTemplate redisTemplate;
    @Value("${mail.host}")
    private  String host;
    @Value("${mail.username}")
    private  String mailname;
    @Value("${mail.password}")
    private  String password;
    @PostMapping("/mailCaptcha")
    @ResponseBody
    public AjaxResult ajaxLogin(String username)
    {
        //
        SysUser sysUser = sysUserService.selectUserByLoginName(username);
        if(sysUser==null){
            return error("用户名不存在");
        }
        if(StringUtils.isEmpty(sysUser.getEmail())){
            return error("用户邮箱未设置");
        }
        MailAuthenticator mailAuthenticator = new MailAuthenticator();
        try {
            String captchaCode = mailAuthenticator.genMailCode();
            redisTemplate.opsForValue().set(username+"-mailcode",captchaCode, mailAuthenticator.MINUTES,TimeUnit.MINUTES);
            System.out.println("用户："+username+"，邮箱验证码："+captchaCode);
            String[] mails = mailname.split(";");
            String[] pwds = password.split(";");
            Random random = new Random();
            int r =random.nextInt(mails.length);
            boolean flag = mailAuthenticator.sendMail(host,mails[r],pwds[r],sysUser.getEmail(),captchaCode);
            if(flag)return success();
            else return error("发送邮箱验证码失败");
        }catch (Exception e){
                e.printStackTrace();
                return error("发送邮箱验证码失败");
        }
    }
    /**
     * 验证码生成
     */
    @GetMapping(value = "/captchaImage")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response)
    {
        ServletOutputStream out = null;
        try
        {
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            String type = request.getParameter("type");
            String capStr = null;
            String code = null;
            BufferedImage bi = null;
            if ("math".equals(type))
            {
                String capText = captchaProducerMath.createText();
                capStr = capText.substring(0, capText.lastIndexOf("@"));
                code = capText.substring(capText.lastIndexOf("@") + 1);
                bi = captchaProducerMath.createImage(capStr);
            }
            else if ("char".equals(type))
            {
                capStr = code = captchaProducer.createText();
                bi = captchaProducer.createImage(capStr);
            }
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, code);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}