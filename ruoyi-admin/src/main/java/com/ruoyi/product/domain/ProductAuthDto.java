package com.ruoyi.product.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品授权对象 product_auth
 * 
 * @author haoyu
 * @date 2021-09-08
 */
public class ProductAuthDto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 商户id */
    private Long merId;

    /** 通道id */
    @Excel(name = "通道id")
    private Long channelId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名 */
    @Excel(name = "产品名")
    private String productName;

    /** 上级产品名 */
    @Excel(name = "上级产品名")
    private String parentName;

    /** 费率 */
    @Excel(name = "费率")
    private Integer rate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerId() {
        return merId;
    }

    public void setMerId(Long merId) {
        this.merId = merId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
