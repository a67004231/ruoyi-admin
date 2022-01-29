package com.ruoyi.merchant.controller;

import java.util.List;

import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.company.domain.CompanyInfo;
import com.ruoyi.company.service.ICompanyInfoService;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.system.domain.SysArea;
import com.ruoyi.system.service.ISysAreaService;
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
import com.ruoyi.merchant.domain.MerChannelRoute;
import com.ruoyi.merchant.service.IMerChannelRouteService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商户通道路由Controller
 * 
 * @author ruoyi
 * @date 2021-08-20
 */
@Controller
@RequestMapping("/merchant/route")
public class MerChannelRouteController extends BaseController
{
    private String prefix = "merchant/route";

    @Autowired
    private IMerChannelRouteService merChannelRouteService;
    @Autowired
    private IChannelInfoService channelInfoService;
    @Autowired
    private IMerInfoService merInfoService;
    @Autowired
    private ICompanyInfoService companyInfoService;
    @Autowired
    private ISysAreaService sysAreaService;
    @RequiresPermissions("merchant:route:view")
    @GetMapping()
    public String route()
    {
        return prefix + "/route";
    }

    /**
     * 查询商户通道路由列表
     */
    @RequiresPermissions("merchant:route:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MerChannelRoute merChannelRoute)
    {
        startPage();
        List<MerChannelRoute> list = merChannelRouteService.selectMerChannelRouteList(merChannelRoute);
        return getDataTable(list);
    }

    /**
     * 导出商户通道路由列表
     */
    @RequiresPermissions("merchant:route:export")
    @Log(title = "商户通道路由", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MerChannelRoute merChannelRoute)
    {
        List<MerChannelRoute> list = merChannelRouteService.selectMerChannelRouteList(merChannelRoute);
        ExcelUtil<MerChannelRoute> util = new ExcelUtil<MerChannelRoute>(MerChannelRoute.class);
        return util.exportExcel(list, "商户通道路由数据");
    }

    /**
     * 新增商户通道路由
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
        SysArea sysArea = new SysArea();
        sysArea.setLevel(1L);
        List<SysArea> areaList = sysAreaService.selectSysAreaList(sysArea);
        mmap.put("channelList",channelList);
        mmap.put("merList",merList);
        mmap.put("companyList",companyList);
        mmap.put("areaList",areaList);
        return prefix + "/add";
    }

    /**
     * 新增保存商户通道路由
     */
    @RequiresPermissions("merchant:route:add")
    @Log(title = "商户通道路由", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MerChannelRoute merChannelRoute)
    {
        MerInfo merInfo = merInfoService.selectMerInfoById(merChannelRoute.getMerId());
        ChannelInfo channelInfo = channelInfoService.selectChannelInfoById(merChannelRoute.getChannelId());
        merChannelRoute.setMerNo(merInfo.getMerNo());
        merChannelRoute.setMerName(merInfo.getMerName());
        merChannelRoute.setCompanyId(channelInfo.getCompanyId());
        merChannelRoute.setChannelName(channelInfo.getCompanyName());
        merChannelRoute.setChannelCode(channelInfo.getChannelCode());
        merChannelRoute.setChannelName(channelInfo.getChannelName());
        //
        merChannelRoute.setAreaIds(","+merChannelRoute.getAreaIds()+",");
        merChannelRoute.setOperators(","+merChannelRoute.getOperators()+",");
        merChannelRoute.setHasAmout(","+merChannelRoute.getHasAmout()+",");
        return toAjax(merChannelRouteService.insertMerChannelRoute(merChannelRoute));
    }

    /**
     * 修改商户通道路由
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MerChannelRoute merChannelRoute = merChannelRouteService.selectMerChannelRouteById(id);
        mmap.put("merChannelRoute", merChannelRoute);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户通道路由
     */
    @RequiresPermissions("merchant:route:edit")
    @Log(title = "商户通道路由", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MerChannelRoute merChannelRoute)
    {
        return toAjax(merChannelRouteService.updateMerChannelRoute(merChannelRoute));
    }

    /**
     * 删除商户通道路由
     */
    @RequiresPermissions("merchant:route:remove")
    @Log(title = "商户通道路由", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(merChannelRouteService.deleteMerChannelRouteByIds(ids));
    }
}
