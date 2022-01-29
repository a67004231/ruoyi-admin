package com.ruoyi.channel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 通道信息对象 channel_info
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
public class ChannelInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;
    /** 通道编码 */
    @Excel(name = "通道分组")
    private String groupCode;

    /** 通道名称 */
    @Excel(name = "通道名称")
    private String channelName;

    /** 初始权重 */
    @Excel(name = "初始权重")
    private String weight;

    /** 通道状态 */
    @Excel(name = "通道状态")
    private Integer status;

    /** 创建人 */
    private String createBy;
    /** 创建IP */
    private String createIp;
    /**
     * 授信总金额，单位：分
     */
    private Long creditAmt;

    /**
     * 已使用授信额度，单位：分
     */
    private Long creditUseAmt;

    /**
     * 授信余额，单位：分
     */
    private Long creditBalanceAmt;

    /**
     * 处理中授信资金，单位：分
     */
    private Long creditFixAmt;

    /**公司ID**/
    private Long companyId;
    /**公司名**/
    private String companyName;
    /**通道简称**/
    private String channelShortName;

    public String getChannelShortName() {
        return channelShortName;
    }

    public void setChannelShortName(String channelShortName) {
        this.channelShortName = channelShortName;
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
    
    
    public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
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

	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }
    public void setChannelName(String channelName) 
    {
        this.channelName = channelName;
    }

    public String getChannelName() 
    {
        return channelName;
    }
    public void setWeight(String weight) 
    {
        this.weight = weight;
    }

    public String getWeight() 
    {
        return weight;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    
    public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelCode", getChannelCode())
            .append("channelName", getChannelName())
            .append("weight", getWeight())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .toString();
    }
}
