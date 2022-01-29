package com.ruoyi.company.controller;

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
import com.ruoyi.company.domain.CompanyInfo;
import com.ruoyi.company.service.ICompanyInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公司信息Controller
 * 
 * @author ruoyi
 * @date 2021-08-11
 */
@Controller
@RequestMapping("/company/info")
public class CompanyInfoController extends BaseController
{
    private String prefix = "company/info";

    @Autowired
    private ICompanyInfoService companyInfoService;

    @RequiresPermissions("company:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询公司信息列表
     */
    @RequiresPermissions("company:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CompanyInfo companyInfo)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType().intValue()==4) {
            companyInfo.setId(sysUser.getUserTypeId());
        }
        List<CompanyInfo> list = companyInfoService.selectCompanyInfoList(companyInfo);
        return getDataTable(list);
    }
    /**
     * 公司状态修改
     */
    @Log(title = "公司管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("company:info:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(CompanyInfo companyInfo)
    {

        return toAjax(companyInfoService.changeStatus(companyInfo));
    }
    /**
     * 导出公司信息列表
     */
    @RequiresPermissions("company:info:export")
    @Log(title = "公司信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CompanyInfo companyInfo)
    {
        List<CompanyInfo> list = companyInfoService.selectCompanyInfoList(companyInfo);
        ExcelUtil<CompanyInfo> util = new ExcelUtil<CompanyInfo>(CompanyInfo.class);
        return util.exportExcel(list, "公司信息数据");
    }

    /**
     * 新增公司信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存公司信息
     */
    @RequiresPermissions("company:info:add")
    @Log(title = "公司信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CompanyInfo companyInfo)
    {
        return toAjax(companyInfoService.insertCompanyInfo(companyInfo));
    }

    /**
     * 修改公司信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CompanyInfo companyInfo = companyInfoService.selectCompanyInfoById(id);
        mmap.put("companyInfo", companyInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存公司信息
     */
    @RequiresPermissions("company:info:edit")
    @Log(title = "公司信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CompanyInfo companyInfo)
    {
        return toAjax(companyInfoService.updateCompanyInfo(companyInfo));
    }

    /**
     * 删除公司信息
     */
    @RequiresPermissions("company:info:remove")
    @Log(title = "公司信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(companyInfoService.deleteCompanyInfoByIds(ids));
    }
}
