package com.ruoyi.factor.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 通道路由日志对象 factor_log
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
public class FactorLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /**  */
    @Excel(name = "")
    private String sysOrderNo;

    /**  */
    @Excel(name = "")
    private String merOrderNo;

    /**  */
    @Excel(name = "")
    private String tel;

    /**  */
    @Excel(name = "")
    private Long productId;

    /**  */
    @Excel(name = "")
    private String merNo;

    /**  */
    @Excel(name = "")
    private Long merId;

    /**  */
    @Excel(name = "")
    private String channelCode;

    /**  */
    @Excel(name = "")
    private Long channelId;

    /**  */
    @Excel(name = "")
    private BigDecimal total;

    /**  */
    @Excel(name = "")
    private String rate;

    /**  */
    @Excel(name = "")
    private Long amt;

    /**  */
    @Excel(name = "")
    private String factorName;

    /**  */
    @Excel(name = "")
    private Long factorId;

    /**  */
    @Excel(name = "")
    private String value;

    /**  */
    @Excel(name = "")
    private Long minData;

    /**  */
    @Excel(name = "")
    private Long maxData;

    /**  */
    @Excel(name = "")
    private BigDecimal weight;

    /**  */
    @Excel(name = "")
    private String lastChannel;

    /**  */
    @Excel(name = "")
    private Long step;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSysOrderNo(String sysOrderNo) 
    {
        this.sysOrderNo = sysOrderNo;
    }

    public String getSysOrderNo() 
    {
        return sysOrderNo;
    }
    public void setMerOrderNo(String merOrderNo) 
    {
        this.merOrderNo = merOrderNo;
    }

    public String getMerOrderNo() 
    {
        return merOrderNo;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setMerNo(String merNo) 
    {
        this.merNo = merNo;
    }

    public String getMerNo() 
    {
        return merNo;
    }
    public void setMerId(Long merId) 
    {
        this.merId = merId;
    }

    public Long getMerId() 
    {
        return merId;
    }
    public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }
    public void setChannelId(Long channelId) 
    {
        this.channelId = channelId;
    }

    public Long getChannelId() 
    {
        return channelId;
    }
    public void setTotal(BigDecimal total) 
    {
        this.total = total;
    }

    public BigDecimal getTotal() 
    {
        return total;
    }
    public void setRate(String rate) 
    {
        this.rate = rate;
    }

    public String getRate() 
    {
        return rate;
    }
    public void setAmt(Long amt) 
    {
        this.amt = amt;
    }

    public Long getAmt() 
    {
        return amt;
    }
    public void setFactorName(String factorName) 
    {
        this.factorName = factorName;
    }

    public String getFactorName() 
    {
        return factorName;
    }
    public void setFactorId(Long factorId) 
    {
        this.factorId = factorId;
    }

    public Long getFactorId() 
    {
        return factorId;
    }
    public void setValue(String value) 
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }
    public void setMinData(Long minData) 
    {
        this.minData = minData;
    }

    public Long getMinData() 
    {
        return minData;
    }
    public void setMaxData(Long maxData) 
    {
        this.maxData = maxData;
    }

    public Long getMaxData() 
    {
        return maxData;
    }
    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }

    public BigDecimal getWeight() 
    {
        return weight;
    }
    public void setLastChannel(String lastChannel) 
    {
        this.lastChannel = lastChannel;
    }

    public String getLastChannel() 
    {
        return lastChannel;
    }
    public void setStep(Long step) 
    {
        this.step = step;
    }

    public Long getStep() 
    {
        return step;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sysOrderNo", getSysOrderNo())
            .append("merOrderNo", getMerOrderNo())
            .append("tel", getTel())
            .append("productId", getProductId())
            .append("merNo", getMerNo())
            .append("merId", getMerId())
            .append("channelCode", getChannelCode())
            .append("channelId", getChannelId())
            .append("total", getTotal())
            .append("rate", getRate())
            .append("amt", getAmt())
            .append("factorName", getFactorName())
            .append("factorId", getFactorId())
            .append("value", getValue())
            .append("minData", getMinData())
            .append("maxData", getMaxData())
            .append("weight", getWeight())
            .append("createTime", getCreateTime())
            .append("lastChannel", getLastChannel())
            .append("step", getStep())
            .toString();
    }
}
