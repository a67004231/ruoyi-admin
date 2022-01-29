package com.ruoyi.merchant.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 账户变更记录对象 mer_account_log
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
public class MerAccountLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账户变更记录ID */
    private Long id;

    /** 商户ID */
    @Excel(name = "商户ID")
    private Long merId;

    /** 资金类型1：授信总余额增加，2：授信总余额减少3：已使用授信金额增加，4：已使用授信金额减少5：可用余额增加 6：可用余额减少7：处理中资金增加 8：处理中资金减少 */
    @Excel(name = "资金类型1：授信总余额增加，2：授信总余额减少3：已使用授信金额增加，4：已使用授信金额减少5：可用余额增加 6：可用余额减少7：处理中资金增加 8：处理中资金减少")
    private Integer type;

    /** 账户ID */
    @Excel(name = "账户ID")
    private Long accountId;

    /** 系统订单号 */
    @Excel(name = "系统订单号")
    private String cxOrderNo;

    /** 变更金额，单位：元 */
    @Excel(name = "变更金额，单位：元")
    private Long changeAmt;

    /** 变更前，账户信息（json串） */
    @Excel(name = "变更前，账户信息", readConverterExp = "j=son串")
    private String changeBef;

    /** 变更后账户信息（json串） */
    @Excel(name = "变更后账户信息", readConverterExp = "j=son串")
    private String changeAft;

    /** 变更备注 */
    @Excel(name = "变更备注")
    private String changeText;
    @Excel(name = "系统订单号")
    private String sysOrderNo;

    
    public String getSysOrderNo() {
		return sysOrderNo;
	}

	public void setSysOrderNo(String sysOrderNo) {
		this.sysOrderNo = sysOrderNo;
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
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setAccountId(Long accountId) 
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
    }
    public void setCxOrderNo(String cxOrderNo) 
    {
        this.cxOrderNo = cxOrderNo;
    }

    public String getCxOrderNo() 
    {
        return cxOrderNo;
    }
    public void setChangeAmt(Long changeAmt) 
    {
        this.changeAmt = changeAmt;
    }

    public Long getChangeAmt() 
    {
        return changeAmt;
    }
    public void setChangeBef(String changeBef) 
    {
        this.changeBef = changeBef;
    }

    public String getChangeBef() 
    {
        return changeBef;
    }
    public void setChangeAft(String changeAft) 
    {
        this.changeAft = changeAft;
    }

    public String getChangeAft() 
    {
        return changeAft;
    }
    public void setChangeText(String changeText) 
    {
        this.changeText = changeText;
    }

    public String getChangeText() 
    {
        return changeText;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merId", getMerId())
            .append("type", getType())
            .append("accountId", getAccountId())
            .append("cxOrderNo", getCxOrderNo())
            .append("changeAmt", getChangeAmt())
            .append("changeBef", getChangeBef())
            .append("changeAft", getChangeAft())
            .append("changeText", getChangeText())
            .append("createTime", getCreateTime())
            .toString();
    }
}
