package com.ruoyi.merchant.controller;

import java.util.List;
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
import com.ruoyi.merchant.domain.MerAccountLog;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.service.IMerAccountLogService;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 账户变更记录Controller
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
@Controller
@RequestMapping("/merchant/account")
public class MerAccountLogController extends BaseController
{
    private String prefix = "merchant/account";
    @Autowired
    private IMerInfoService merInfoService;
    @Autowired
    private IMerAccountLogService merAccountLogService;

    @RequiresPermissions("merchant:account:view")
    @GetMapping()
    public String account(ModelMap mmap)
    {
    	SysUser sysUser = ShiroUtils.getSysUser();
    	MerInfo merInfo=new MerInfo();
    	merInfo.setStatus(1);
    	List<MerInfo> merInfoList = merInfoService.selectMerInfoList(merInfo);
    	
    	mmap.put("merInfoList", merInfoList);
    	mmap.put("type",sysUser.getType());
        return prefix + "/account";
    }

    /**
     * 查询账户变更记录列表
     */
    @RequiresPermissions("merchant:account:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MerAccountLog merAccountLog)
    {
    	
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
            	merAccountLog.setMerId(sysUser.getUserTypeId());
            }
        }
        List<MerAccountLog> list = merAccountLogService.selectMerAccountLogList(merAccountLog);
        return getDataTable(list);
    }

    /**
     * 导出账户变更记录列表
     */
    @RequiresPermissions("merchant:account:export")
    @Log(title = "账户变更记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MerAccountLog merAccountLog)
    {
        List<MerAccountLog> list = merAccountLogService.selectMerAccountLogList(merAccountLog);
        ExcelUtil<MerAccountLog> util = new ExcelUtil<MerAccountLog>(MerAccountLog.class);
        return util.exportExcel(list, "账户变更记录数据");
    }

    /**
     * 新增账户变更记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存账户变更记录
     */
    @RequiresPermissions("merchant:account:add")
    @Log(title = "账户变更记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MerAccountLog merAccountLog)
    {
        return toAjax(merAccountLogService.insertMerAccountLog(merAccountLog));
    }

    /**
     * 修改账户变更记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MerAccountLog merAccountLog = merAccountLogService.selectMerAccountLogById(id);
        mmap.put("merAccountLog", merAccountLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存账户变更记录
     */
    @RequiresPermissions("merchant:account:edit")
    @Log(title = "账户变更记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MerAccountLog merAccountLog)
    {
        return toAjax(merAccountLogService.updateMerAccountLog(merAccountLog));
    }

    /**
     * 删除账户变更记录
     */
    @RequiresPermissions("merchant:account:remove")
    @Log(title = "账户变更记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(merAccountLogService.deleteMerAccountLogByIds(ids));
    }
}
