package com.ruoyi.channel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 通道库存信息对象 channel_stock
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
public class ChannelStock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;

    /** 通道名称 */
    @Excel(name = "通道名称")
    private String channelName;

    /** 金额 */
    @Excel(name = "金额")
    private Long amt;

    /** 初始权重 */
    @Excel(name = "初始权重")
    private Long stockNum;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

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
    public void setAmt(Long amt) 
    {
        this.amt = amt;
    }

    public Long getAmt() 
    {
        return amt;
    }
    public void setStockNum(Long stockNum) 
    {
        this.stockNum = stockNum;
    }

    public Long getStockNum() 
    {
        return stockNum;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelCode", getChannelCode())
            .append("channelName", getChannelName())
            .append("amt", getAmt())
            .append("stockNum", getStockNum())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .toString();
    }
}
