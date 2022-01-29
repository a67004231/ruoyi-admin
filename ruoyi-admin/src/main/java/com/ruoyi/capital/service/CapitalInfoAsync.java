package com.ruoyi.capital.service;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.capital.domain.CapitalInfo;
import com.ruoyi.capital.domain.CapitalInfoLog;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.merchant.domain.ChannelMerAuth;
import com.ruoyi.merchant.service.IChannelMerAuthService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 资金信息Controller
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
@Component
public class CapitalInfoAsync
{


    @Autowired
    private ICapitalInfoService capitalInfoService;
    @Autowired
    private IChannelMerAuthService channelMerAuthService;
    @Autowired
    private ICapitalInfoLogService capitalInfoLogService;

    @Async
    @Transactional
    public void saveInfoToday(ChannelMerAuth channelMerAuth) {
        try {
            //查询出所有关联通道
            List<ChannelMerAuth> channelList = channelMerAuthService.selectChannelMerAuthList(channelMerAuth);
            for (ChannelMerAuth channel : channelList) {
                CapitalInfo capitalInfo = new CapitalInfo();
                capitalInfo.setMerId(channel.getMerId());
                capitalInfo.setChannelId(channel.getChannelId());
                //状态1为当日资金，状态2为汇总资金
                capitalInfo.setStatus(1);
                //查询当日商户与通道的资金
                CapitalInfo capital = capitalInfoService.selectCapitalByChannel(capitalInfo);
                //保存或者更新列表,留一条
                List<CapitalInfo> list = capitalInfoService.selectByMerIdAndChannelId(capitalInfo);
                if (list.size()<1) {
                    capital.setStatus(1);
                    capitalInfoService.insertCapitalInfo(capital);
                } else {
                    CapitalInfo saveInfo = list.get(0);
                    if(list.size()>1){
                       for(int i=1;i<list.size();i++){
                           capitalInfoService.deleteCapitalInfoById(list.get(i).getId());
                       }
                    }
                    capital.setId(saveInfo.getId());
                    capital.setStatus(1);
                    capitalInfoService.updateCapitalInfo(capital);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
        @Async
        @Transactional
        public void saveInfoTotal(ChannelMerAuth channelMerAuth){
            try{
                //查询出所有关联通道
                List<ChannelMerAuth> channelList = channelMerAuthService.selectChannelMerAuthList(channelMerAuth);
                for(ChannelMerAuth channel:channelList){
                    CapitalInfo capitalInfo = new CapitalInfo();
                    capitalInfo.setMerId(channel.getMerId());
                    capitalInfo.setChannelId(channel.getChannelId());
                    //状态1为当日资金，状态2为汇总资金
                    capitalInfo.setStatus(2);
                    //查询当日商户与通道的资金
                    CapitalInfo capital= capitalInfoService.selectCapitalByChannel(capitalInfo);
                    //查询历史商户与通道的资金
                    CapitalInfoLog capitalInfoLog = new CapitalInfoLog();
                    capitalInfoLog.setMerId(channel.getMerId());
                    capitalInfoLog.setChannelId(channel.getChannelId());
                    CapitalInfoLog totalCaptial = capitalInfoLogService.selectCapitalTotal(capitalInfoLog);
                    //若无今日数据则返回历史数据
                    //资金相加
                    capital.setSuccessAmt(capital.getSuccessAmt().add(totalCaptial.getSuccessAmt()));
                    capital.setUnpaidAmt(capital.getUnpaidAmt().add(totalCaptial.getUnpaidAmt()));
                    //查询已支付资金合并计入
                    capital.setPaidAmt(capital.getPaidAmt().add(totalCaptial.getPaidAmt()));
                    //保存或者更新列表

                    //保存或者更新列表,留一条
                    List<CapitalInfo> list = capitalInfoService.selectByMerIdAndChannelId(capitalInfo);
                    if (list.size()<1) {
                        capital.setStatus(2);
                        capitalInfoService.insertCapitalInfo(capital);
                    } else {
                        CapitalInfo saveInfo = list.get(0);
                        if(list.size()>1){
                            for(int i=1;i<list.size();i++){
                                capitalInfoService.deleteCapitalInfoById(list.get(i).getId());
                            }
                        }
                        capital.setId(saveInfo.getId());
                        capital.setStatus(2);
                        capitalInfoService.updateCapitalInfo(capital);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

    }

}
