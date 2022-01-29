package com.ruoyi.merchant.controller;

import java.util.Date;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.domain.ChannelProductAuth;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.domain.MerProductAuth;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.merchant.service.IMerProductAuthService;
import com.ruoyi.product.domain.ProductInfo;
import com.ruoyi.product.service.IProductInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商户产品授权Controller
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Controller
@RequestMapping("/merchant/auth")
public class MerProductAuthController extends BaseController
{
    private String prefix = "merchant/auth";
    @Autowired
    IProductInfoService productService;
    @Autowired
    private IChannelInfoService channelInfoService;
    @Autowired
    private IMerInfoService merInfoService;
    @Autowired
    private IMerProductAuthService merProductAuthService;
    

    @RequiresPermissions("merchant:auth:view")
    @GetMapping()
    public String auth()
    {
        return prefix + "/auth";
    }

    /**
     * 查询商户产品授权列表
     */
    @RequiresPermissions("merchant:auth:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MerProductAuth merProductAuth)
    {
        startPage();
        List<MerProductAuth> list = merProductAuthService.selectMerProductAuthList(merProductAuth);
        return getDataTable(list);
    }

    /**
     * 导出商户产品授权列表
     */
    @RequiresPermissions("merchant:auth:export")
    @Log(title = "商户产品授权", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MerProductAuth merProductAuth)
    {
        List<MerProductAuth> list = merProductAuthService.selectMerProductAuthList(merProductAuth);
        ExcelUtil<MerProductAuth> util = new ExcelUtil<MerProductAuth>(MerProductAuth.class);
        return util.exportExcel(list, "商户产品授权数据");
    }

    /**
     * 新增商户产品授权
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	ChannelInfo channelInfo=new ChannelInfo();
    	channelInfo.setStatus(1);
    	List<ChannelInfo> infoList = channelInfoService.selectChannelInfoList(channelInfo);
    	MerInfo merInfo=new MerInfo();
    	merInfo.setStatus(1);
    	List<MerInfo> merInfoList = merInfoService.selectMerInfoList(merInfo);
    	
    	mmap.put("infoList", infoList);
    	mmap.put("merInfoList", merInfoList);
        return prefix + "/add";
    }

    /**
     * 新增保存商户产品授权
     */
    @RequiresPermissions("merchant:auth:add")
    @Log(title = "商户产品授权", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @Transactional
    public AjaxResult addSave(String productType,String productOne,String productTwo,Long channelId,Long merId,String rate)
    {
    	ProductInfo productInfo =new ProductInfo();
    	ChannelInfo channelInfo = channelInfoService.selectChannelInfoById(channelId);
    	MerInfo merInfo = merInfoService.selectMerInfoById(merId);
    	if(!"".equals(productTwo)) {
    		productInfo.setCode(productTwo);
    		List<ProductInfo> infoListForFun = productService.selectProductInfoListForFun(productInfo);
			for (ProductInfo productInfo3 : infoListForFun) {
				MerProductAuth merProductAuth=new MerProductAuth();
				merProductAuth.setMerId(merId);
				merProductAuth.setChannelCode(channelInfo.getChannelCode());
				merProductAuth.setChannelId(channelInfo.getId());
				merProductAuth.setProductCode(productInfo3.getCode());
				merProductAuth.setProductId(productInfo3.getId());
				merProductAuth.setRate(rate);
				merProductAuth.setRateType(1);
				merProductAuthService.insertMerProductAuth(merProductAuth);
			}
    	}else if(!"".equals(productOne)){
    		productInfo.setCode(productOne);
    		List<ProductInfo> infoListForFun = productService.selectProductInfoListForFun(productInfo);
			for (ProductInfo productInfo3 : infoListForFun) {
				MerProductAuth merProductAuth=new MerProductAuth();
				merProductAuth.setMerId(merId);
				merProductAuth.setChannelCode(channelInfo.getChannelCode());
				merProductAuth.setChannelId(channelInfo.getId());
				merProductAuth.setProductCode(productInfo3.getCode());
				merProductAuth.setProductId(productInfo3.getId());
				merProductAuth.setRate(rate);
				merProductAuth.setRateType(1);
				merProductAuthService.insertMerProductAuth(merProductAuth);
			}
    	}else {
    		productInfo.setParentCode(productType);
    		List<ProductInfo> productInfoList = productService.selectProductInfoList(productInfo);
    		for (ProductInfo productInfo2 : productInfoList) {
    			List<ProductInfo> infoListForFun = productService.selectProductInfoListForFun(productInfo2);
    			for (ProductInfo productInfo3 : infoListForFun) {
    				MerProductAuth merProductAuth=new MerProductAuth();
    				merProductAuth.setMerId(merId);
    				merProductAuth.setChannelCode(channelInfo.getChannelCode());
    				merProductAuth.setChannelId(channelInfo.getId());
    				merProductAuth.setProductCode(productInfo3.getCode());
    				merProductAuth.setProductId(productInfo3.getId());
    				merProductAuth.setRate(rate);
    				merProductAuth.setRateType(1);
    				merProductAuthService.insertMerProductAuth(merProductAuth);
				}
			}
    	}
        return toAjax(1);
    }

    /**
     * 修改商户产品授权
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MerProductAuth merProductAuth = merProductAuthService.selectMerProductAuthById(id);
        mmap.put("merProductAuth", merProductAuth);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户产品授权
     */
    @RequiresPermissions("merchant:auth:edit")
    @Log(title = "商户产品授权", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MerProductAuth merProductAuth)
    {
        return toAjax(merProductAuthService.updateMerProductAuth(merProductAuth));
    }

    /**
     * 删除商户产品授权
     */
    @RequiresPermissions("merchant:auth:remove")
    @Log(title = "商户产品授权", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(merProductAuthService.deleteMerProductAuthByIds(ids));
    }
}
