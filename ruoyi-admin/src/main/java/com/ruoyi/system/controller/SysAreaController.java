package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.SysArea;
import com.ruoyi.system.service.ISysAreaService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地区Controller
 * 
 * @author ruoyi
 * @date 2021-08-06
 */
@Controller
@RequestMapping("/system/area")
public class SysAreaController extends BaseController
{
    private String prefix = "system/area";

    @Autowired
    private ISysAreaService sysAreaService;

    @RequiresPermissions("system:area:view")
    @GetMapping()
    public String area()
    {
        return prefix + "/area";
    }

    /**
     * 查询地区列表
     */
    @RequiresPermissions("system:area:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysArea sysArea)
    {
        startPage();
        List<SysArea> list = sysAreaService.selectSysAreaList(sysArea);
        return getDataTable(list);
    }

    /**
     * 导出地区列表
     */
    @RequiresPermissions("system:area:export")
    @Log(title = "地区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysArea sysArea)
    {
        List<SysArea> list = sysAreaService.selectSysAreaList(sysArea);
        ExcelUtil<SysArea> util = new ExcelUtil<SysArea>(SysArea.class);
        return util.exportExcel(list, "地区数据");
    }

    /**
     * 新增地区
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存地区
     */
    @RequiresPermissions("system:area:add")
    @Log(title = "地区", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysArea sysArea)
    {
        return toAjax(sysAreaService.insertSysArea(sysArea));
    }

    /**
     * 修改地区
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysArea sysArea = sysAreaService.selectSysAreaById(id);
        mmap.put("sysArea", sysArea);
        return prefix + "/edit";
    }

    /**
     * 修改保存地区
     */
    @RequiresPermissions("system:area:edit")
    @Log(title = "地区", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysArea sysArea)
    {
        return toAjax(sysAreaService.updateSysArea(sysArea));
    }

    /**
     * 删除地区
     */
    @RequiresPermissions("system:area:remove")
    @Log(title = "地区", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysAreaService.deleteSysAreaByIds(ids));
    }
}
