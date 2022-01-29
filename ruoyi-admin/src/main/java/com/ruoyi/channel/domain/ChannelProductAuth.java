package com.ruoyi.channel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 通道产品授权对象 channel_product_auth
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public class ChannelProductAuth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 授权ID */
    private Long id;

    /** 通道ID */
    @Excel(name = "通道ID")
    private Long channelId;

    /** 产品ID */
    @Excel(name = "产品ID")
    private Long productId;

    /** 费率类型 */
    @Excel(name = "费率类型")
    private Integer rateType;

    /** 费率 */
    @Excel(name = "费率")
    private String rate;

    /** 权重 */
    @Excel(name = "权重")
    private Integer weight;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

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
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setRateType(Integer rateType) 
    {
        this.rateType = rateType;
    }

    public Integer getRateType() 
    {
        return rateType;
    }
    public void setRate(String rate) 
    {
        this.rate = rate;
    }

    public String getRate() 
    {
        return rate;
    }
    public void setWeight(Integer weight) 
    {
        this.weight = weight;
    }

    public Integer getWeight() 
    {
        return weight;
    }
    public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }
    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelId", getChannelId())
            .append("productId", getProductId())
            .append("rateType", getRateType())
            .append("rate", getRate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("weight", getWeight())
            .append("channelCode", getChannelCode())
            .append("productCode", getProductCode())
            .toString();
    }
}
