package com.ruoyi.common;


import com.ruoyi.capital.domain.CapitalAccInfo;
import com.ruoyi.capital.domain.CapitalInfo;
import com.ruoyi.capital.domain.CapitalInfoLog;
import com.ruoyi.capital.domain.CapitalPayLog;
import com.ruoyi.capital.service.ICapitalInfoLogService;
import com.ruoyi.capital.service.ICapitalInfoService;
import com.ruoyi.capital.service.ICapitalPayLogService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.merchant.domain.ChannelMerAuth;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.service.IChannelMerAuthService;
import com.ruoyi.merchant.service.IMerInfoService;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration      //主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 开启定时任务
@EnableAsync        // 开启多线程
public class CapitalScheduleTask {


	@Autowired
	private ICapitalInfoService capitalInfoService;
	@Autowired
	private ICapitalInfoLogService capitalInfoLogService;
	@Autowired
	private IMerInfoService merInfoService;
	@Autowired
	private ICapitalPayLogService capitalPayLogService;
	@Autowired
	private IChannelMerAuthService channelMerAuthService;
    /**
     * 每天十二点半执行统计数据写入数据库
	 * @author Liang
     */
    @Async
    @Scheduled(cron="0 30 0 * * ?")
//	@Scheduled(fixedRate=100000)
    public void orderNoticeTask() {
		//查询商户与公司昨日数据
		CapitalInfo capitalInfo  =  new CapitalInfo();
		List<CapitalInfo> list = capitalInfoService.selectCapitalByYes(capitalInfo);
		for(CapitalInfo info:list){
			//保存资金日志
			CapitalInfoLog log = new CapitalInfoLog();
			log.setCompanyId(info.getCompanyId());
			log.setCompanyName(info.getCompanyName());
			log.setLogDate(DateUtils.getYesDate());
			log.setSuccessAmt(info.getSuccessAmt());
			log.setChannelId(info.getChannelId());
			log.setChannelName(info.getChannelName());
			log.setMerId(info.getMerId());
			log.setMerName(info.getMerName());
			log.setPaidAmt(info.getPaidAmt());
			log.setUnpaidAmt(info.getUnpaidAmt());
			log.setCreateTime(new Date());
			log.setStatus(1);
			capitalInfoLogService.insertCapitalInfoLog(log);
			}

    }

}
