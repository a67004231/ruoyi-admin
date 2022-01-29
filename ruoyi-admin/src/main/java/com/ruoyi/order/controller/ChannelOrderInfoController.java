package com.ruoyi.order.controller;

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
import com.ruoyi.order.domain.ChannelOrderInfo;
import com.ruoyi.order.service.IChannelOrderInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 通道订单Controller
 * 
 * @author ruoyi
 * @date 2021-06-15
 */
@Controller
@RequestMapping("/channelOrder/info")
public class ChannelOrderInfoController extends BaseController
{
    private String prefix = "channelOrder/info";

    @Autowired
    private IChannelOrderInfoService channelOrderInfoService;

    @RequiresPermissions("channelOrder:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询通道订单列表
     */
    @RequiresPermissions("channelOrder:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChannelOrderInfo channelOrderInfo)
    {
        startPage();
        List<ChannelOrderInfo> list = channelOrderInfoService.selectChannelOrderInfoList(channelOrderInfo);
        return getDataTable(list);
    }

    /**
     * 导出通道订单列表
     */
    @RequiresPermissions("channelOrder:info:export")
    @Log(title = "通道订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChannelOrderInfo channelOrderInfo)
    {
        List<ChannelOrderInfo> list = channelOrderInfoService.selectChannelOrderInfoList(channelOrderInfo);
        ExcelUtil<ChannelOrderInfo> util = new ExcelUtil<ChannelOrderInfo>(ChannelOrderInfo.class);
        return util.exportExcel(list, "通道订单数据");
    }

    /**
     * 新增通道订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存通道订单
     */
    @RequiresPermissions("channelOrder:info:add")
    @Log(title = "通道订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChannelOrderInfo channelOrderInfo)
    {
        return toAjax(channelOrderInfoService.insertChannelOrderInfo(channelOrderInfo));
    }

    /**
     * 修改通道订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ChannelOrderInfo channelOrderInfo = channelOrderInfoService.selectChannelOrderInfoById(id);
        mmap.put("channelOrderInfo", channelOrderInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存通道订单
     */
    @RequiresPermissions("channelOrder:info:edit")
    @Log(title = "通道订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChannelOrderInfo channelOrderInfo)
    {
        return toAjax(channelOrderInfoService.updateChannelOrderInfo(channelOrderInfo));
    }

    /**
     * 删除通道订单
     */
    @RequiresPermissions("channelOrder:info:remove")
    @Log(title = "通道订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(channelOrderInfoService.deleteChannelOrderInfoByIds(ids));
    }
}
