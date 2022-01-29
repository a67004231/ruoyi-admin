//package com.ruoyi.common;
//
//
//import com.ruoyi.capital.domain.CapitalInfo;
//import com.ruoyi.capital.domain.CapitalInfoLog;
//import com.ruoyi.capital.service.ICapitalInfoLogService;
//import com.ruoyi.capital.service.ICapitalInfoService;
//import com.ruoyi.channel.domain.ChannelInfo;
//import com.ruoyi.channel.service.IChannelInfoService;
//import com.ruoyi.common.utils.StringUtils;
//import com.ruoyi.merchant.service.IMerInfoService;
//import org.apache.commons.mail.SimpleEmail;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import javax.annotation.Resource;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
//@Configuration      //主要用于标记配置类，兼备Component的效果。
//@EnableScheduling   // 开启定时任务
//@EnableAsync        // 开启多线程
//public class AccountRiskScheduleTask {
//
//
//	@Autowired
//	private IChannelInfoService channelInfoService;
//	@Autowired
//	private IMerInfoService merInfoService;
//	@Resource
//	private RedisTemplate redisTemplate;
//	@Value("${mail.host}")
//	private  String host;
//	@Value("${mail.username}")
//	private  String username;
//	@Value("${mail.password}")
//	private  String password;
//    @Value("${mail.recevie}")
//    private  String recevie;
//    /**
//     * 每10分钟查询一次通道账户，如果低于总额30%则进行邮件报警
//	 * @author Liang
//     */
//    @Async
//    @Scheduled(fixedRate=600000)
//    public void channelNoticeTask() {
//        ChannelInfo channel = new ChannelInfo();
//        channel.setStatus(1);
//    	List<ChannelInfo> list = channelInfoService.selectChannelInfoList(channel);
//		for(ChannelInfo channelInfo:list){
//			BigDecimal amt = BigDecimal.valueOf(channelInfo.getCreditAmt()).multiply(BigDecimal.valueOf(0.3));
//			BigDecimal balance = BigDecimal.valueOf(channelInfo.getCreditBalanceAmt());
//			if(amt.compareTo(balance)==1){
//				//查询是否发送超过三次,超过三次则不进行
//                int count = 0;
//                if(redisTemplate.hasKey(channelInfo.getChannelCode()+"mail-count")){
//                    String countStr = redisTemplate.opsForValue().get(channelInfo.getChannelCode()+"mail-count").toString();
//                    if(StringUtils.isNotEmpty(countStr)){
//                        count = Integer.valueOf(countStr);
//                    }
//                }
//                if(count>=3){
//                    continue;
//                }
//                //批量发送s
//                String[] mailArry = recevie.split(";");
//                String[] mails = username.split(";");
//                String[] pwds = password.split(";");
//                Random random = new Random();
//                int r =random.nextInt(mails.length);
//                for(String mail:mailArry){
//                    if(StringUtils.isNotEmpty(mail)){
//                        try{
//                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//                            SimpleEmail email = new SimpleEmail();
//                            email.setHostName(host);
//                            email.setSSLOnConnect(true);
//                            System.setProperty("mail.smtp.ssl.enable", "true");
//                            email.setAuthentication(mails[r], pwds[r]);//邮件服务器验证：用户名/密码
//                            email.setCharset("UTF-8");// 必须放在前面，否则乱码
//                            email.addTo(mail);
//                            email.setFrom(mails[r], "后台运营团队");
//                            email.setSubject("后台预警邮件");
//                            StringBuilder msgInfo = new StringBuilder();
//                            msgInfo.append("尊敬的后台管理者您好：").append("\r\n\t");
//                            msgInfo.append("通道【").append(channelInfo.getChannelShortName())
//                                    .append("】的授信余额为").append(channelInfo.getCreditBalanceAmt())
//                                    .append(",不足授信的30%").append("\r\n\t");
//                            msgInfo.append("请在合适的时间进行操作汇款，以免影响该通道的正常运行").append("\r\n\t");
//                            msgInfo.append("发送时间：").append(sdf.format(new Date()));
//                            email.setMsg(msgInfo.toString());
//                            String result = email.send();
//
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//                count++;
//                redisTemplate.opsForValue().set(channelInfo.getChannelCode()+"mail-count",count,1, TimeUnit.DAYS);
//			}
//		}
//    }
//
//}
