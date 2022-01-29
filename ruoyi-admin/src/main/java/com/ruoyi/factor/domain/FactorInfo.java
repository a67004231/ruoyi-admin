package com.ruoyi.factor.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 路由因子对象 factor_info
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
public class FactorInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 因子类型id */
    @Excel(name = "因子类型id")
    private Long factorTypeId;

    /** 通道id */
    @Excel(name = "通道id")
    private Long channelId;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;
    /** 通道名称 */
    @Excel(name = "通道名称")
    private String channelName;
    /** 参数值 */
    @Excel(name = "参数值")
    private Long value;

    /** 最小数据 */
    @Excel(name = "最小数据")
    private Long minData;

    /** 最大数据 */
    @Excel(name = "最大数据")
    private Long maxData;

    /** 权重（0-100） */
    @Excel(name = "权重", readConverterExp = "0=-100")
    private Integer weight;

    /** redis主键，对应redis动态数据值 */
    @Excel(name = "redis主键，对应redis动态数据值")
    private String redisKey;

    /** 库存比例 */
    @Excel(name = "库存比例")
    private BigDecimal rate;

    /** 因子名称 */
    @Excel(name = "因子名称")
    private String factorName;

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFactorTypeId(Long factorTypeId) 
    {
        this.factorTypeId = factorTypeId;
    }

    public Long getFactorTypeId() 
    {
        return factorTypeId;
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
    public void setValue(Long value) 
    {
        this.value = value;
    }

    public Long getValue() 
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
    public void setWeight(Integer weight) 
    {
        this.weight = weight;
    }

    public Integer getWeight() 
    {
        return weight;
    }
    public void setRedisKey(String redisKey) 
    {
        this.redisKey = redisKey;
    }

    public String getRedisKey() 
    {
        return redisKey;
    }
    public void setRate(BigDecimal rate) 
    {
        this.rate = rate;
    }

    public BigDecimal getRate() 
    {
        return rate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("factorTypeId", getFactorTypeId())
            .append("channelId", getChannelId())
            .append("channelCode", getChannelCode())
            .append("value", getValue())
            .append("minData", getMinData())
            .append("maxData", getMaxData())
            .append("weight", getWeight())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("redisKey", getRedisKey())
            .append("rate", getRate())
            .toString();
    }
}
