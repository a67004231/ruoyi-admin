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
import com.ruoyi.channel.domain.ChannelAccount;
import com.ruoyi.channel.service.IChannelAccountService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 通道账户Controller
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
@Controller
@RequestMapping("/channel/account")
public class ChannelAccountController extends BaseController
{
    private String prefix = "channel/account";

    @Autowired
    private IChannelAccountService channelAccountService;

    @RequiresPermissions("channel:account:view")
    @GetMapping()
    public String account()
    {
        return prefix + "/account";
    }

    /**
     * 查询通道账户列表
     */
    @RequiresPermissions("channel:account:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChannelAccount channelAccount)
    {
        startPage();
        List<ChannelAccount> list = channelAccountService.selectChannelAccountList(channelAccount);
        return getDataTable(list);
    }

    /**
     * 导出通道账户列表
     */
    @RequiresPermissions("channel:account:export")
    @Log(title = "通道账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChannelAccount channelAccount)
    {
        List<ChannelAccount> list = channelAccountService.selectChannelAccountList(channelAccount);
        ExcelUtil<ChannelAccount> util = new ExcelUtil<ChannelAccount>(ChannelAccount.class);
        return util.exportExcel(list, "通道账户数据");
    }

    /**
     * 新增通道账户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存通道账户
     */
    @RequiresPermissions("channel:account:add")
    @Log(title = "通道账户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChannelAccount channelAccount)
    {
        return toAjax(channelAccountService.insertChannelAccount(channelAccount));
    }

    /**
     * 修改通道账户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ChannelAccount channelAccount = channelAccountService.selectChannelAccountById(id);
        mmap.put("channelAccount", channelAccount);
        return prefix + "/edit";
    }

    /**
     * 修改保存通道账户
     */
    @RequiresPermissions("channel:account:edit")
    @Log(title = "通道账户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChannelAccount channelAccount)
    {
        return toAjax(channelAccountService.updateChannelAccount(channelAccount));
    }

    /**
     * 删除通道账户
     */
    @RequiresPermissions("channel:account:remove")
    @Log(title = "通道账户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(channelAccountService.deleteChannelAccountByIds(ids));
    }
}
