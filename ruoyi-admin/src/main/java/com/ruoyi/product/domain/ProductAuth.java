package com.ruoyi.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品授权对象 product_auth
 * 
 * @author haoyu
 * @date 2021-09-08
 */
public class ProductAuth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 商户id */
    private Long merId;

    /** 商户编码 */
    @Excel(name = "商户编码")
    private String merNo;

    /** 商户名 */
    @Excel(name = "商户名")
    private String merName;

    /** 通道id */
    @Excel(name = "通道id")
    private Long channelId;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;

    /** 通道名称 */
    @Excel(name = "通道名称")
    private String channelName;

    /** 公司id */
    @Excel(name = "公司id")
    private Long companyId;

    /** 公司名 */
    @Excel(name = "公司名")
    private String companyName;

    /** 类型 */
    @Excel(name = "类型")
    private Integer type;

    /** 运营商 */
    @Excel(name = "运营商")
    private String operators;

    /** 区域id */
    @Excel(name = "区域id")
    private String areaIds;

    /** 面额 */
    @Excel(name = "面额")
    private String hasAmout;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 费率 */
    @Excel(name = "费率")
    private Integer rate;

    /** 费率类型 */
    @Excel(name = "费率类型")
    private Integer rateType;

    /** 表类型 */
    @Excel(name = "表类型")
    private Integer tableType;

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
    public void setMerNo(String merNo) 
    {
        this.merNo = merNo;
    }

    public String getMerNo() 
    {
        return merNo;
    }
    public void setMerName(String merName) 
    {
        this.merName = merName;
    }

    public String getMerName() 
    {
        return merName;
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
    public void setChannelName(String channelName) 
    {
        this.channelName = channelName;
    }

    public String getChannelName() 
    {
        return channelName;
    }
    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setOperators(String operators) 
    {
        this.operators = operators;
    }

    public String getOperators() 
    {
        return operators;
    }
    public void setAreaIds(String areaIds) 
    {
        this.areaIds = areaIds;
    }

    public String getAreaIds() 
    {
        return areaIds;
    }
    public void setHasAmout(String hasAmout) 
    {
        this.hasAmout = hasAmout;
    }

    public String getHasAmout() 
    {
        return hasAmout;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setRate(Integer rate) 
    {
        this.rate = rate;
    }

    public Integer getRate() 
    {
        return rate;
    }
    public void setRateType(Integer rateType) 
    {
        this.rateType = rateType;
    }

    public Integer getRateType() 
    {
        return rateType;
    }
    public void setTableType(Integer tableType) 
    {
        this.tableType = tableType;
    }

    public Integer getTableType() 
    {
        return tableType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merId", getMerId())
            .append("merNo", getMerNo())
            .append("merName", getMerName())
            .append("channelId", getChannelId())
            .append("channelCode", getChannelCode())
            .append("channelName", getChannelName())
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .append("type", getType())
            .append("operators", getOperators())
            .append("areaIds", getAreaIds())
            .append("hasAmout", getHasAmout())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("rate", getRate())
            .append("rateType", getRateType())
            .append("tableType", getTableType())
            .toString();
    }
}
