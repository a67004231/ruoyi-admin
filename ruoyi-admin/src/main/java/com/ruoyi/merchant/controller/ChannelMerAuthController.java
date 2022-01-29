package com.ruoyi.merchant.controller;

import java.util.Date;
import java.util.List;

import cn.hutool.json.JSONUtil;
import com.ruoyi.channel.domain.AccountUpdateDto;
import com.ruoyi.channel.domain.ChannelAccount;
import com.ruoyi.channel.service.IChannelAccountLogService;
import com.ruoyi.channel.service.IChannelAccountService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
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

import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.merchant.domain.ChannelMerAuth;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.service.IChannelMerAuthService;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 上下游关联Controller
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
@Controller
@RequestMapping("/merchant/channel")
public class ChannelMerAuthController extends BaseController
{
    private String prefix = "merchant/channel";
    @Autowired
    private IChannelInfoService channelInfoService;
    @Autowired
    private IMerInfoService merInfoService;
    @Autowired
    private IChannelMerAuthService channelMerAuthService;
    @Autowired
    private IChannelAccountLogService channelAccountLogService;
    @Autowired
    private IChannelAccountService channelAccountService;
    @Resource
    RabbitTemplate rabbitTemplate;
    @Value("${order.account.queue}")
    private  String orderAccountQueue;
    @RequiresPermissions("merchant:channel:view")
    @GetMapping()
    public String channel()
    {
        return prefix + "/channel";
    }
    @GetMapping("/addAmount")
    public String addAmount(Long id,ModelMap mmap)
    {
        ChannelMerAuth channelMerAuth = channelMerAuthService.selectChannelMerAuthById(id);
        mmap.put("channelMerAuth", channelMerAuth);
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
            accountDto.setAmt(amount*100);//换算成分
            accountDto.setChannelId(id);
            accountDto.setDesc("后台增加授信额度");
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
        ChannelMerAuth channelMerAuth = channelMerAuthService.selectChannelMerAuthById(id);
        mmap.put("channelMerAuth", channelMerAuth);
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
            accountDto.setAmt(0-amount*100);//换算成分
            accountDto.setChannelId(id);
            accountDto.setDesc("后台减少授信额度");
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
     * 查询上下游关联列表
     */
    @RequiresPermissions("merchant:channel:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChannelMerAuth channelMerAuth)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                channelMerAuth.setMerId(sysUser.getUserTypeId());
            }else if(sysUser.getType().intValue()==2) {
                channelMerAuth.setChannelId(sysUser.getUserTypeId());
            }
        }
        List<ChannelMerAuth> list = channelMerAuthService.selectChannelMerAuthList(channelMerAuth);
        return getDataTable(list);
    }

    /**
     * 导出上下游关联列表
     */
    @RequiresPermissions("merchant:channel:export")
    @Log(title = "上下游关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChannelMerAuth channelMerAuth)
    {
        List<ChannelMerAuth> list = channelMerAuthService.selectChannelMerAuthList(channelMerAuth);
        ExcelUtil<ChannelMerAuth> util = new ExcelUtil<ChannelMerAuth>(ChannelMerAuth.class);
        return util.exportExcel(list, "上下游关联数据");
    }

    /**
     * 新增上下游关联
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	ChannelInfo channelInfo=new ChannelInfo();
        //关联关系与通道是否开启无关，只和是否合作有关
//    	channelInfo.setStatus(1);
    	List<ChannelInfo> infoList = channelInfoService.selectChannelInfoList(channelInfo);
    	MerInfo merInfo=new MerInfo();

    	merInfo.setStatus(1);
    	List<MerInfo> merInfoList = merInfoService.selectMerInfoList(merInfo);
    	
    	mmap.put("infoList", infoList);
    	mmap.put("merInfoList", merInfoList);
        return prefix + "/add";
    }

    /**
     * 新增保存上下游关联
     */
    @RequiresPermissions("merchant:channel:add")
    @Log(title = "上下游关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChannelMerAuth channelMerAuth)
    {
    	//根据选择的通道得到公司信息加入信息
        ChannelInfo channelInfo = channelInfoService.selectChannelInfoById(channelMerAuth.getChannelId());
        channelMerAuth.setChannelId(channelInfo.getCompanyId());
        return toAjax(channelMerAuthService.insertChannelMerAuth(channelMerAuth));
    }

    /**
     * 修改上下游关联
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ChannelMerAuth channelMerAuth = channelMerAuthService.selectChannelMerAuthById(id);
        mmap.put("channelMerAuth", channelMerAuth);
        return prefix + "/edit";
    }

    /**
     * 修改保存上下游关联
     */
    @RequiresPermissions("merchant:channel:edit")
    @Log(title = "上下游关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChannelMerAuth channelMerAuth)
    {
        return toAjax(channelMerAuthService.updateChannelMerAuth(channelMerAuth));
    }

    /**
     * 删除上下游关联
     */
    @RequiresPermissions("merchant:channel:remove")
    @Log(title = "上下游关联", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(channelMerAuthService.deleteChannelMerAuthByIds(ids));
    }

    /**
     * 上下游状态修改
     */
    @Log(title = "上下游管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("merchant:channel:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(ChannelMerAuth channelMerAuth)
    {

        return toAjax(channelMerAuthService.changeStatus(channelMerAuth));
    }
}
