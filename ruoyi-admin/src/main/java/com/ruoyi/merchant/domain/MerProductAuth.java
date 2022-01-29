package com.ruoyi.merchant.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商户产品授权对象 mer_product_auth
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public class MerProductAuth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 授权ID */
    private Long id;

    /** 商户编号 */
    @Excel(name = "商户编号")
    private Long merId;

    /** 产品ID */
    @Excel(name = "产品ID")
    private Long productId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 费率类型 */
    @Excel(name = "费率类型")
    private Integer rateType;

    /** 费率 */
    @Excel(name = "费率")
    private String rate;

    /** 通道ID */
    @Excel(name = "通道ID")
    private Long channelId;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;

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
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
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
    public void setChannelId(Long channelId) 
    {
        this.channelId = channelId;
    }

    public Long getChannelId() 
    {
        return channelId;
    }
    public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merId", getMerId())
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("rateType", getRateType())
            .append("rate", getRate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("channelId", getChannelId())
            .append("channelCode", getChannelCode())
            .toString();
    }
}
