package com.ruoyi.capital.controller;

import java.util.List;

import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.company.domain.CompanyInfo;
import com.ruoyi.company.service.ICompanyInfoService;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.service.IMerInfoService;
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
import com.ruoyi.capital.domain.CapitalAccInfo;
import com.ruoyi.capital.service.ICapitalAccInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * capitalAccInfoController
 * 
 * @author ruoyi
 * @date 2021-05-20
 */
@Controller
@RequestMapping("/capital/accinfo")
public class CapitalAccInfoController extends BaseController
{
    private String prefix = "capital/accinfo";

    @Autowired
    private ICapitalAccInfoService capitalAccInfoService;
    @Autowired
    private IChannelInfoService channelInfoService;
    @Autowired
    private IMerInfoService merInfoService;
    @Autowired
    private ICompanyInfoService companyInfoService;
    @RequiresPermissions("capital:accinfo:view")
    @GetMapping()
    public String accinfo(ModelMap mmap)
    {
        //根据登录类型查询数据
        SysUser sysUser = ShiroUtils.getSysUser();
        ChannelInfo channelInfo=new ChannelInfo();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==4) {
                channelInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
        MerInfo merInfo = new MerInfo();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==4) {
                merInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<MerInfo> merList = merInfoService.selectMerInfoList(merInfo);
        mmap.put("channelList",channelList);
        mmap.put("merList",merList);
        return prefix + "/accinfo";
    }

    /**
     * 查询capitalAccInfo列表
     */
    @RequiresPermissions("capital:accinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CapitalAccInfo capitalAccInfo)
    {
        startPage();
        //根据登录类型查询数据
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                capitalAccInfo.setMerId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==2) {
                capitalAccInfo.setChannelId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==4) {
                capitalAccInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<CapitalAccInfo> list = capitalAccInfoService.selectCapitalAccInfoList(capitalAccInfo);
        return getDataTable(list);
    }

    /**
     * 导出capitalAccInfo列表
     */
    @RequiresPermissions("capital:accinfo:export")
    @Log(title = "capitalAccInfo", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CapitalAccInfo capitalAccInfo)
    {
        List<CapitalAccInfo> list = capitalAccInfoService.selectCapitalAccInfoList(capitalAccInfo);
        ExcelUtil<CapitalAccInfo> util = new ExcelUtil<CapitalAccInfo>(CapitalAccInfo.class);
        return util.exportExcel(list, "capitalAccInfo数据");
    }

    /**
     * 新增capitalAccInfo
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        ChannelInfo channelInfo=new ChannelInfo();
        CompanyInfo companyInfo = new CompanyInfo();
        MerInfo merInfo = new MerInfo();
//        channelInfo.setStatus(1);
//        merInfo.setStatus(1);
//        channelInfo.setStatus(1);
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==4) {
                channelInfo.setCompanyId(sysUser.getUserTypeId());
                merInfo.setCompanyId(sysUser.getUserTypeId());
                companyInfo.setId(sysUser.getUserTypeId());
            }
        }
        List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
        List<MerInfo> merList = merInfoService.selectMerInfoList(merInfo);
        List<CompanyInfo> companyList = companyInfoService.selectCompanyInfoList(companyInfo);
        mmap.put("channelList",channelList);
        mmap.put("merList",merList);
        mmap.put("companyList",companyList);
        return prefix + "/add";
    }

    /**
     * 新增保存capitalAccInfo
     */
    @RequiresPermissions("capital:accinfo:add")
    @Log(title = "capitalAccInfo", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CapitalAccInfo capitalAccInfo)
    {
        if(capitalAccInfo.getType().equals(1)){
            MerInfo merInfo = merInfoService.selectMerInfoById(capitalAccInfo.getMerId());
            capitalAccInfo.setMerName(merInfo.getMerName());
        }
        else if(capitalAccInfo.getType().equals(2)){
            ChannelInfo channelInfo =  channelInfoService.selectChannelInfoById(capitalAccInfo.getChannelId());
            capitalAccInfo.setChannelName(channelInfo.getChannelName());
        }
        else if(capitalAccInfo.getType().equals(3)){
            CompanyInfo companyInfo =  companyInfoService.selectCompanyInfoById(capitalAccInfo.getCompanyId());
            capitalAccInfo.setCompanyName(companyInfo.getCompanyName());
        }

        return toAjax(capitalAccInfoService.insertCapitalAccInfo(capitalAccInfo));
    }

    /**
     * 修改capitalAccInfo
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CapitalAccInfo capitalAccInfo = capitalAccInfoService.selectCapitalAccInfoById(id);
        mmap.put("capitalAccInfo", capitalAccInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存capitalAccInfo
     */
    @RequiresPermissions("capital:accinfo:edit")
    @Log(title = "capitalAccInfo", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CapitalAccInfo capitalAccInfo)
    {
        return toAjax(capitalAccInfoService.updateCapitalAccInfo(capitalAccInfo));
    }

    /**
     * 删除capitalAccInfo
     */
    @RequiresPermissions("capital:accinfo:remove")
    @Log(title = "capitalAccInfo", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(capitalAccInfoService.deleteCapitalAccInfoByIds(ids));
    }
}
