package com.ruoyi.channel.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 通道初始化对象 channel_start
 * 
 * @author ruoyi
 * @date 2021-05-10
 */
public class ChannelStart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /**  */
    private Long channelId;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;
    /** 通道名称 */
    @Excel(name = "通道名称")
    private String channelName;
    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 订单数量 */
    @Excel(name = "订单数量")
    private Long orderNum;

    /** 最大数量 */
    @Excel(name = "最大数量")
    private Long maxNum;

    /** 库存率比例 */
    @Excel(name = "库存率比例")
    private String scale;

    /** 面额 */
    @Excel(name = "面额")
    private Long amt;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;
    
    private String serviceName;
    
    private Integer merId;
    
    private String merName;

    private Long companyId;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getMerId() {
		return merId;
	}

	public void setMerId(Integer merId) {
		this.merId = merId;
	}

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
    public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setOrderNum(Long orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum() 
    {
        return orderNum;
    }
    public void setMaxNum(Long maxNum) 
    {
        this.maxNum = maxNum;
    }

    public Long getMaxNum() 
    {
        return maxNum;
    }
    public void setScale(String scale) 
    {
        this.scale = scale;
    }

    public String getScale() 
    {
        return scale;
    }
    public void setAmt(Long amt) 
    {
        this.amt = amt;
    }

    public Long getAmt() 
    {
        return amt;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelId", getChannelId())
            .append("channelCode", getChannelCode())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("orderNum", getOrderNum())
            .append("maxNum", getMaxNum())
            .append("scale", getScale())
            .append("amt", getAmt())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .toString();
    }
}
