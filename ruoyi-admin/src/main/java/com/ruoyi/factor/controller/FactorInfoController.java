package com.ruoyi.factor.controller;

import java.util.List;

import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.factor.domain.FactorType;
import com.ruoyi.factor.service.IFactorTypeService;
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
import com.ruoyi.factor.domain.FactorInfo;
import com.ruoyi.factor.service.IFactorInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 路由因子Controller
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
@Controller
@RequestMapping("/factor/info")
public class FactorInfoController extends BaseController
{
    private String prefix = "factor/info";

    @Autowired
    private IFactorInfoService factorInfoService;
    @Autowired
    private IFactorTypeService factorTypeService;
    @Autowired
    private IChannelInfoService channelInfoService;
    @RequiresPermissions("factor:info:view")
    @GetMapping()
    public String info(ModelMap mmap)
    {
        //查询因子类型
        FactorType factorType = new FactorType();
        factorType.setStatus(1);
        List<FactorType> typeList = factorTypeService.selectFactorTypeList(factorType);
        ChannelInfo channelInfo = new ChannelInfo();
        List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
        mmap.put("typeList",typeList);
        mmap.put("channelList",channelList);
        return prefix + "/info";
    }

    /**
     * 查询路由因子列表
     */
    @RequiresPermissions("factor:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FactorInfo factorInfo)
    {
        startPage();
        List<FactorInfo> list = factorInfoService.selectFactorInfoList(factorInfo);
        return getDataTable(list);
    }

    /**
     * 导出路由因子列表
     */
    @RequiresPermissions("factor:info:export")
    @Log(title = "路由因子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FactorInfo factorInfo)
    {
        List<FactorInfo> list = factorInfoService.selectFactorInfoList(factorInfo);
        ExcelUtil<FactorInfo> util = new ExcelUtil<FactorInfo>(FactorInfo.class);
        return util.exportExcel(list, "路由因子数据");
    }

    /**
     * 新增路由因子
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        //查询因子类型
        FactorType factorType = new FactorType();
        factorType.setStatus(1);
        List<FactorType> typeList = factorTypeService.selectFactorTypeList(factorType);
        ChannelInfo channelInfo = new ChannelInfo();
        List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
        mmap.put("typeList",typeList);
        mmap.put("channelList",channelList);
        return prefix + "/add";
    }

    /**
     * 新增保存路由因子
     */
    @RequiresPermissions("factor:info:add")
    @Log(title = "路由因子", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FactorInfo factorInfo)
    {
        if(factorInfo.getChannelId()!=null){
            ChannelInfo channelInfo = channelInfoService.selectChannelInfoById(factorInfo.getChannelId());
            factorInfo.setChannelCode(channelInfo.getChannelCode());
        }
        return toAjax(factorInfoService.insertFactorInfo(factorInfo));
    }

    /**
     * 修改路由因子
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FactorInfo factorInfo = factorInfoService.selectFactorInfoById(id);
        mmap.put("factorInfo", factorInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存路由因子
     */
    @RequiresPermissions("factor:info:edit")
    @Log(title = "路由因子", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FactorInfo factorInfo)
    {
        return toAjax(factorInfoService.updateFactorInfo(factorInfo));
    }

    /**
     * 删除路由因子
     */
    @RequiresPermissions("factor:info:remove")
    @Log(title = "路由因子", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(factorInfoService.deleteFactorInfoByIds(ids));
    }
}
