package com.ruoyi.channel.controller;

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
import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.domain.ChannelStart;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.channel.service.IChannelStartService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 通道初始化Controller
 * 
 * @author ruoyi
 * @date 2021-05-10
 */
@Controller
@RequestMapping("/start/info")
public class ChannelStartController extends BaseController
{
    private String prefix = "start/info";
    @Autowired
    private IChannelInfoService channelInfoService;
    @Autowired
    private IMerInfoService merInfoService;
    @Autowired
    private IChannelStartService channelStartService;

    @RequiresPermissions("start:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询通道初始化列表
     */
    @RequiresPermissions("start:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChannelStart channelStart)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                //不处理
            }else if(sysUser.getType().intValue()==4) {
                channelStart.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<ChannelStart> list = channelStartService.selectChannelStartList(channelStart);
        return getDataTable(list);
    }
    /**
     * 启动全通道初始化
     */
    @RequiresPermissions("start:info:add")
    @Log(title = "启动全通道初始化", businessType = BusinessType.UPDATE)
    @PostMapping( "/restartAll")
    @ResponseBody
    public AjaxResult restartAll(String ids)
    {
        return toAjax(channelStartService.restartAll());
    }
    /**
     * 停全通道初始化
     */
    @RequiresPermissions("start:info:add")
    @Log(title = "停全通道初始化", businessType = BusinessType.UPDATE)
    @PostMapping( "/stopAll")
    @ResponseBody
    public AjaxResult stopAll(String ids)
    {
        return toAjax(channelStartService.stopAll());
    }
    /**
     * 重启通道初始化
     */
    @RequiresPermissions("start:info:edit")
    @Log(title = "重启通道初始化", businessType = BusinessType.UPDATE)
    @GetMapping( "/restartSelect/{ids}")
    public String restartSelect(@PathVariable("ids") String ids,ModelMap mmap)
    {
        mmap.put("ids", ids);
        return prefix + "/start";
    }
    /**
     * 重启通道初始化
     */
    @RequiresPermissions("start:info:edit")
    @Log(title = "重启通道初始化", businessType = BusinessType.UPDATE)
    @PostMapping( "/editSelect")
    @ResponseBody
    public AjaxResult editSelect(ChannelStart channelStart)
    {
        String ids =  channelStart.getChannelCode();
        channelStart.setChannelCode(null);
        String[] idArry = ids.split(",");
        int num = 0;
        for(int i=0;i<idArry.length;i++){
            channelStart.setId(Long.valueOf(idArry[i]));
            num+=channelStartService.updateChannelStart(channelStart);
        }
        return toAjax(num);
    }
    /**
     * 导出通道初始化列表
     */
    @RequiresPermissions("start:info:export")
    @Log(title = "通道初始化", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChannelStart channelStart)
    {
        List<ChannelStart> list = channelStartService.selectChannelStartList(channelStart);
        ExcelUtil<ChannelStart> util = new ExcelUtil<ChannelStart>(ChannelStart.class);
        return util.exportExcel(list, "通道初始化数据");
    }

    /**
     * 新增通道初始化
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	MerInfo merInfo=new MerInfo();
//    	merInfo.setStatus(1);
    	List<MerInfo> merInfoList = merInfoService.selectMerInfoList(merInfo);
    	
    	ChannelInfo channelInfo=new ChannelInfo();
//    	channelInfo.setStatus(1);
    	List<ChannelInfo> infoList = channelInfoService.selectChannelInfoList(channelInfo);
    	mmap.put("merInfoList", merInfoList);
    	mmap.put("infoList", infoList);
        return prefix + "/add";
    }

    /**
     * 新增保存通道初始化
     */
    @RequiresPermissions("start:info:add")
    @Log(title = "通道初始化", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChannelStart channelStart)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==4) {
                channelStart.setCompanyId(sysUser.getUserTypeId());
            }
        }
        ChannelInfo channelInfo = channelInfoService.selectChannelInfoById(channelStart.getChannelId());
        channelStart.setChannelCode(channelInfo.getChannelCode());
        channelStart.setChannelName(channelInfo.getChannelName());
        return toAjax(channelStartService.insertChannelStart(channelStart));
    }

    /**
     * 修改通道初始化
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ChannelStart channelStart = channelStartService.selectChannelStartById(id);
        mmap.put("channelStart", channelStart);
        return prefix + "/edit";
    }

    /**
     * 修改保存通道初始化
     */
    @RequiresPermissions("start:info:edit")
    @Log(title = "通道初始化", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChannelStart channelStart)
    {
        //转化成分
        return toAjax(channelStartService.updateChannelStart(channelStart));
    }

    /**
     * 删除通道初始化
     */
    @RequiresPermissions("start:info:remove")
    @Log(title = "通道初始化", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(channelStartService.deleteChannelStartByIds(ids));
    }
}
