package com.ruoyi.merchant.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cn.hutool.json.JSONUtil;
import com.ruoyi.capital.dto.AccountUpdateDto;
import com.ruoyi.channel.domain.ChannelAccount;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.company.domain.CompanyInfo;
import com.ruoyi.company.service.ICompanyInfoService;
import com.ruoyi.merchant.domain.MerAccount;
import com.ruoyi.merchant.service.IMerAccountService;
import com.ruoyi.web.controller.tool.GoogleAuthenticator;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 商户基础信息Controller
 * 
 * @author ruoyi
 * @date 2021-04-30
 */
@Controller
@RequestMapping("/merchant/merinfo")
public class MerInfoController extends BaseController
{
    private String prefix = "merchant/merinfo";

    @Autowired
    private IMerInfoService merInfoService;
    @Autowired
    private ICompanyInfoService companyInfoService;
    @Autowired
    private IMerAccountService merAccountService;
    @Resource
    RabbitTemplate rabbitTemplate;
    @Value("${order.account.queue}")
    private  String orderAccountQueue;
    @RequiresPermissions("merchant:merinfo:view")
    @GetMapping()
    public String merinfo(ModelMap mmap)
    {

        CompanyInfo companyInfo =  new CompanyInfo();
        companyInfo.setStatus(1);
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType().intValue()==4) {
            companyInfo.setId(sysUser.getUserTypeId());
        }
        List<CompanyInfo> infoList = companyInfoService.selectCompanyInfoList(companyInfo);
        mmap.put("companyList",infoList);
        return prefix + "/merinfo";
    }

    /**
     * 查询商户基础信息列表
     */
    @RequiresPermissions("merchant:merinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MerInfo merInfo)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
//                merInfo.setId(sysUser.getUserTypeId());
                MerInfo info = merInfoService.selectMerInfoById(sysUser.getUserTypeId());
                List<MerInfo> list = new ArrayList<>();
                list.add(info);
                return getDataTable(list);
            }else if(sysUser.getType().intValue()==2) {


            }
            else if(sysUser.getType().intValue()==4) {
                merInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<MerInfo> list = merInfoService.selectMerInfoList(merInfo);
        return getDataTable(list);
    }

    /**
     * 导出商户基础信息列表
     */
    @RequiresPermissions("merchant:merinfo:export")
    @Log(title = "商户基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MerInfo merInfo)
    {
        List<MerInfo> list = merInfoService.selectMerInfoList(merInfo);
        ExcelUtil<MerInfo> util = new ExcelUtil<MerInfo>(MerInfo.class);
        return util.exportExcel(list, "商户基础信息数据");
    }

    /**
     * 新增商户基础信息
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        CompanyInfo companyInfo =  new CompanyInfo();
        companyInfo.setStatus(1);
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType().intValue()==4) {
            companyInfo.setId(sysUser.getUserTypeId());
        }
        List<CompanyInfo> infoList = companyInfoService.selectCompanyInfoList(companyInfo);
        mmap.put("infoList",infoList);
        mmap.put("md5Key", UUID.randomUUID().toString().replace("-",""));
        return prefix + "/add";
    }

    /**
     * 新增保存商户基础信息
     */
    @RequiresPermissions("merchant:merinfo:add")
    @Log(title = "商户基础信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MerInfo merInfo)
    {
        return toAjax(merInfoService.insertMerInfo(merInfo));
    }

    /**
     * 修改商户基础信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MerInfo merInfo = merInfoService.selectMerInfoById(id);
        mmap.put("merInfo", merInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户基础信息
     */
    @RequiresPermissions("merchant:merinfo:edit")
    @Log(title = "商户基础信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MerInfo merInfo)
    {
        return toAjax(merInfoService.updateMerInfo(merInfo));
    }

    /**
     * 删除商户基础信息
     */
    @RequiresPermissions("merchant:merinfo:remove")
    @Log(title = "商户基础信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(merInfoService.deleteMerInfoByIds(ids));
    }
    @GetMapping("/googleCode")
    public String googleCode(Long id,ModelMap mmap)
    {
        MerInfo merInfo = merInfoService.selectMerInfoById(id);
        mmap.put("merInfo", merInfo);
        return prefix + "/googleCode";
    }
    /**
     * 删除商户基础信息
     */
    @RequiresPermissions("merchant:merinfo:remove")
    @Log(title = "商户基础信息", businessType = BusinessType.DELETE)
    @PostMapping( "/removeMer")
    @ResponseBody
    public String removeMer(String id,String googleCode)
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
        if(merInfoService.deleteMerInfoById(Long.valueOf(id))<1){
            return "删除失败";
        }
        return "操作成功";
    }
    @GetMapping("/addAmount")
    public String addAmount(Long id,ModelMap mmap)
    {
        MerInfo merInfo = merInfoService.selectMerInfoById(id);
        mmap.put("merInfo", merInfo);
        return prefix + "/addAmount";
    }
    /**
     * 增加授信额
     */
    @Log(title = "增加授信额", businessType = BusinessType.UPDATE)
    @PostMapping( "/addSaveAmount")
    @ResponseBody
    public String addSaveAmount(Long id,Long amount)
    {
        String rsp="增加授信成功";
        MerAccount merAccount=new MerAccount();
        merAccount.setMerId(id);
        //处理资金
        List<MerAccount> merAccountList = merAccountService.selectMerAccountList(merAccount);
        if(merAccountList==null||merAccountList.size()==0){
            return "资金信息异常";
        }
        merAccount=merAccountList.get(0);
        //处理资金
        try {
            AccountUpdateDto accountDto=new AccountUpdateDto();
            accountDto.setType(5);
            accountDto.setAccountType(1);
            accountDto.setMerAmt(amount*100);//换算成分
            accountDto.setMerId(merAccount.getMerId());
            accountDto.setDesc("后台增加商户授信额度");
            String no = DateUtils.parseDateToStr("yyyyMMddHHmmss",new Date())+merAccount.getMerId();
            accountDto.setOrderId(Long.valueOf(no));
            rabbitTemplate.convertAndSend(orderAccountQueue, JSONUtil.toJsonStr(accountDto));
        } catch (Exception e) {
            e.printStackTrace();
            rsp="增加授信失败";
        }

        return rsp;
    }
    @GetMapping("/reduceAmount")
    public String reduceAmount(Long id,ModelMap mmap)
    {
        MerInfo merInfo = merInfoService.selectMerInfoById(id);
        mmap.put("merInfo", merInfo);
        return prefix + "/reduceAmount";
    }
    /**
     * 减少授信额
     */
    @Log(title = "减少授信额", businessType = BusinessType.UPDATE)
    @PostMapping( "/reduceSaveAmount")
    @ResponseBody
    public String reduceSaveAmount(Long id,Long amount)
    {
        String rsp="减少授信成功";
        MerAccount merAccount=new MerAccount();
        merAccount.setMerId(id);
        //处理资金
        List<MerAccount> merAccountList = merAccountService.selectMerAccountList(merAccount);
        if(merAccountList==null||merAccountList.size()==0){
            return "资金信息异常";
        }
        merAccount=merAccountList.get(0);
        //处理资金
        try {
            AccountUpdateDto accountDto=new AccountUpdateDto();
            accountDto.setType(5);
            accountDto.setAccountType(1);
            accountDto.setMerAmt(0-amount*100);//换算成分
            accountDto.setMerId(merAccount.getMerId());
            accountDto.setDesc("后台减少商户授信额度");
            String no = DateUtils.parseDateToStr("yyyyMMddHHmmss",new Date())+merAccount.getMerId();
            accountDto.setOrderId(Long.valueOf(no));
            rabbitTemplate.convertAndSend(orderAccountQueue, JSONUtil.toJsonStr(accountDto));
        } catch (Exception e) {
            e.printStackTrace();
            rsp="减少授信失败";
        }

        return rsp;
    }
}
