package com.ruoyi.capital.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.capital.domain.CapitalInfoLog;
import com.ruoyi.capital.domain.CapitalPayLog;
import com.ruoyi.capital.service.CapitalInfoAsync;
import com.ruoyi.capital.service.ICapitalInfoLogService;
import com.ruoyi.capital.service.ICapitalPayLogService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.merchant.domain.ChannelMerAuth;
import com.ruoyi.merchant.service.IChannelMerAuthService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.capital.domain.CapitalInfo;
import com.ruoyi.capital.service.ICapitalInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资金信息Controller
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
@Controller
@RequestMapping("/capital/capitalInfo")
public class CapitalInfoController extends BaseController
{
    private String prefix = "capital/capitalInfo";

    @Autowired
    private ICapitalInfoService capitalInfoService;
    @Autowired
    private IChannelMerAuthService channelMerAuthService;
    @Autowired
    private ICapitalInfoLogService capitalInfoLogService;
    @Autowired
    private ICapitalPayLogService capitalPayLogService;
    @Autowired
    private CapitalInfoAsync capitalInfoAsync;
    @RequiresPermissions("capital:capitalInfo:view")
    @GetMapping("/total")
    public String capitalTotal()
    {
        return prefix + "/capitalTotal";
    }

    /**
     * 查询资金汇总列表
     */
    @RequiresPermissions("capital:capitalInfo:list")
    @PostMapping("/totalList")
    @ResponseBody
    public TableDataInfo total(CapitalInfo capitalInfo)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        startPage();
        //根据登录类型查询数据
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                capitalInfo.setMerId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==2) {
                capitalInfo.setChannelId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==4) {
                capitalInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<CapitalInfo> list = capitalInfoService.selectCapitalInfoList(capitalInfo);

        return getDataTable(list);
    }

    @RequiresPermissions("capital:capitalInfo:view")
    @GetMapping()
    public String capitalInfo()
    {
        return prefix + "/capitalInfo";
    }

    /**
     * 查询资金信息列表
     */
    @RequiresPermissions("capital:capitalInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CapitalInfo capitalInfo)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        startPage();
        //根据登录类型查询数据
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                capitalInfo.setMerId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==2) {
                capitalInfo.setChannelId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==4) {
                capitalInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<CapitalInfo> list = capitalInfoService.selectCapitalInfoToday(capitalInfo);
        return getDataTable(list);
    }
    @RequiresPermissions("capital:capitalInfo:view")
    @GetMapping("/profit")
    public String profit(ModelMap mmap)
    {
        String format = DateUtil.format(new Date(), "yyyy-MM-dd");
        mmap.put("startTime", format+" 00:00:00");
        mmap.put("endTime", format+" 23:59:59");
        return prefix + "/capitalProfit";

    }
    /**
     * 查询资金利润汇总
     */
    @RequiresPermissions("capital:capitalInfo:profit")
    @PostMapping("/profitList")
    @ResponseBody
    public TableDataInfo profitList(CapitalInfo capitalInfo)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        startPage();
        //根据登录类型查询数据
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                capitalInfo.setMerId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==2) {
                capitalInfo.setChannelId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==4) {
                capitalInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<CapitalInfo> list= capitalInfoService.selectCapitalInfoList(capitalInfo);
        return getDataTable(list);
    }
    /**
     * 导出资金信息列表
     */
    @RequiresPermissions("capital:capitalInfo:export")
    @Log(title = "资金信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CapitalInfo capitalInfo)
    {
        List<CapitalInfo> list = capitalInfoService.selectCapitalInfoList(capitalInfo);
        ExcelUtil<CapitalInfo> util = new ExcelUtil<CapitalInfo>(CapitalInfo.class);
        return util.exportExcel(list, "资金信息数据");
    }



    /**
     * 查询历史资金
     */
    @RequiresPermissions("capital:capitalInfo:list")
    @GetMapping("/searchLog/{capitalId}")
    public String capitalLog(@PathVariable("capitalId") String capitalId,ModelMap modelMap)
    {
        String[] arry = capitalId.split("-");
        CapitalInfo capitalInfo = new CapitalInfo();
        capitalInfo.setMerId(Long.valueOf(arry[0]));
        capitalInfo.setCompanyId(Long.valueOf(arry[1]));

//        CapitalInfo capitalInfo = capitalInfoService.selectCapitalInfoById(capitalId);
        modelMap.put("capitalInfo",capitalInfo);
        return  prefix +"/capitalLog";
    }
    /**
     * 查询转账记录
     */
    @RequiresPermissions("capital:capitalInfo:list")
    @GetMapping("/searchPayLog/{capitalId}")
    public String searchPayLog(@PathVariable("capitalId") String capitalId,ModelMap modelMap)
    {
        String[] arry = capitalId.split("-");
        CapitalInfo capitalInfo = new CapitalInfo();
        capitalInfo.setMerId(Long.valueOf(arry[0]));
        capitalInfo.setCompanyId(Long.valueOf(arry[1]));
//        CapitalInfo capitalInfo = capitalInfoService.selectCapitalInfoById(capitalId);
        modelMap.put("capitalInfo",capitalInfo);
        return  prefix +"/capitalPayLog";
    }

}
