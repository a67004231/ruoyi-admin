package com.ruoyi.factor.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 路由因子类型对象 factor_type
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
public class FactorType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 1比较型，2加分型，3区间型，4时间区间型，5大于,6小于 */
    @Excel(name = "1比较型，2加分型，3区间型，4时间区间型，5大于,6小于")
    private Integer type;

    /** 因子名称 */
    @Excel(name = "因子名称")
    private String factorName;

    /** 是否为静态，1是，0否 */
    @Excel(name = "是否为静态，1是，0否")
    private Integer isStatic;

    /** 是否是通道参数1是0否 */
    @Excel(name = "是否是通道参数1是0否")
    private Integer isChannel;

    /** 1通过0不通过 */
    @Excel(name = "1通过0不通过")
    private Long isPass;

    /** 1启用0未启用2停用 */
    @Excel(name = "1启用0未启用2停用")
    private Integer status;

    /** 数据键(静态因子与数据字段匹配，动态因子与redis键匹配) */
    @Excel(name = "数据键(静态因子与数据字段匹配，动态因子与redis键匹配)")
    private String dataKey;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setFactorName(String factorName) 
    {
        this.factorName = factorName;
    }

    public String getFactorName() 
    {
        return factorName;
    }
    public void setIsStatic(Integer isStatic) 
    {
        this.isStatic = isStatic;
    }

    public Integer getIsStatic() 
    {
        return isStatic;
    }
    public void setIsChannel(Integer isChannel) 
    {
        this.isChannel = isChannel;
    }

    public Integer getIsChannel() 
    {
        return isChannel;
    }
    public void setIsPass(Long isPass) 
    {
        this.isPass = isPass;
    }

    public Long getIsPass() 
    {
        return isPass;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setDataKey(String dataKey) 
    {
        this.dataKey = dataKey;
    }

    public String getDataKey() 
    {
        return dataKey;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("factorName", getFactorName())
            .append("isStatic", getIsStatic())
            .append("isChannel", getIsChannel())
            .append("isPass", getIsPass())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("dataKey", getDataKey())
            .toString();
    }
}
