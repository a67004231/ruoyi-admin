package com.ruoyi.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 基础产品对象 product_info
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public class ProductInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String code;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private Long type;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String name;

    /** 上级产品编码 */
    @Excel(name = "上级产品编码")
    private String parentCode;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 地区ID */
    @Excel(name = "地区ID")
    private Long areaId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setParentCode(String parentCode) 
    {
        this.parentCode = parentCode;
    }

    public String getParentCode() 
    {
        return parentCode;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setAreaId(Long areaId) 
    {
        this.areaId = areaId;
    }

    public Long getAreaId() 
    {
        return areaId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("type", getType())
            .append("name", getName())
            .append("parentCode", getParentCode())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("areaId", getAreaId())
            .toString();
    }
}
