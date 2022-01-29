package com.ruoyi.capital.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资金信息对象 capital_info
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
public class CapitalInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 今日成功金额 */
    @Excel(name = "今日成功金额")
    private BigDecimal successAmt;

    /** 今日未支付金额 */
    @Excel(name = "今日未支付金额")
    private BigDecimal unpaidAmt;

    /** 今日已支付金额 */
    @Excel(name = "今日已支付金额")
    private BigDecimal paidAmt;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 商户id */
    @Excel(name = "商户id")
    private Long merId;

    /** 商户名称 */
    @Excel(name = "商户名称")
    private String merName;

    /** 通道id */
    @Excel(name = "通道id")
    private Long channelId;

    /** 通道名称 */
    @Excel(name = "通道名称")
    private String channelName;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /** 公司id */
    @Excel(name = "公司id")
    private Long companyId;

    /** 公司名 */
    @Excel(name = "公司名")
    private String companyName;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSuccessAmt(BigDecimal successAmt) 
    {
        this.successAmt = successAmt;
    }

    public BigDecimal getSuccessAmt() 
    {
        return successAmt;
    }
    public void setUnpaidAmt(BigDecimal unpaidAmt) 
    {
        this.unpaidAmt = unpaidAmt;
    }

    public BigDecimal getUnpaidAmt() 
    {
        return unpaidAmt;
    }
    public void setPaidAmt(BigDecimal paidAmt) 
    {
        this.paidAmt = paidAmt;
    }

    public BigDecimal getPaidAmt() 
    {
        return paidAmt;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setMerId(Long merId) 
    {
        this.merId = merId;
    }

    public Long getMerId() 
    {
        return merId;
    }
    public void setMerName(String merName) 
    {
        this.merName = merName;
    }

    public String getMerName() 
    {
        return merName;
    }
    public void setChannelId(Long channelId) 
    {
        this.channelId = channelId;
    }

    public Long getChannelId() 
    {
        return channelId;
    }
    public void setChannelName(String channelName) 
    {
        this.channelName = channelName;
    }

    public String getChannelName() 
    {
        return channelName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("successAmt", getSuccessAmt())
            .append("unpaidAmt", getUnpaidAmt())
            .append("paidAmt", getPaidAmt())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("merId", getMerId())
            .append("merName", getMerName())
            .append("channelId", getChannelId())
            .append("channelName", getChannelName())
            .toString();
    }
}
