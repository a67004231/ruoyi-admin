package com.ruoyi.capital.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * capitalAccInfo对象 capital_acc_info
 * 
 * @author ruoyi
 * @date 2021-05-20
 */
public class CapitalAccInfo extends BaseEntity
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

    /** 商户名 */
    @Excel(name = "商户名")
    private String merName;

    /** 公司id */
    @Excel(name = "公司id")
    private Long companyId;

    /** 公司名 */
    @Excel(name = "公司名")
    private String companyName;
    /** 付款账户 */
    @Excel(name = "付款账户")
    private String capitalAccNo;

    /** 付款账户名 */
    @Excel(name = "付款账户名")
    private String capitalAccName;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 类型 */
    @Excel(name = "类型")
    private Integer type;

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
    public void setMerName(String merName) 
    {
        this.merName = merName;
    }

    public String getMerName() 
    {
        return merName;
    }
    public void setCapitalAccNo(String capitalAccNo) 
    {
        this.capitalAccNo = capitalAccNo;
    }

    public String getCapitalAccNo() 
    {
        return capitalAccNo;
    }
    public void setCapitalAccName(String capitalAccName) 
    {
        this.capitalAccName = capitalAccName;
    }

    public String getCapitalAccName() 
    {
        return capitalAccName;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelId", getChannelId())
            .append("channelName", getChannelName())
            .append("merId", getMerId())
            .append("merName", getMerName())
            .append("capitalAccNo", getCapitalAccNo())
            .append("capitalAccName", getCapitalAccName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("type", getType())
            .toString();
    }
}
