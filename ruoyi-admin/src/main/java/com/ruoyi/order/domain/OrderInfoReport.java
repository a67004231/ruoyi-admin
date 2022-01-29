package com.ruoyi.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充值通道订单对象 order_info
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
public class OrderInfoReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String date;
    private int count;
    private String serviceName;
    private Long amt;
    private Long sumAmt;
    private Long sucAmt;
    private Long sucCount;
    private String rate;
    private BigDecimal sucFee;
    private String merName;
    private String channelName;
	private BigDecimal sysAmt;
	private Long merId;
	private Long channelId;
	private Long timeConsuming;

	
	
	public Long getTimeConsuming() {
		return timeConsuming;
	}

	public void setTimeConsuming(Long timeConsuming) {
		this.timeConsuming = timeConsuming;
	}

	public Long getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(Long sumAmt) {
		this.sumAmt = sumAmt;
	}

	public Long getMerId() {
		return merId;
	}

	public void setMerId(Long merId) {
		this.merId = merId;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public BigDecimal getSucFee() {
		return sucFee;
	}
	public void setSucFee(BigDecimal sucFee) {
		this.sucFee = sucFee;
	}
	public BigDecimal getSysAmt() {
		return sysAmt;
	}
	public void setSysAmt(BigDecimal sysAmt) {
		this.sysAmt = sysAmt;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Long getSucAmt() {
		return sucAmt;
	}
	public void setSucAmt(Long sucAmt) {
		this.sucAmt = sucAmt;
	}
	public Long getSucCount() {
		return sucCount;
	}
	public void setSucCount(Long sucCount) {
		this.sucCount = sucCount;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getAmt() {
		return amt;
	}
	public void setAmt(Long amt) {
		this.amt = amt;
	}
    
    
}
