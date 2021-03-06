package com.ruoyi.capital.controller;

import java.util.Date;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.mchange.lang.LongUtils;
import com.ruoyi.capital.domain.CapitalAccInfo;
import com.ruoyi.capital.dto.AccountUpdateDto;
import com.ruoyi.capital.service.ICapitalAccInfoService;
import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.company.domain.CompanyInfo;
import com.ruoyi.company.service.ICompanyInfoService;
import com.ruoyi.merchant.domain.ChannelMerAuth;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.capital.domain.CapitalPayLog;
import com.ruoyi.capital.service.ICapitalPayLogService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * ??????????????????Controller
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
@Controller
@RequestMapping("/capital/capitalPayLog")
public class CapitalPayMerController extends BaseController
{
    private String prefix = "capital/capitalPayLog";

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
    @RequiresPermissions("capital:capitalPayLog:view")
    @GetMapping()
    public String capitalPayLog(ModelMap mmap)
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
//                ChannelInfo channelInfo = new ChannelInfo();
//                List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
//                mmap.put("channelList",channelList);
                List<MerInfo> merList = merInfoService.selectMerInfoList(merInfo);
                CompanyInfo companyInfo = new CompanyInfo();
                List<CompanyInfo>  companyList =companyInfoService.selectCompanyInfoList(companyInfo);
                mmap.put("companyList",companyList);
                mmap.put("merList",merList);
            }else if(sysUser.getType().intValue()==4) {
                merInfo.setCompanyId(sysUser.getUserTypeId());
                List<MerInfo>  merList =merInfoService.selectMerInfoList(merInfo);
                mmap.put("merList",merList);
            }
        }
        mmap.put("type",sysUser.getType());
        return prefix + "/capitalPayList";
    }

    /**
     * ??????????????????????????????
     */
    @RequiresPermissions("capital:capitalPayLog:list")
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
        capitalPayLog.setType(1);
        List<CapitalPayLog> list = capitalPayLogService.selectCapitalPayLogList(capitalPayLog);
        return getDataTable(list);
    }
    /**
     * ??????????????????????????????
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
     * ???????????????????????????
     */
    @RequiresPermissions("capital:capitalPayLog:edit")
    @GetMapping("/audit/{id}")
    public String searchPayLog(@PathVariable("id") Long id,ModelMap modelMap)
    {
        CapitalPayLog capitalPayLog = capitalPayLogService.selectCapitalPayLogById(id);
        modelMap.put("capitalPayLog", capitalPayLog);
        return prefix + "/audit";
    }
    /**
     * ??????????????????????????????
     */
    @RequiresPermissions("capital:capitalPayLog:edit")
    @Log(title = "??????????????????", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    @ResponseBody
    @Transactional
    public AjaxResult auditSave(CapitalPayLog capitalPayLog,String googleCode){
        //??????????????????????????????
        //??????????????????
        CapitalPayLog log = capitalPayLogService.selectCapitalPayLogById(capitalPayLog.getId());
        if(log.getStatus()!=0){
            return error("???????????????????????????");
        }
        if(googleCode==null||googleCode.equals("")) {
        	return error("google???????????????");
        }
        SysUser sysUser = ShiroUtils.getSysUser();
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        boolean r = ga.check_code(sysUser.getGoogleKey(), Long.valueOf(googleCode), t);
        if(!r) {
        	return error("google???????????????");
        }
        if(capitalPayLog.getStatus()==1) {
	        try {
	            AccountUpdateDto accountDto=new AccountUpdateDto();
	            accountDto.setType(4);
	            accountDto.setAccountType(1);
	            accountDto.setMerId(capitalPayLog.getMerId());
	            accountDto.setMerAmt(capitalPayLog.getAmt().longValue()*100);
	            String no = DateUtils.parseDateToStr("yyyyMMddHHmmss",new Date())+capitalPayLog.getMerId();
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
     * ??????????????????????????????
     */
    @RequiresPermissions("capital:capitalPayLog:export")
    @Log(title = "??????????????????", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CapitalPayLog capitalPayLog)
    {
        List<CapitalPayLog> list = capitalPayLogService.selectCapitalPayLogList(capitalPayLog);
        ExcelUtil<CapitalPayLog> util = new ExcelUtil<CapitalPayLog>(CapitalPayLog.class);
        return util.exportExcel(list, "????????????????????????");
    }

    /**
     * ????????????????????????
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        CapitalAccInfo capitalAccInfo = new CapitalAccInfo();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
               //????????????????????????
                capitalAccInfo.setMerId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==2) {

            }else if(sysUser.getType().intValue()==4) {
                capitalAccInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
//        capitalAccInfo.setStatus(1);
        capitalAccInfo.setType(1);
        List<CapitalAccInfo> merAccList= capitalAccInfoService.selectCapitalAccInfoList(capitalAccInfo);
        capitalAccInfo.setType(3);
        List<CapitalAccInfo> companyAccList= capitalAccInfoService.selectCapitalAccInfoList(capitalAccInfo);
        mmap.put("merAccList",merAccList);
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
        capitalAccInfo.setType(3);
        //???????????????????????????

        List<CapitalAccInfo> channelAccList= capitalAccInfoService.selectCapitalAccInfoList(capitalAccInfo);
        return channelAccList;
    }
    /**
     * ??????????????????????????????
     */
    @RequiresPermissions("capital:capitalPayLog:add")
    @Log(title = "??????????????????", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CapitalPayLog capitalPayLog)
    {
        if(capitalPayLog.getPayCapitalAccId()==null){
            return error("?????????????????????");
        }
        if(capitalPayLog.getPayCapitalAccId()==null){
            return error("?????????????????????");
        }
        if(capitalPayLog.getAmt()==null){
            return error("???????????????");
        }
        CapitalAccInfo recAccInfo=capitalAccInfoService.selectCapitalAccInfoById(capitalPayLog.getRecCapitalAccId());
        CapitalAccInfo payAccInfo=capitalAccInfoService.selectCapitalAccInfoById(capitalPayLog.getPayCapitalAccId());
        capitalPayLog.setCompanyId(recAccInfo.getCompanyId());
        capitalPayLog.setCompanyName(recAccInfo.getCompanyName());
//        capitalPayLog.setChannelId(recAccInfo.getChannelId());
//        capitalPayLog.setChannelName(recAccInfo.getChannelName());
        capitalPayLog.setRecCapitalAccNo(recAccInfo.getCapitalAccNo());
        capitalPayLog.setRecCapitalAccName(recAccInfo.getCapitalAccName());
        capitalPayLog.setMerId(payAccInfo.getMerId());
        capitalPayLog.setMerName(payAccInfo.getMerName());
        capitalPayLog.setPayCapitalAccNo(payAccInfo.getCapitalAccNo());
        capitalPayLog.setPayCapitalAccName(payAccInfo.getCapitalAccName());
        capitalPayLog.setCreateTime(new Date());
        capitalPayLog.setType(1);
        capitalPayLog.setStatus(0);
        return toAjax(capitalPayLogService.insertCapitalPayLog(capitalPayLog));
    }

    /**
     * ????????????????????????
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CapitalPayLog capitalPayLog = capitalPayLogService.selectCapitalPayLogById(id);
        mmap.put("capitalPayLog", capitalPayLog);
        return prefix + "/edit";
    }

    /**
     * ??????????????????????????????
     */
    @RequiresPermissions("capital:capitalPayLog:edit")
    @Log(title = "??????????????????", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CapitalPayLog capitalPayLog)
    {
        return toAjax(capitalPayLogService.updateCapitalPayLog(capitalPayLog));
    }

    /**
     * ????????????????????????
     */
    @RequiresPermissions("capital:capitalPayLog:remove")
    @Log(title = "??????????????????", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(capitalPayLogService.deleteCapitalPayLogByIds(ids));
    }
}
