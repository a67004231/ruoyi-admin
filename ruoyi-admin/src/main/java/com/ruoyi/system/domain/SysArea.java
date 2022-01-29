package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地区对象 sys_area
 * 
 * @author ruoyi
 * @date 2021-08-06
 */
public class SysArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 区域主键 */
    private Long id;

    /** 区域名称 */
    @Excel(name = "区域名称")
    private String name;

    /** 区域上级标识 */
    @Excel(name = "区域上级标识")
    private Long parentId;

    /** 地名简称 */
    @Excel(name = "地名简称")
    private String simpleName;

    /** 区域等级 */
    @Excel(name = "区域等级")
    private Long level;

    /** 城市编码 */
    @Excel(name = "城市编码")
    private String cityCode;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String zipCode;

    /** 组合名称 */
    @Excel(name = "组合名称")
    private String merName;

    /** 经度 */
    @Excel(name = "经度")
    private Long lng;

    /** 纬度 */
    @Excel(name = "纬度")
    private Long lat;

    /** 拼音 */
    @Excel(name = "拼音")
    private String pinyin;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setSimpleName(String simpleName) 
    {
        this.simpleName = simpleName;
    }

    public String getSimpleName() 
    {
        return simpleName;
    }
    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
    }
    public void setCityCode(String cityCode) 
    {
        this.cityCode = cityCode;
    }

    public String getCityCode() 
    {
        return cityCode;
    }
    public void setZipCode(String zipCode) 
    {
        this.zipCode = zipCode;
    }

    public String getZipCode() 
    {
        return zipCode;
    }
    public void setMerName(String merName) 
    {
        this.merName = merName;
    }

    public String getMerName() 
    {
        return merName;
    }
    public void setLng(Long lng) 
    {
        this.lng = lng;
    }

    public Long getLng() 
    {
        return lng;
    }
    public void setLat(Long lat) 
    {
        this.lat = lat;
    }

    public Long getLat() 
    {
        return lat;
    }
    public void setPinyin(String pinyin) 
    {
        this.pinyin = pinyin;
    }

    public String getPinyin() 
    {
        return pinyin;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("parentId", getParentId())
            .append("simpleName", getSimpleName())
            .append("level", getLevel())
            .append("cityCode", getCityCode())
            .append("zipCode", getZipCode())
            .append("merName", getMerName())
            .append("lng", getLng())
            .append("lat", getLat())
            .append("pinyin", getPinyin())
            .toString();
    }
}
