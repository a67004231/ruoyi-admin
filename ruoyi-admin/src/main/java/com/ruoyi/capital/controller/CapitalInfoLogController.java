package com.ruoyi.capital.controller;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.capital.domain.CapitalInfoLog;
import com.ruoyi.capital.service.ICapitalInfoLogService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资金日志Controller
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
@Controller
@RequestMapping("/capital/capitalLog")
public class CapitalInfoLogController extends BaseController
{
    private String prefix = "capital/capitalLog";

    @Autowired
    private ICapitalInfoLogService capitalInfoLogService;

    @RequiresPermissions("capital:capitalLog:view")
    @GetMapping()
    public String capitalLog()
    {
        return prefix + "/capitalLog";
    }

    /**
     * 查询资金日志列表
     */
    @RequiresPermissions("capital:capitalLog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CapitalInfoLog capitalInfoLog)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                capitalInfoLog.setMerId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==2) {
                capitalInfoLog.setChannelId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==4) {
                capitalInfoLog.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<CapitalInfoLog> list = capitalInfoLogService.selectCapitalInfoLogList(capitalInfoLog);
        return getDataTable(list);
    }
    /**
     * 根据商户和通道查询资金日志列表
     */
    @RequiresPermissions("capital:capitalInfo:list")
    @PostMapping("/listLog")
    @ResponseBody
    public TableDataInfo listLog(CapitalInfoLog capitalInfoLog)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                capitalInfoLog.setMerId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==2) {
                capitalInfoLog.setChannelId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==4) {
                capitalInfoLog.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<CapitalInfoLog> list = capitalInfoLogService.selectCapitalInfoLogList(capitalInfoLog);
        return getDataTable(list);
    }
    /**
     * 导出资金日志列表
     */
    @RequiresPermissions("capital:capitalLog:export")
    @Log(title = "资金日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CapitalInfoLog capitalInfoLog)
    {
        List<CapitalInfoLog> list = capitalInfoLogService.selectCapitalInfoLogList(capitalInfoLog);
        ExcelUtil<CapitalInfoLog> util = new ExcelUtil<CapitalInfoLog>(CapitalInfoLog.class);
        return util.exportExcel(list, "资金日志数据");
    }

    /**
     * 新增资金日志
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存资金日志
     */
    @RequiresPermissions("capital:capitalLog:add")
    @Log(title = "资金日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CapitalInfoLog capitalInfoLog)
    {
        return toAjax(capitalInfoLogService.insertCapitalInfoLog(capitalInfoLog));
    }

    /**
     * 修改资金日志
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CapitalInfoLog capitalInfoLog = capitalInfoLogService.selectCapitalInfoLogById(id);
        mmap.put("capitalInfoLog", capitalInfoLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存资金日志
     */
    @RequiresPermissions("capital:capitalLog:edit")
    @Log(title = "资金日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CapitalInfoLog capitalInfoLog)
    {
        return toAjax(capitalInfoLogService.updateCapitalInfoLog(capitalInfoLog));
    }

    /**
     * 删除资金日志
     */
    @RequiresPermissions("capital:capitalLog:remove")
    @Log(title = "资金日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(capitalInfoLogService.deleteCapitalInfoLogByIds(ids));
    }
}
