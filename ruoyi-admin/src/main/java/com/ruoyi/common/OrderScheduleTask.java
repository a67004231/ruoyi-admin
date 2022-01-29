package com.ruoyi.common;


import com.ruoyi.capital.domain.CapitalInfo;
import com.ruoyi.capital.domain.CapitalInfoLog;
import com.ruoyi.capital.service.ICapitalInfoLogService;
import com.ruoyi.capital.service.ICapitalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Configuration      //主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 开启定时任务
@EnableAsync        // 开启多线程
public class OrderScheduleTask {


	@Autowired
	private ICapitalInfoService capitalInfoService;
	@Autowired
	private ICapitalInfoLogService capitalInfoLogService;
    /**
     * 每10秒通知一次商户
	 * @author Liang
     */
    @Async
//    @Scheduled(fixedRate=100000)
    public void orderNoticeTask() {
    	CapitalInfo capitalInfo = new CapitalInfo();
		List<CapitalInfo> list = capitalInfoService.selectCapitalInfoList(capitalInfo);
		for(CapitalInfo info:list){
			CapitalInfoLog log = new CapitalInfoLog();
			log.setChannelId(info.getChannelId());
			log.setChannelName(info.getChannelName());
			log.setLogDate("");
			log.setMerId(info.getMerId());
			log.setMerName(info.getMerName());
			log.setPaidAmt(info.getPaidAmt());
			log.setSuccessAmt(info.getSuccessAmt());
			log.setUnpaidAmt(info.getUnpaidAmt());
			log.setCreateTime(new Date());
			capitalInfoLogService.insertCapitalInfoLog(log);
		}
    }

}
