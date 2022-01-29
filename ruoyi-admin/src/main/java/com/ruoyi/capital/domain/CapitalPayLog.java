package com.ruoyi.capital.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资金转账记录对象 capital_pay_log
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
public class CapitalPayLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 通道id */
    @Excel(name = "通道id")
    private Long channelId;

    /** 通道名 */
    @Excel(name = "通道名")
    private String channelName;

    /** 商户id */
    @Excel(name = "商户id")
    private Long merId;

    /** 收款账号id */
    @Excel(name = "收款账号id")
    private Long recCapitalAccId;

    /** 收款账号 */
    @Excel(name = "收款账号")
    private String recCapitalAccNo;

    /** 商户名 */
    @Excel(name = "商户名")
    private String merName;

    /** 收款账号名 */
    @Excel(name = "收款账号名")
    private String recCapitalAccName;

    /** 付款账户id */
    @Excel(name = "付款账户id")
    private Long payCapitalAccId;

    /** 付款账户 */
    @Excel(name = "付款账户")
    private String payCapitalAccNo;

    /** 付款账户名 */
    @Excel(name = "付款账户名")
    private String payCapitalAccName;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amt;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;

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
    /** 类型 */
    @Excel(name = "类型")
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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
    public void setMerId(Long merId)
    {
        this.merId = merId;
    }

    public Long getMerId()
    {
        return merId;
    }
    public void setRecCapitalAccId(Long recCapitalAccId)
    {
        this.recCapitalAccId = recCapitalAccId;
    }

    public Long getRecCapitalAccId()
    {
        return recCapitalAccId;
    }
    public void setRecCapitalAccNo(String recCapitalAccNo)
    {
        this.recCapitalAccNo = recCapitalAccNo;
    }

    public String getRecCapitalAccNo()
    {
        return recCapitalAccNo;
    }
    public void setMerName(String merName)
    {
        this.merName = merName;
    }

    public String getMerName()
    {
        return merName;
    }
    public void setRecCapitalAccName(String recCapitalAccName)
    {
        this.recCapitalAccName = recCapitalAccName;
    }

    public String getRecCapitalAccName()
    {
        return recCapitalAccName;
    }
    public void setPayCapitalAccId(Long payCapitalAccId)
    {
        this.payCapitalAccId = payCapitalAccId;
    }

    public Long getPayCapitalAccId()
    {
        return payCapitalAccId;
    }
    public void setPayCapitalAccNo(String payCapitalAccNo)
    {
        this.payCapitalAccNo = payCapitalAccNo;
    }

    public String getPayCapitalAccNo()
    {
        return payCapitalAccNo;
    }
    public void setPayCapitalAccName(String payCapitalAccName)
    {
        this.payCapitalAccName = payCapitalAccName;
    }

    public String getPayCapitalAccName()
    {
        return payCapitalAccName;
    }
    public void setAmt(BigDecimal amt)
    {
        this.amt = amt;
    }

    public BigDecimal getAmt()
    {
        return amt;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    public Date getAuditTime()
    {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelId", getChannelId())
            .append("channelName", getChannelName())
            .append("merId", getMerId())
            .append("recCapitalAccId", getRecCapitalAccId())
            .append("recCapitalAccNo", getRecCapitalAccNo())
            .append("merName", getMerName())
            .append("recCapitalAccName", getRecCapitalAccName())
            .append("payCapitalAccId", getPayCapitalAccId())
            .append("payCapitalAccNo", getPayCapitalAccNo())
            .append("payCapitalAccName", getPayCapitalAccName())
            .append("amt", getAmt())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("auditTime", getAuditTime())
            .append("status", getStatus())
            .toString();
    }
}
