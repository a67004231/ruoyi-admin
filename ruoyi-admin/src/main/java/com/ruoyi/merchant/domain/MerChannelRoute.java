package com.ruoyi.merchant.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商户通道路由对象 mer_channel_route
 * 
 * @author ruoyi
 * @date 2021-08-20
 */
public class MerChannelRoute extends BaseEntity
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
    private Long channelId;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;

    /** 通道名称 */
    @Excel(name = "通道名称")
    private String channelName;

    /** 公司id */
    private Long companyId;

    /** 公司名 */
    @Excel(name = "公司名")
    private String companyName;

    /** 运营商 */
    @Excel(name = "运营商")
    private String operators;

    /** 区域id */
    @Excel(name = "区域id")
    private String areaIds;

    /** 面额 */
    @Excel(name = "面额")
    private String hasAmout;

    /** 起始时间 */
    private String startTime;

    /** 结束时间 */
    private String endTime;

    /** 状态0未启用1启用2停用 */
    @Excel(name = "状态0未启用1启用2停用")
    private Integer status;
    /** 排序 */
    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
            .append("operators", getOperators())
            .append("areaIds", getAreaIds())
            .append("hasAmout", getHasAmout())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .toString();
    }
}
