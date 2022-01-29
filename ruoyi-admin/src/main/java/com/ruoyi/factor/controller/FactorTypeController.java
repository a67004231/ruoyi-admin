package com.ruoyi.factor.controller;

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
import com.ruoyi.factor.domain.FactorType;
import com.ruoyi.factor.service.IFactorTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 路由因子类型Controller
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
@Controller
@RequestMapping("/factor/type")
public class FactorTypeController extends BaseController
{
    private String prefix = "factor/type";

    @Autowired
    private IFactorTypeService factorTypeService;

    @RequiresPermissions("factor:type:view")
    @GetMapping()
    public String type()
    {
        return prefix + "/type";
    }

    /**
     * 查询路由因子类型列表
     */
    @RequiresPermissions("factor:type:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FactorType factorType)
    {
        startPage();
        List<FactorType> list = factorTypeService.selectFactorTypeList(factorType);
        return getDataTable(list);
    }

    /**
     * 导出路由因子类型列表
     */
    @RequiresPermissions("factor:type:export")
    @Log(title = "路由因子类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FactorType factorType)
    {
        List<FactorType> list = factorTypeService.selectFactorTypeList(factorType);
        ExcelUtil<FactorType> util = new ExcelUtil<FactorType>(FactorType.class);
        return util.exportExcel(list, "路由因子类型数据");
    }

    /**
     * 新增路由因子类型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存路由因子类型
     */
    @RequiresPermissions("factor:type:add")
    @Log(title = "路由因子类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FactorType factorType)
    {
        return toAjax(factorTypeService.insertFactorType(factorType));
    }

    /**
     * 修改路由因子类型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FactorType factorType = factorTypeService.selectFactorTypeById(id);
        mmap.put("factorType", factorType);
        return prefix + "/edit";
    }

    /**
     * 修改保存路由因子类型
     */
    @RequiresPermissions("factor:type:edit")
    @Log(title = "路由因子类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FactorType factorType)
    {
        return toAjax(factorTypeService.updateFactorType(factorType));
    }

    /**
     * 删除路由因子类型
     */
    @RequiresPermissions("factor:type:remove")
    @Log(title = "路由因子类型", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(factorTypeService.deleteFactorTypeByIds(ids));
    }
}
