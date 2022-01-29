package com.ruoyi.web.controller.system;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.company.domain.CompanyInfo;
import com.ruoyi.company.service.ICompanyInfoService;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.order.domain.SysAreaDto;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.web.controller.tool.GoogleAuthenticator;

import cn.hutool.json.JSONUtil;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * 用户信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController
{
    private String prefix = "system/user";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    private ICompanyInfoService companyService;
    @Autowired
    private IChannelInfoService channelInfoService;;
    @Autowired
    private IMerInfoService merInfoService;;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUser user)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                //不处理
            }else if(sysUser.getType().intValue()==4) {
                user.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysUser user)
    {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }
    /**
     * 二维码生成
     */
    @GetMapping(value = "/QRimage/{loginName}/{code}")
    public ModelAndView getQRImage(@PathVariable("loginName") String loginName,@PathVariable("code") String code,HttpServletRequest request, HttpServletResponse response)
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
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            String url = "otpauth://totp/"+loginName+"?secret="+code;
            BitMatrix matrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 300, 300);
            BufferedImage image = new BufferedImage(matrix.getWidth(), matrix.getHeight(), TYPE_INT_RGB);
            for (int x = 0; x < matrix.getWidth(); x++) {
                for (int y = 0; y < matrix.getHeight(); y++) {
                    image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
            out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
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

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> selectRoleAll  = new ArrayList<>();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==4) {
                SysRole sysRole = new SysRole();
                sysRole.setCompanyId(sysUser.getUserTypeId());
                selectRoleAll = roleService.selectRoleList(sysRole);
            }else {
                selectRoleAll = roleService.selectRoleAll().stream().filter(r -> !r.isAdmin()).collect(Collectors.toList());
            }
        }
    	List<SysAreaDto> roleDtoList=new ArrayList<SysAreaDto>(); 
		SysAreaDto roleDto=new SysAreaDto();
		roleDto.setV("1");
		roleDto.setN("商户类型");
		MerInfo merInfo=new MerInfo();
		merInfo.setStatus(1);
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==4) {
                merInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
		List<MerInfo> selectMerInfoList = merInfoService.selectMerInfoList(merInfo);
		List<SysAreaDto> merList=new ArrayList<SysAreaDto>();
		for (MerInfo mer : selectMerInfoList) {
			SysAreaDto companyDto=new SysAreaDto();
			companyDto.setV(mer.getId().toString());
			companyDto.setN(mer.getMerName());
			merList.add(companyDto);
		}
		roleDto.setS(merList);
		roleDtoList.add(roleDto);
		
		SysAreaDto roleDto2=new SysAreaDto();
		roleDto2.setV("4");
		roleDto2.setN("公司类型");
		CompanyInfo companyInfo=new CompanyInfo();
		companyInfo.setStatus(1);
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==4) {
                companyInfo.setId(sysUser.getUserTypeId());
            }
        }
		List<CompanyInfo> companyInfoList = companyService.selectCompanyInfoList(companyInfo);
		List<SysAreaDto> companyList=new ArrayList<SysAreaDto>();
		for (CompanyInfo company : companyInfoList) {
			SysAreaDto companyDto=new SysAreaDto();
			companyDto.setV(company.getId().toString());
			companyDto.setN(company.getCompanyName());
			companyList.add(companyDto);
		}
		roleDto2.setS(companyList);
		roleDtoList.add(roleDto2);
		String jsonStr=JSONUtil.toJsonStr(roleDtoList);
//        mmap.put("roles", roleService.selectRoleAll().stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        mmap.put("roles", selectRoleAll);
        mmap.put("roleJsonList", jsonStr);
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysUser user,String googleCode)
    {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName())))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        SysUser sysUser = ShiroUtils.getSysUser();
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        boolean r = ga.check_code(sysUser.getGoogleKey(), Long.valueOf(googleCode), t);
        if(!r) {
        	return error("google验证码错误");
        }
        String secret = GoogleAuthenticator.genSecret(user.getLoginName());
        user.setGoogleKey(secret);
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(userService.insertUser(user));
    }
    public static void main(String[] args) {
    	String genSecret = GoogleAuthenticator.genSecret("admin");
    	System.out.println(genSecret);
	}
    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        mmap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysUser user)
    {
        userService.checkUserAllowed(user);
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return error("修改用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return error("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(userService.updateUser(user));
    }

    @RequiresPermissions("system:user:resetPwd")
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SysUser user)
    {
        userService.checkUserAllowed(user);
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        if (userService.resetUserPwd(user) > 0)
        {
            if (ShiroUtils.getUserId().longValue() == user.getUserId().longValue())
            {
                ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

    /**
     * 进入授权角色页
     */
    @GetMapping("/authRole/{userId}")
    public String authRole(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        SysUser user = userService.selectUserById(userId);
        // 获取用户所属的角色列表
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        mmap.put("user", user);
        mmap.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return prefix + "/authRole";
    }

    /**
     * 用户授权角色
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PostMapping("/authRole/insertAuthRole")
    @ResponseBody
    public AjaxResult insertAuthRole(Long userId, Long[] roleIds)
    {
        userService.insertUserAuth(userId, roleIds);
        return success();
    }
    @GetMapping("/googleCode")
    public String googleCode(Long id,ModelMap mmap)
    {
        SysUser sysUser = userService.selectUserById(id);
        mmap.put("sysUser", sysUser);
        return prefix + "/googleCode";
    }
    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/removeUser")
    @ResponseBody
    public String removeUser(String id,String googleCode)
    {
        if(googleCode==null||googleCode.equals("")) {
            return "google验证码错误";
        }
        SysUser sysUser = ShiroUtils.getSysUser();
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        boolean r = ga.check_code(sysUser.getGoogleKey(), Long.valueOf(googleCode), t);
        if(!r) {
            return "google验证码错误";
        }
        if(userService.deleteUserById(Long.valueOf(id))<1){
            return "删除失败";
        }
        return "操作成功";
    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userService.deleteUserByIds(ids));
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(SysUser user)
    {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(SysUser user)
    {
        return userService.checkPhoneUnique(user);
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(SysUser user)
    {
        return userService.checkEmailUnique(user);
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysUser user)
    {
        userService.checkUserAllowed(user);
        return toAjax(userService.changeStatus(user));
    }
}