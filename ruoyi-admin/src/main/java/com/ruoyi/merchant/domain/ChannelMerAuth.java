package com.ruoyi.merchant.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 上下游关联对象 channel_mer_auth
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
public class ChannelMerAuth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 通道ID */
    @Excel(name = "通道ID")
    private Long channelId;

    /** 商户ID */
    @Excel(name = "商户ID")
    private Long merId;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;

    /** 商户名称 */
    @Excel(name = "商户名称")
    private String merName;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 通道名称 */
    @Excel(name = "通道名称")
    private String channelName;

    /**
     * 授信总金额，
     */
    private Long creditAmt;

    /**
     * 已使用授信额度，
     */
    private Long creditUseAmt;

    /**
     * 授信余额，
     */
    private Long creditBalanceAmt;

    /**
     * 处理中授信资金，
     */
    private Long creditFixAmt;
    /**公司ID**/
    private Long companyId;
    /**公司名**/
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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    
    public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getMerId() {
		return merId;
	}

	public void setMerId(Long merId) {
		this.merId = merId;
	}

	public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }
    public void setMerName(String merName) 
    {
        this.merName = merName;
    }

    public String getMerName() 
    {
        return merName;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setChannelName(String channelName) 
    {
        this.channelName = channelName;
    }

    public String getChannelName() 
    {
        return channelName;
    }

    public Long getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(Long creditAmt) {
        this.creditAmt = creditAmt;
    }

    public Long getCreditUseAmt() {
        return creditUseAmt;
    }

    public void setCreditUseAmt(Long creditUseAmt) {
        this.creditUseAmt = creditUseAmt;
    }

    public Long getCreditBalanceAmt() {
        return creditBalanceAmt;
    }

    public void setCreditBalanceAmt(Long creditBalanceAmt) {
        this.creditBalanceAmt = creditBalanceAmt;
    }

    public Long getCreditFixAmt() {
        return creditFixAmt;
    }

    public void setCreditFixAmt(Long creditFixAmt) {
        this.creditFixAmt = creditFixAmt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelId", getChannelId())
            .append("merId", getMerId())
            .append("channelCode", getChannelCode())
            .append("merName", getMerName())
            .append("status", getStatus())
            .append("channelName", getChannelName())
            .toString();
    }
}
