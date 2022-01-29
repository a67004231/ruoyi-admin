package com.ruoyi.channel.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import cn.hutool.core.util.StrUtil;
import com.ruoyi.capital.domain.CapitalAccInfo;
import com.ruoyi.capital.dto.AccountUpdateDto;
import com.ruoyi.channel.domain.ChannelAccount;
import com.ruoyi.channel.service.IChannelAccountService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.company.domain.CompanyInfo;
import com.ruoyi.company.service.ICompanyInfoService;
import com.ruoyi.merchant.domain.ChannelMerAuth;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.order.domain.OrderInfo;

import cn.hutool.json.JSONUtil;

import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 通道信息Controller
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
@Controller
@RequestMapping("/channel/info")
public class ChannelInfoController extends BaseController
{
    private String prefix = "channel/info";
    @Resource
    RabbitTemplate rabbitTemplate;
    @Value("${order.account.queue}")
    private  String orderAccountQueue;
    @Autowired
    private IChannelInfoService channelInfoService;
    @Autowired
    private ICompanyInfoService companyInfoService;
    @Autowired
    private IChannelAccountService channelAccountService;


    @RequiresPermissions("channel:info:view")
    @GetMapping()
    public String info(ModelMap mmap)
    {
        CompanyInfo companyInfo =  new CompanyInfo();
        companyInfo.setStatus(1);
        List<CompanyInfo> infoList = companyInfoService.selectCompanyInfoList(companyInfo);
        mmap.put("companyList",infoList);
        return prefix + "/info";
    }
    @GetMapping("/addAmount")
    public String addAmount(Long id,ModelMap mmap)
    {
        ChannelInfo channelInfo = channelInfoService.selectChannelInfoById(id);
        mmap.put("channelInfo", channelInfo);
        return prefix + "/addAmount";
    }
    /**
     * 增加授信额
     */
    @Log(title = "增加授信额", businessType = BusinessType.UPDATE)
    @PostMapping( "/addSaveAmount")
    @ResponseBody
    public String addSaveAmount(Long id,Long amount)
    {
        String rsp="增加授信成功";
        ChannelAccount channelAccount=new ChannelAccount();
        channelAccount.setChannelId(id);
        //处理资金
        List<ChannelAccount> channelAccountList = channelAccountService.selectChannelAccountList(channelAccount);
        if(channelAccountList==null||channelAccountList.size()==0){
            return "资金信息异常";
        }
        channelAccount=channelAccountList.get(0);
        //处理资金
        try {
            AccountUpdateDto accountDto=new AccountUpdateDto();
            accountDto.setType(5);
            accountDto.setAccountType(2);
            accountDto.setSysAmt(amount*100);//换算成分
            accountDto.setChannelId(id);
            accountDto.setDesc("后台增加通道授信额度");
            String no = DateUtils.parseDateToStr("yyyyMMddHHmmss",new Date())+channelAccount.getChannelId();
            accountDto.setOrderId(Long.valueOf(no));
            rabbitTemplate.convertAndSend(orderAccountQueue, JSONUtil.toJsonStr(accountDto));
        } catch (Exception e) {
            e.printStackTrace();
            rsp="增加授信失败";
        }

        return rsp;
    }
    @GetMapping("/reduceAmount")
    public String reduceAmount(Long id,ModelMap mmap)
    {
        ChannelInfo channelInfo = channelInfoService.selectChannelInfoById(id);
        mmap.put("channelInfo", channelInfo);
        return prefix + "/reduceAmount";
    }
    /**
     * 减少授信额
     */
    @Log(title = "减少授信额", businessType = BusinessType.UPDATE)
    @PostMapping( "/reduceSaveAmount")
    @ResponseBody
    public String reduceSaveAmount(Long id,Long amount)
    {
        String rsp="减少授信成功";
        ChannelAccount channelAccount=new ChannelAccount();
        channelAccount.setChannelId(id);
        //处理资金
        List<ChannelAccount> channelAccountList = channelAccountService.selectChannelAccountList(channelAccount);
        if(channelAccountList==null||channelAccountList.size()==0){
            return "资金信息异常";
        }
        channelAccount=channelAccountList.get(0);
        //判断金额
        if(channelAccount.getCreditBalanceAmt()<amount*100){
            return "减少额度不能大于授信余额";
        }
        //处理资金
        try {
            AccountUpdateDto accountDto=new AccountUpdateDto();
            accountDto.setType(5);
            accountDto.setAccountType(2);
            accountDto.setSysAmt(0-amount*100);//换算成分
            accountDto.setChannelId(id);
            accountDto.setDesc("后台减少通道授信额度");
            String no = DateUtils.parseDateToStr("yyyyMMddHHmmss",new Date())+channelAccount.getChannelId();
            accountDto.setOrderId(Long.valueOf(no));
            rabbitTemplate.convertAndSend(orderAccountQueue, JSONUtil.toJsonStr(accountDto));
        } catch (Exception e) {
            e.printStackTrace();
            rsp="减少授信失败";
        }

        return rsp;
    }
    /**
     * 查询通道信息列表
     */
    @RequiresPermissions("channel:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChannelInfo channelInfo)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
              //不处理
            }else if(sysUser.getType().intValue()==2) {
                channelInfo.setId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==4) {
                channelInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<ChannelInfo> list = channelInfoService.selectChannelInfoList(channelInfo);
        return getDataTable(list);
    }

    /**
     * 导出通道信息列表
     */
    @RequiresPermissions("channel:info:export")
    @Log(title = "通道信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChannelInfo channelInfo)
    {
        List<ChannelInfo> list = channelInfoService.selectChannelInfoList(channelInfo);
        ExcelUtil<ChannelInfo> util = new ExcelUtil<ChannelInfo>(ChannelInfo.class);
        return util.exportExcel(list, "通道信息数据");
    }

    /**
     * 新增通道信息
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        CompanyInfo companyInfo =  new CompanyInfo();
        companyInfo.setStatus(1);
        List<CompanyInfo> infoList = companyInfoService.selectCompanyInfoList(companyInfo);
        mmap.put("infoList",infoList);
        return prefix + "/add";
    }

    /**
     * 新增保存通道信息
     */
    @RequiresPermissions("channel:info:add")
    @Log(title = "通道信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChannelInfo channelInfo)
    {
        return toAjax(channelInfoService.insertChannelInfo(channelInfo));
    }

    /**
     * 修改通道信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ChannelInfo channelInfo = channelInfoService.selectChannelInfoById(id);
        mmap.put("channelInfo", channelInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存通道信息
     */
    @RequiresPermissions("channel:info:edit")
    @Log(title = "通道信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChannelInfo channelInfo)
    {
        return toAjax(channelInfoService.updateChannelInfo(channelInfo));
    }

    /**
     * 删除通道信息
     */
    @RequiresPermissions("channel:info:remove")
    @Log(title = "通道信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(channelInfoService.deleteChannelInfoByIds(ids));
    }

    /**
     * 通道状态修改
     */
    @Log(title = "通道管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("channel:info:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(ChannelInfo channelInfo)
    {

        return toAjax(channelInfoService.changeStatus(channelInfo));
    }
    @GetMapping("/channelList")
    @ResponseBody
    public List<ChannelInfo> channelAccList(String companyId)
    {
        if(StrUtil.isBlank(companyId)){
            return null;
        }
        ChannelInfo channelInfo = new ChannelInfo();
        channelInfo.setCompanyId(Long.valueOf(companyId));
        //查询关联关系的通道

        List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
        return channelList;
    }
}
