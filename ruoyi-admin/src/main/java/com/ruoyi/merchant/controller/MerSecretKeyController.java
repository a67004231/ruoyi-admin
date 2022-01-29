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
import com.ruoyi.merchant.domain.MerSecretKey;
import com.ruoyi.merchant.service.IMerSecretKeyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商户秘钥管理Controller
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Controller
@RequestMapping("/merchant/key")
public class MerSecretKeyController extends BaseController
{
    private String prefix = "merchant/key";

    @Autowired
    private IMerSecretKeyService merSecretKeyService;

    @RequiresPermissions("merchant:key:view")
    @GetMapping()
    public String key()
    {
        return prefix + "/key";
    }

    /**
     * 查询商户秘钥管理列表
     */
    @RequiresPermissions("merchant:key:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MerSecretKey merSecretKey)
    {
        startPage();
        List<MerSecretKey> list = merSecretKeyService.selectMerSecretKeyList(merSecretKey);
        return getDataTable(list);
    }

    /**
     * 导出商户秘钥管理列表
     */
    @RequiresPermissions("merchant:key:export")
    @Log(title = "商户秘钥管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MerSecretKey merSecretKey)
    {
        List<MerSecretKey> list = merSecretKeyService.selectMerSecretKeyList(merSecretKey);
        ExcelUtil<MerSecretKey> util = new ExcelUtil<MerSecretKey>(MerSecretKey.class);
        return util.exportExcel(list, "商户秘钥管理数据");
    }

    /**
     * 新增商户秘钥管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商户秘钥管理
     */
    @RequiresPermissions("merchant:key:add")
    @Log(title = "商户秘钥管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MerSecretKey merSecretKey)
    {
        return toAjax(merSecretKeyService.insertMerSecretKey(merSecretKey));
    }

    /**
     * 修改商户秘钥管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MerSecretKey merSecretKey = merSecretKeyService.selectMerSecretKeyById(id);
        mmap.put("merSecretKey", merSecretKey);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户秘钥管理
     */
    @RequiresPermissions("merchant:key:edit")
    @Log(title = "商户秘钥管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MerSecretKey merSecretKey)
    {
        return toAjax(merSecretKeyService.updateMerSecretKey(merSecretKey));
    }

    /**
     * 删除商户秘钥管理
     */
    @RequiresPermissions("merchant:key:remove")
    @Log(title = "商户秘钥管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(merSecretKeyService.deleteMerSecretKeyByIds(ids));
    }
}
