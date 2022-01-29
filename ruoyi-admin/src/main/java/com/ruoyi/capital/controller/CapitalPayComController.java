package com.ruoyi.capital.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ruoyi.capital.domain.CapitalAccInfo;
import com.ruoyi.capital.domain.CapitalPayLog;
import com.ruoyi.capital.dto.AccountUpdateDto;
import com.ruoyi.capital.service.ICapitalAccInfoService;
import com.ruoyi.capital.service.ICapitalPayLogService;
import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.company.domain.CompanyInfo;
import com.ruoyi.company.service.ICompanyInfoService;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.service.IChannelMerAuthService;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.web.controller.tool.GoogleAuthenticator;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 资金转账记录Controller
 *
 * @author ruoyi
 * @date 2021-05-12
 */
@Controller
@RequestMapping("/capital/capitalPayCom")
public class CapitalPayComController extends BaseController
{
    private String prefix = "capital/capitalPayCom";

    @Autowired
    private ICapitalPayLogService capitalPayLogService;
    @Autowired
    private IChannelInfoService channelInfoService;
    @Autowired
    private IMerInfoService merInfoService;
    @Autowired
    private ICapitalAccInfoService capitalAccInfoService;
    @Autowired
    private IChannelMerAuthService channelMerAuthService;
    @Autowired
    private ICompanyInfoService companyInfoService;
    @Resource
    RabbitTemplate rabbitTemplate;
    @Value("${order.account.queue}")
    private  String orderAccountQueue;
    @RequiresPermissions("capital:capitalPayCom:view")
    @GetMapping()
    public String capitalPayCom(ModelMap mmap)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        MerInfo merInfo = new MerInfo();
        merInfoService.selectMerInfoList(merInfo);
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                merInfo.setId(sysUser.getUserTypeId());
                List<MerInfo>  companyList =merInfoService.selectMerInfoList(merInfo);
                mmap.put("companyList",companyList);
            }else if(sysUser.getType().intValue()==2) {
                List<MerInfo>  merList =merInfoService.selectMerInfoList(merInfo);
                mmap.put("merList",merList);
            }else if(sysUser.getType().intValue()==3){
                ChannelInfo channelInfo = new ChannelInfo();
                List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
                mmap.put("channelList",channelList);
                CompanyInfo companyInfo = new CompanyInfo();
                List<CompanyInfo>  companyList =companyInfoService.selectCompanyInfoList(companyInfo);
                mmap.put("companyList",companyList);
            }else if(sysUser.getType().intValue()==4) {
                ChannelInfo channelInfo = new ChannelInfo();
                channelInfo.setCompanyId(sysUser.getUserTypeId());
                List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
                mmap.put("channelList",channelList);
            }
        }
        mmap.put("type",sysUser.getType());
        return prefix + "/capitalPayList";
    }

    /**
     * 查询资金转账记录列表
     */
    @RequiresPermissions("capital:capitalPayCom:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CapitalPayLog capitalPayLog)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                capitalPayLog.setMerId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==2) {
                capitalPayLog.setChannelId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==4) {
                capitalPayLog.setCompanyId(sysUser.getUserTypeId());
            }
        }
        capitalPayLog.setType(2);
        List<CapitalPayLog> list = capitalPayLogService.selectCapitalPayLogList(capitalPayLog);
        return getDataTable(list);
    }
    /**
     * 查询资金转账记录列表
     */
    @RequiresPermissions("capital:capitalInfo:list")
    @PostMapping("/listPay")
    @ResponseBody
    public TableDataInfo listPay(CapitalPayLog capitalPayLog)
    {
        startPage();
        List<CapitalPayLog> list = capitalPayLogService.selectCapitalPayLogList(capitalPayLog);
        return getDataTable(list);
    }
    /**
     * 查询待确认转账记录
     */
    @RequiresPermissions("capital:capitalPayCom:edit")
    @GetMapping("/audit/{id}")
    public String searchPayLog(@PathVariable("id") Long id,ModelMap modelMap)
    {
        CapitalPayLog capitalPayLog = capitalPayLogService.selectCapitalPayLogById(id);
        modelMap.put("capitalPayLog", capitalPayLog);
        return prefix + "/audit";
    }
    /**
     * 审核保存资金转账记录
     */
    @RequiresPermissions("capital:capitalPayCom:edit")
    @Log(title = "资金转账记录", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    @ResponseBody
    @Transactional
    public AjaxResult auditSave(CapitalPayLog capitalPayLog,String googleCode){
        //异步更新资金账户信息
        //校验审核状态
        CapitalPayLog log = capitalPayLogService.selectCapitalPayLogById(capitalPayLog.getId());
        if(log.getStatus()!=0){
            return error("已审核请勿重复操作");
        }
        if(googleCode==null||googleCode.equals("")) {
        	return error("google验证码错误");
        }
        SysUser sysUser = ShiroUtils.getSysUser();
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        boolean r = ga.check_code(sysUser.getGoogleKey(), Long.valueOf(googleCode), t);
        if(!r) {
        	return error("google验证码错误");
        }
        if(capitalPayLog.getStatus()==1) {
	        try {
	            AccountUpdateDto accountDto=new AccountUpdateDto();
	            accountDto.setType(4);
	            accountDto.setAccountType(2);
	            accountDto.setSysAmt(capitalPayLog.getAmt().longValue()*100);
	            accountDto.setChannelId(capitalPayLog.getChannelId());
	            String no = DateUtils.parseDateToStr("yyyyMMddHHmmss",new Date())+capitalPayLog.getChannelId();
	            accountDto.setOrderId(Long.valueOf(no));
	            rabbitTemplate.convertAndSend(orderAccountQueue, JSONUtil.toJsonStr(accountDto));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }
        capitalPayLog.setAuditTime(new Date());
        return toAjax(capitalPayLogService.updateCapitalPayLog(capitalPayLog));
    }
    /**
     * 导出资金转账记录列表
     */
    @RequiresPermissions("capital:capitalPayCom:export")
    @Log(title = "资金转账记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CapitalPayLog capitalPayLog)
    {
        List<CapitalPayLog> list = capitalPayLogService.selectCapitalPayLogList(capitalPayLog);
        ExcelUtil<CapitalPayLog> util = new ExcelUtil<CapitalPayLog>(CapitalPayLog.class);
        return util.exportExcel(list, "资金转账记录数据");
    }

    /**
     * 新增资金转账记录
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        CapitalAccInfo capitalAccInfo = new CapitalAccInfo();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
               //查询商户对应数据
                capitalAccInfo.setMerId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==2) {

            }else if(sysUser.getType().intValue()==4) {
                capitalAccInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
//        capitalAccInfo.setStatus(1);
        capitalAccInfo.setType(2);
        List<CapitalAccInfo> channelAccList= capitalAccInfoService.selectCapitalAccInfoList(capitalAccInfo);
        capitalAccInfo.setType(3);
        List<CapitalAccInfo> companyAccList= capitalAccInfoService.selectCapitalAccInfoList(capitalAccInfo);
        mmap.put("channelAccList",channelAccList);
        mmap.put("companyAccList",companyAccList);
        return  prefix + "/add";
    }
    @GetMapping("/channelAccList")
    @ResponseBody
    public List<CapitalAccInfo> channelAccList(String accId)
    {
        if(StrUtil.isBlank(accId)){
            return null;
        }
        CapitalAccInfo merAccInfo = capitalAccInfoService.selectCapitalAccInfoById(Long.valueOf(accId));

        CapitalAccInfo capitalAccInfo = new CapitalAccInfo();
        capitalAccInfo.setCompanyId(merAccInfo.getCompanyId());
        capitalAccInfo.setType(2);
        //查询关联关系的通道

        List<CapitalAccInfo> channelAccList= capitalAccInfoService.selectCapitalAccInfoList(capitalAccInfo);
        return channelAccList;
    }
    /**
     * 新增保存资金转账记录
     */
    @RequiresPermissions("capital:capitalPayCom:add")
    @Log(title = "资金转账记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CapitalPayLog capitalPayLog)
    {
        if(capitalPayLog.getPayCapitalAccId()==null){
            return error("请选择打款账户");
        }
        if(capitalPayLog.getPayCapitalAccId()==null){
            return error("请选择收款账户");
        }
        if(capitalPayLog.getAmt()==null){
            return error("请填写金额");
        }
        CapitalAccInfo recAccInfo=capitalAccInfoService.selectCapitalAccInfoById(capitalPayLog.getRecCapitalAccId());
        CapitalAccInfo payAccInfo=capitalAccInfoService.selectCapitalAccInfoById(capitalPayLog.getPayCapitalAccId());
        capitalPayLog.setChannelId(recAccInfo.getChannelId());
        capitalPayLog.setChannelName(recAccInfo.getChannelName());
        capitalPayLog.setRecCapitalAccNo(recAccInfo.getCapitalAccNo());
        capitalPayLog.setRecCapitalAccName(recAccInfo.getCapitalAccName());
        capitalPayLog.setCompanyId(payAccInfo.getCompanyId());
        capitalPayLog.setCompanyName(payAccInfo.getCompanyName());
        capitalPayLog.setPayCapitalAccNo(payAccInfo.getCapitalAccNo());
        capitalPayLog.setPayCapitalAccName(payAccInfo.getCapitalAccName());
        capitalPayLog.setCreateTime(new Date());
        capitalPayLog.setType(2);
        capitalPayLog.setStatus(0);
        return toAjax(capitalPayLogService.insertCapitalPayLog(capitalPayLog));
    }

    /**
     * 修改资金转账记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CapitalPayLog capitalPayLog = capitalPayLogService.selectCapitalPayLogById(id);
        mmap.put("capitalPayLog", capitalPayLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存资金转账记录
     */
    @RequiresPermissions("capital:capitalPayCom:edit")
    @Log(title = "资金转账记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CapitalPayLog capitalPayLog)
    {
        return toAjax(capitalPayLogService.updateCapitalPayLog(capitalPayLog));
    }

    /**
     * 删除资金转账记录
     */
    @RequiresPermissions("capital:capitalPayCom:remove")
    @Log(title = "资金转账记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(capitalPayLogService.deleteCapitalPayLogByIds(ids));
    }
}
