package com.ruoyi.channel.controller;

import java.util.Date;
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
import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.domain.ChannelProductAuth;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.channel.service.IChannelProductAuthService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.product.domain.ProductInfo;
import com.ruoyi.product.mapper.ProductInfoMapper;
import com.ruoyi.product.service.IProductInfoService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 通道产品授权Controller
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Controller
@RequestMapping("/channel/auth")
public class ChannelProductAuthController extends BaseController
{
    private String prefix = "channel/auth";
    @Autowired
    private IChannelInfoService channelInfoService;

    @Autowired
    private IChannelProductAuthService channelProductAuthService;
    @Autowired
    IProductInfoService productService;
    @RequiresPermissions("channel:auth:view")
    @GetMapping()
    public String auth()
    {
        return prefix + "/auth";
    }

    /**
     * 查询通道产品授权列表
     */
    @RequiresPermissions("channel:auth:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChannelProductAuth channelProductAuth)
    {
        startPage();
        List<ChannelProductAuth> list = channelProductAuthService.selectChannelProductAuthList(channelProductAuth);
        return getDataTable(list);
    }

    /**
     * 导出通道产品授权列表
     */
    @RequiresPermissions("channel:auth:export")
    @Log(title = "通道产品授权", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChannelProductAuth channelProductAuth)
    {
        List<ChannelProductAuth> list = channelProductAuthService.selectChannelProductAuthList(channelProductAuth);
        ExcelUtil<ChannelProductAuth> util = new ExcelUtil<ChannelProductAuth>(ChannelProductAuth.class);
        return util.exportExcel(list, "通道产品授权数据");
    }

    /**
     * 新增通道产品授权
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	ChannelInfo channelInfo=new ChannelInfo();
    	channelInfo.setStatus(1);
    	List<ChannelInfo> infoList = channelInfoService.selectChannelInfoList(channelInfo);
    	mmap.put("infoList", infoList);
        return prefix + "/add";
    }

    /**
     * 新增保存通道产品授权
     */
    @RequiresPermissions("channel:auth:add")
    @Log(title = "通道产品授权", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(String productType,String productOne,String productTwo,Long channelId)
    {
    	ProductInfo productInfo =new ProductInfo();
    	ChannelInfo channelInfo = channelInfoService.selectChannelInfoById(channelId);
    	if(!"".equals(productTwo)) {
    		productInfo.setCode(productTwo);
    		List<ProductInfo> infoListForFun = productService.selectProductInfoListForFun(productInfo);
			for (ProductInfo productInfo3 : infoListForFun) {
				ChannelProductAuth channelProductAuth=new ChannelProductAuth();
    	    	channelProductAuth.setChannelId(channelId);
    	    	channelProductAuth.setChannelCode(channelInfo.getChannelCode());
    	    	channelProductAuth.setProductCode(productInfo3.getCode());
    	    	channelProductAuth.setProductId(productInfo3.getId());
    	    	channelProductAuth.setCreateTime(new Date());
    	    	channelProductAuth.setUpdateTime(new Date());
    	    	channelProductAuthService.insertChannelProductAuth(channelProductAuth);
			}
    	}else if(!"".equals(productOne)){
    		productInfo.setCode(productOne);
    		List<ProductInfo> infoListForFun = productService.selectProductInfoListForFun(productInfo);
			for (ProductInfo productInfo3 : infoListForFun) {
				ChannelProductAuth channelProductAuth=new ChannelProductAuth();
    	    	channelProductAuth.setChannelId(channelId);
    	    	channelProductAuth.setChannelCode(channelInfo.getChannelCode());
    	    	channelProductAuth.setProductCode(productInfo3.getCode());
    	    	channelProductAuth.setProductId(productInfo3.getId());
    	    	channelProductAuth.setCreateTime(new Date());
    	    	channelProductAuth.setUpdateTime(new Date());
    	    	channelProductAuthService.insertChannelProductAuth(channelProductAuth);
			}
    	}else {
    		productInfo.setParentCode(productType);
    		List<ProductInfo> productInfoList = productService.selectProductInfoList(productInfo);
    		for (ProductInfo productInfo2 : productInfoList) {
    			List<ProductInfo> infoListForFun = productService.selectProductInfoListForFun(productInfo2);
    			for (ProductInfo productInfo3 : infoListForFun) {
    				ChannelProductAuth channelProductAuth=new ChannelProductAuth();
        	    	channelProductAuth.setChannelId(channelId);
        	    	channelProductAuth.setChannelCode(channelInfo.getChannelCode());
        	    	channelProductAuth.setProductCode(productInfo3.getCode());
        	    	channelProductAuth.setProductId(productInfo3.getId());
        	    	channelProductAuth.setCreateTime(new Date());
        	    	channelProductAuth.setUpdateTime(new Date());
        	    	channelProductAuthService.insertChannelProductAuth(channelProductAuth);
				}
			}
    	}
        return toAjax(1);
    }

    /**
     * 修改通道产品授权
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ChannelProductAuth channelProductAuth = channelProductAuthService.selectChannelProductAuthById(id);
        mmap.put("channelProductAuth", channelProductAuth);
        return prefix + "/edit";
    }

    /**
     * 修改保存通道产品授权
     */
    @RequiresPermissions("channel:auth:edit")
    @Log(title = "通道产品授权", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChannelProductAuth channelProductAuth)
    {
        return toAjax(channelProductAuthService.updateChannelProductAuth(channelProductAuth));
    }

    /**
     * 删除通道产品授权
     */
    @RequiresPermissions("channel:auth:remove")
    @Log(title = "通道产品授权", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(channelProductAuthService.deleteChannelProductAuthByIds(ids));
    }
}
