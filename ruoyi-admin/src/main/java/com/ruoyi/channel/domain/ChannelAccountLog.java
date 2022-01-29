package com.ruoyi.channel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 通道账户变更记录对象 channel_account_log
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
public class ChannelAccountLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账户变更记录ID */
    private Long id;

    /** 通道ID */
    @Excel(name = "通道ID")
    private Long channelId;

    /** 资金类型 */
    private Integer type;

    /** 账户ID */
    private Long accountId;

    /** 系统订单号 */
    @Excel(name = "系统订单号")
    private String cxOrderNo;

    /** 变更金额 */
    @Excel(name = "变更金额")
    private Long changeAmt;

    /** 变更前，账户信息（json串） */
    private String changeBef;

    /** 变更后账户信息（json串） */
    private String changeAft;

    /** 变更备注 */
    @Excel(name = "变更备注")
    private String changeText;
    
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
    public void setChannelId(Long channelId) 
    {
        this.channelId = channelId;
    }

    public Long getChannelId() 
    {
        return channelId;
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
            .append("channelId", getChannelId())
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
