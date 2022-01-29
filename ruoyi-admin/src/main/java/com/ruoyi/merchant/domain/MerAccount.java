package com.ruoyi.merchant.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商户账户对象 mer_account
 * 
 * @author ruoyi
 * @date 2021-05-03
 */
public class MerAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 商户ID */
    @Excel(name = "商户ID")
    private Long merId;

    /** 授信总金额，单位：分 */
    @Excel(name = "授信总金额，单位：分")
    private Long creditAmt;

    /** 已使用授信额度，单位：分 */
    @Excel(name = "已使用授信额度，单位：分")
    private Long creditUseAmt;

    /** 授信余额，单位：分 */
    @Excel(name = "授信余额，单位：分")
    private Long creditBalanceAmt;

    /** 处理中授信资金，单位：分 */
    @Excel(name = "处理中授信资金，单位：分")
    private Long creditFixAmt;

    /** 账户状态，1：可用；0：停用 */
    @Excel(name = "账户状态，1：可用；0：停用")
    private Integer status;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    private String merName;
    
    
    public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMerId(Long merId) 
    {
        this.merId = merId;
    }

    public Long getMerId() 
    {
        return merId;
    }
    public void setCreditAmt(Long creditAmt) 
    {
        this.creditAmt = creditAmt;
    }

    public Long getCreditAmt() 
    {
        return creditAmt;
    }
    public void setCreditUseAmt(Long creditUseAmt) 
    {
        this.creditUseAmt = creditUseAmt;
    }

    public Long getCreditUseAmt() 
    {
        return creditUseAmt;
    }
    public void setCreditBalanceAmt(Long creditBalanceAmt) 
    {
        this.creditBalanceAmt = creditBalanceAmt;
    }

    public Long getCreditBalanceAmt() 
    {
        return creditBalanceAmt;
    }
    public void setCreditFixAmt(Long creditFixAmt) 
    {
        this.creditFixAmt = creditFixAmt;
    }

    public Long getCreditFixAmt() 
    {
        return creditFixAmt;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merId", getMerId())
            .append("creditAmt", getCreditAmt())
            .append("creditUseAmt", getCreditUseAmt())
            .append("creditBalanceAmt", getCreditBalanceAmt())
            .append("creditFixAmt", getCreditFixAmt())
            .append("status", getStatus())
            .append("version", getVersion())
            .toString();
    }
}
