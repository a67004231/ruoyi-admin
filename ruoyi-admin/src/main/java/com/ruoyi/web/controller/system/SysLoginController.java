package com.ruoyi.web.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.web.controller.tool.GoogleAuthenticator;
import sun.misc.BASE64Decoder;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Controller
public class SysLoginController extends BaseController
{
	@Resource
	ISysUserService userService;
    @Resource
    private RedisTemplate redisTemplate;
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe,String mailCode,String googleCode,String captchaType)
    {

        SysUser sysUser = userService.selectUserByLoginName(username);
        if(sysUser==null) {
            return error("账号信息错误");
        }
        if(captchaType.equals("1")){
            long t = System.currentTimeMillis();
            GoogleAuthenticator ga = new GoogleAuthenticator();
            ga.setWindowSize(5);
            boolean r = ga.check_code(sysUser.getGoogleKey(), Long.valueOf(googleCode), t);
            if(!r) {
                return error("Google验证码错误");
            }
        }else if(captchaType.equals("2")){
            //验证邮箱
            if(!redisTemplate.hasKey(username+"-mailcode")){
                return error("邮箱验证码已失效，请重新获取");
            }
            String captchaCode = redisTemplate.opsForValue().get(username+"-mailcode").toString();
            if(StringUtils.isEmpty(captchaCode)){
                return error("邮箱验证码已失效，请重新获取");
            }
            if(!mailCode.equals(captchaCode)){
                return error("邮箱验证码错误");
            }
        }
        try {
            password = new String((new BASE64Decoder()).decodeBuffer(password));
        } catch (IOException e) {
            e.printStackTrace();
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
}
