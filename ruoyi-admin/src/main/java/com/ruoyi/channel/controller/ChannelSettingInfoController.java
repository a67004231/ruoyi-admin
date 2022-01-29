package com.ruoyi.channel.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.company.domain.CompanyInfo;
import com.ruoyi.company.service.ICompanyInfoService;
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
import com.ruoyi.channel.domain.ChannelSettingInfo;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.channel.service.IChannelSettingInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 通道账户Controller
 * 
 * @author ruoyi
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/channel/setting")
public class ChannelSettingInfoController extends BaseController
{
	@Autowired
    private IChannelInfoService channelInfoService;
	@Autowired
	private IMerInfoService merInfoService;
    private String prefix = "channel/setting";

    @Autowired
    private IChannelSettingInfoService channelSettingInfoService;
    @Autowired
    private ICompanyInfoService companyInfoService;

    @RequiresPermissions("channel:setting:view")
    @GetMapping()
    public String setting(ModelMap mmap)
    { SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==3){
                ChannelInfo channelInfo = new ChannelInfo();
                List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
                mmap.put("channelList",channelList);
                CompanyInfo companyInfo = new CompanyInfo();
                List<CompanyInfo>  companyList =companyInfoService.selectCompanyInfoList(companyInfo);
                mmap.put("companyList",companyList);
            }else if(sysUser.getType().intValue()==4) {
                ChannelInfo channelInfo = new ChannelInfo();
                channelInfo.setCompanyId(sysUser.getUserTypeId());
                List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
                mmap.put("channelList",channelList);
            }
        }
        mmap.put("type",sysUser.getType());
        return prefix + "/setting";
    }

    /**
     * 查询通道账户列表
     */
    @RequiresPermissions("channel:setting:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChannelSettingInfo channelSettingInfo)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                //不处理
            }else if(sysUser.getType().intValue()==2) {
                channelSettingInfo.setChannelId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==4) {
                channelSettingInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<ChannelSettingInfo> list = channelSettingInfoService.selectChannelSettingInfoList(channelSettingInfo);
        return getDataTable(list);
    }

    /**
     * 导出通道账户列表
     */
    @RequiresPermissions("channel:setting:export")
    @Log(title = "通道账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChannelSettingInfo channelSettingInfo)
    {
        List<ChannelSettingInfo> list = channelSettingInfoService.selectChannelSettingInfoList(channelSettingInfo);
        ExcelUtil<ChannelSettingInfo> util = new ExcelUtil<ChannelSettingInfo>(ChannelSettingInfo.class);
        return util.exportExcel(list, "通道账户数据");
    }

    /**
     * 新增通道账户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==3){
                ChannelInfo channelInfo = new ChannelInfo();
                List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
                mmap.put("channelList",channelList);
                CompanyInfo companyInfo = new CompanyInfo();
                List<CompanyInfo>  companyList =companyInfoService.selectCompanyInfoList(companyInfo);
                mmap.put("companyList",companyList);
            }else if(sysUser.getType().intValue()==4) {
                ChannelInfo channelInfo = new ChannelInfo();
                channelInfo.setCompanyId(sysUser.getUserTypeId());
                List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
                mmap.put("channelList",channelList);
                CompanyInfo companyInfo =companyInfoService.selectCompanyInfoById(sysUser.getUserTypeId());
                List<CompanyInfo>  companyList = new ArrayList<>();
                companyList.add(companyInfo);
                mmap.put("companyList",companyList);
            }
        }
        mmap.put("md5Key",UUID.randomUUID().toString().replace("-",""));
        return prefix + "/add";
    }

    /**
     * 新增保存通道账户
     */
    @RequiresPermissions("channel:setting:add")
    @Log(title = "通道账户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChannelSettingInfo channelSettingInfo)
    {
    	ChannelInfo channelInfo = channelInfoService.selectChannelInfoById(channelSettingInfo.getChannelId());
    	channelSettingInfo.setChannelCode(channelInfo.getChannelCode());
        channelSettingInfo.setChannelName(channelInfo.getChannelName());
        CompanyInfo companyInfo = companyInfoService.selectCompanyInfoById(channelSettingInfo.getCompanyId());
        channelSettingInfo.setCompanyName(companyInfo.getCompanyName());
    	channelSettingInfo.setUpdateTime(new Date());
        return toAjax(channelSettingInfoService.insertChannelSettingInfo(channelSettingInfo));
    }

    /**
     * 修改通道账户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ChannelSettingInfo channelSettingInfo = channelSettingInfoService.selectChannelSettingInfoById(id);
        mmap.put("channelSettingInfo", channelSettingInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存通道账户
     */
    @RequiresPermissions("channel:setting:edit")
    @Log(title = "通道账户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChannelSettingInfo channelSettingInfo)
    {
        return toAjax(channelSettingInfoService.updateChannelSettingInfo(channelSettingInfo));
    }

    /**
     * 删除通道账户
     */
    @RequiresPermissions("channel:setting:remove")
    @Log(title = "通道账户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(channelSettingInfoService.deleteChannelSettingInfoByIds(ids));
    }
    /**
     * 通道状态修改
     */
    @Log(title = "通道管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("channel:setting:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(ChannelSettingInfo channelSettingInfo)
    {

        return toAjax(channelSettingInfoService.changeStatus(channelSettingInfo));
    }
}
