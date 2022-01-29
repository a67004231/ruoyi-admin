package com.ruoyi.factor.controller;

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
import com.ruoyi.factor.domain.FactorLog;
import com.ruoyi.factor.service.IFactorLogService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 通道路由日志Controller
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
@Controller
@RequestMapping("/factor/factorLog")
public class FactorLogController extends BaseController
{
    private String prefix = "factor/factorLog";

    @Autowired
    private IFactorLogService factorLogService;

    @RequiresPermissions("factor:factorLog:view")
    @GetMapping()
    public String factorLog()
    {
        return prefix + "/factorLog";
    }

    /**
     * 查询通道路由日志列表
     */
    @RequiresPermissions("factor:factorLog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FactorLog factorLog)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        startPage();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                factorLog.setMerId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==2) {
                factorLog.setChannelId(sysUser.getUserTypeId());
            }
        }
        List<FactorLog> list = factorLogService.selectFactorLogList(factorLog);
        return getDataTable(list);
    }

    /**
     * 导出通道路由日志列表
     */
    @RequiresPermissions("factor:factorLog:export")
    @Log(title = "通道路由日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FactorLog factorLog)
    {
        List<FactorLog> list = factorLogService.selectFactorLogList(factorLog);
        ExcelUtil<FactorLog> util = new ExcelUtil<FactorLog>(FactorLog.class);
        return util.exportExcel(list, "通道路由日志数据");
    }

    /**
     * 新增通道路由日志
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存通道路由日志
     */
    @RequiresPermissions("factor:factorLog:add")
    @Log(title = "通道路由日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FactorLog factorLog)
    {
        return toAjax(factorLogService.insertFactorLog(factorLog));
    }

    /**
     * 修改通道路由日志
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FactorLog factorLog = factorLogService.selectFactorLogById(id);
        mmap.put("factorLog", factorLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存通道路由日志
     */
    @RequiresPermissions("factor:factorLog:edit")
    @Log(title = "通道路由日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FactorLog factorLog)
    {
        return toAjax(factorLogService.updateFactorLog(factorLog));
    }

    /**
     * 删除通道路由日志
     */
    @RequiresPermissions("factor:factorLog:remove")
    @Log(title = "通道路由日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(factorLogService.deleteFactorLogByIds(ids));
    }
}
