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
import com.ruoyi.channel.domain.ChannelAccountLog;
import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.service.IChannelAccountLogService;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 通道账户变更记录Controller
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
@Controller
@RequestMapping("/channel/log")
public class ChannelAccountLogController extends BaseController
{
    private String prefix = "channel/log";
    @Autowired
    private IChannelInfoService channelInfoService;
    @Autowired
    private IChannelAccountLogService channelAccountLogService;

    @RequiresPermissions("channel:log:view")
    @GetMapping()
    public String log(ModelMap mmap)
    {
    	SysUser sysUser = ShiroUtils.getSysUser();
    	ChannelInfo channelInfo=new ChannelInfo();
//    	channelInfo.setStatus(1);
    	List<ChannelInfo> infoList = channelInfoService.selectChannelInfoList(channelInfo);
    	mmap.put("type",sysUser.getType());
    	mmap.put("infoList", infoList);
        return prefix + "/log";
    }

    /**
     * 查询通道账户变更记录列表
     */
    @RequiresPermissions("channel:log:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChannelAccountLog channelAccountLog)
    {
        startPage();
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==2) {
            	channelAccountLog.setChannelId(sysUser.getUserTypeId());
            }
        }
        List<ChannelAccountLog> list = channelAccountLogService.selectChannelAccountLogList(channelAccountLog);
        return getDataTable(list);
    }

    /**
     * 导出通道账户变更记录列表
     */
    @RequiresPermissions("channel:log:export")
    @Log(title = "通道账户变更记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChannelAccountLog channelAccountLog)
    {
        List<ChannelAccountLog> list = channelAccountLogService.selectChannelAccountLogList(channelAccountLog);
        ExcelUtil<ChannelAccountLog> util = new ExcelUtil<ChannelAccountLog>(ChannelAccountLog.class);
        return util.exportExcel(list, "通道账户变更记录数据");
    }

    /**
     * 新增通道账户变更记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存通道账户变更记录
     */
    @RequiresPermissions("channel:log:add")
    @Log(title = "通道账户变更记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChannelAccountLog channelAccountLog)
    {
        return toAjax(channelAccountLogService.insertChannelAccountLog(channelAccountLog));
    }

    /**
     * 修改通道账户变更记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ChannelAccountLog channelAccountLog = channelAccountLogService.selectChannelAccountLogById(id);
        mmap.put("channelAccountLog", channelAccountLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存通道账户变更记录
     */
    @RequiresPermissions("channel:log:edit")
    @Log(title = "通道账户变更记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChannelAccountLog channelAccountLog)
    {
        return toAjax(channelAccountLogService.updateChannelAccountLog(channelAccountLog));
    }

    /**
     * 删除通道账户变更记录
     */
    @RequiresPermissions("channel:log:remove")
    @Log(title = "通道账户变更记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(channelAccountLogService.deleteChannelAccountLogByIds(ids));
    }
}
