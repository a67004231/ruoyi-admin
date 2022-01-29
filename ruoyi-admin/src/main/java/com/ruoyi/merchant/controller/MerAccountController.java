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
import com.ruoyi.merchant.domain.MerAccount;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.service.IMerAccountService;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商户账户Controller
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
@Controller
@RequestMapping("/merchant/accountInfo")
public class MerAccountController extends BaseController
{
    private String prefix = "merchant/accountInfo";
    @Autowired
    private IMerInfoService merInfoService;
    @Autowired
    private IMerAccountService merAccountService;

    @RequiresPermissions("merchant:accountInfo:view")
    @GetMapping()
    public String accountInfo(ModelMap mmap)
    {
    	SysUser sysUser = ShiroUtils.getSysUser();
    	MerInfo merInfo=new MerInfo();
    	merInfo.setStatus(1);
    	List<MerInfo> merInfoList = merInfoService.selectMerInfoList(merInfo);
    	
    	mmap.put("merInfoList", merInfoList);
    	mmap.put("type",sysUser.getType());
        return prefix + "/accountInfo";
    }

    /**
     * 查询商户账户列表
     */
    @RequiresPermissions("merchant:accountInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MerAccount merAccount)
    {
    	
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
            	merAccount.setMerId(sysUser.getUserTypeId());
            }
        }
        List<MerAccount> list = merAccountService.selectMerAccountList(merAccount);
        return getDataTable(list);
    }

    /**
     * 导出商户账户列表
     */
    @RequiresPermissions("merchant:accountInfo:export")
    @Log(title = "商户账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MerAccount merAccount)
    {
        List<MerAccount> list = merAccountService.selectMerAccountList(merAccount);
        ExcelUtil<MerAccount> util = new ExcelUtil<MerAccount>(MerAccount.class);
        return util.exportExcel(list, "商户账户数据");
    }

    /**
     * 新增商户账户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商户账户
     */
    @RequiresPermissions("merchant:accountInfo:add")
    @Log(title = "商户账户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MerAccount merAccount)
    {
        return toAjax(merAccountService.insertMerAccount(merAccount));
    }

    /**
     * 修改商户账户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MerAccount merAccount = merAccountService.selectMerAccountById(id);
        mmap.put("merAccount", merAccount);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户账户
     */
    @RequiresPermissions("merchant:accountInfo:edit")
    @Log(title = "商户账户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MerAccount merAccount)
    {
        return toAjax(merAccountService.updateMerAccount(merAccount));
    }

    /**
     * 删除商户账户
     */
    @RequiresPermissions("merchant:accountInfo:remove")
    @Log(title = "商户账户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(merAccountService.deleteMerAccountByIds(ids));
    }
}
