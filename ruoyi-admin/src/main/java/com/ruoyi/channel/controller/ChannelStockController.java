package com.ruoyi.channel.controller;

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
import com.ruoyi.channel.domain.ChannelStock;
import com.ruoyi.channel.service.IChannelStockService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 通道库存信息Controller
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
@Controller
@RequestMapping("/channel/stock")
public class ChannelStockController extends BaseController
{
    private String prefix = "channel/stock";

    @Autowired
    private IChannelStockService channelStockService;

    @RequiresPermissions("channel:stock:view")
    @GetMapping()
    public String stock()
    {
        return prefix + "/stock";
    }

    /**
     * 查询通道库存信息列表
     */
    @RequiresPermissions("channel:stock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChannelStock channelStock)
    {
        startPage();
        List<ChannelStock> list = channelStockService.selectChannelStockList(channelStock);
        return getDataTable(list);
    }

    /**
     * 导出通道库存信息列表
     */
    @RequiresPermissions("channel:stock:export")
    @Log(title = "通道库存信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChannelStock channelStock)
    {
        List<ChannelStock> list = channelStockService.selectChannelStockList(channelStock);
        ExcelUtil<ChannelStock> util = new ExcelUtil<ChannelStock>(ChannelStock.class);
        return util.exportExcel(list, "通道库存信息数据");
    }

    /**
     * 新增通道库存信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存通道库存信息
     */
    @RequiresPermissions("channel:stock:add")
    @Log(title = "通道库存信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChannelStock channelStock)
    {
        return toAjax(channelStockService.insertChannelStock(channelStock));
    }

    /**
     * 修改通道库存信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ChannelStock channelStock = channelStockService.selectChannelStockById(id);
        mmap.put("channelStock", channelStock);
        return prefix + "/edit";
    }

    /**
     * 修改保存通道库存信息
     */
    @RequiresPermissions("channel:stock:edit")
    @Log(title = "通道库存信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChannelStock channelStock)
    {
        return toAjax(channelStockService.updateChannelStock(channelStock));
    }

    /**
     * 删除通道库存信息
     */
    @RequiresPermissions("channel:stock:remove")
    @Log(title = "通道库存信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(channelStockService.deleteChannelStockByIds(ids));
    }
}
