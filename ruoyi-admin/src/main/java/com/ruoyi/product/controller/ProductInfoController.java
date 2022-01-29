package com.ruoyi.product.controller;

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
import com.ruoyi.product.domain.ProductInfo;
import com.ruoyi.product.service.IProductInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基础产品Controller
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Controller
@RequestMapping("/product/info")
public class ProductInfoController extends BaseController
{
    private String prefix = "product/info";

    @Autowired
    private IProductInfoService productInfoService;

    @RequiresPermissions("product:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询基础产品列表
     */
    @RequiresPermissions("product:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProductInfo productInfo)
    {
        startPage();
        List<ProductInfo> list = productInfoService.selectProductInfoList(productInfo);
        return getDataTable(list);
    }
    
    @GetMapping("/productOneList")
    @ResponseBody
    public List<ProductInfo> productOneList(String parentCode)
    {
    	if(parentCode.equals("1")) {
    		parentCode="RG_FAST";
    	}else if(parentCode.equals("2")) {
    		parentCode="RG_FIXED";
    	}
    	ProductInfo productInfo=new ProductInfo();
    	productInfo.setParentCode(parentCode);
    	List<ProductInfo> list = productInfoService.selectProductInfoList(productInfo);
    	return list;
    }
    @GetMapping("/productTwoList")
    @ResponseBody
    public List<ProductInfo> productTwoList(String parentOneCode)
    {
    	ProductInfo productInfo=new ProductInfo();
    	productInfo.setParentCode(parentOneCode);
    	List<ProductInfo> list = productInfoService.selectProductInfoList(productInfo);
    	return list;
    }
    @PostMapping("/productThreeList")
    @ResponseBody
    public List<ProductInfo> productThreeList(String parentTwoCode)
    {
    	ProductInfo productInfo=new ProductInfo();
    	productInfo.setParentCode(parentTwoCode);
    	List<ProductInfo> list = productInfoService.selectProductInfoList(productInfo);
    	return list;
    }

    /**
     * 导出基础产品列表
     */
    @RequiresPermissions("product:info:export")
    @Log(title = "基础产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductInfo productInfo)
    {
        List<ProductInfo> list = productInfoService.selectProductInfoList(productInfo);
        ExcelUtil<ProductInfo> util = new ExcelUtil<ProductInfo>(ProductInfo.class);
        return util.exportExcel(list, "基础产品数据");
    }

    /**
     * 新增基础产品
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存基础产品
     */
    @RequiresPermissions("product:info:add")
    @Log(title = "基础产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProductInfo productInfo)
    {
        return toAjax(productInfoService.insertProductInfo(productInfo));
    }

    /**
     * 修改基础产品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProductInfo productInfo = productInfoService.selectProductInfoById(id);
        mmap.put("productInfo", productInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存基础产品
     */
    @RequiresPermissions("product:info:edit")
    @Log(title = "基础产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProductInfo productInfo)
    {
        return toAjax(productInfoService.updateProductInfo(productInfo));
    }

    /**
     * 删除基础产品
     */
    @RequiresPermissions("product:info:remove")
    @Log(title = "基础产品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(productInfoService.deleteProductInfoByIds(ids));
    }
}
