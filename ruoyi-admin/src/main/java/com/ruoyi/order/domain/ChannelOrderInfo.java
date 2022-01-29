package com.ruoyi.order.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 通道订单对象 channel_order_info
 * 
 * @author ruoyi
 * @date 2021-06-15
 */
public class ChannelOrderInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 通道ID */
    @Excel(name = "通道ID")
    private Long channelId;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;

    /** 结果时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结果时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date resultDate;

    /** 状态 1充值中 2成功 3失败 */
    @Excel(name = "状态 1充值中 2成功 3失败")
    private Integer status;

    /** 是否收到通知 1收到 0未收到 */
    @Excel(name = "是否收到通知 1收到 0未收到")
    private Integer isNotify;

    /** 是否查询 1查询 0未查询 */
    @Excel(name = "是否查询 1查询 0未查询")
    private Integer isSearch;

    /** 订单ID */
    @Excel(name = "订单ID")
    private Long orderId;

    /** 系统订单号 */
    @Excel(name = "系统订单号")
    private String sysOrderNo;

    /** 通道订单号 */
    @Excel(name = "通道订单号")
    private String channelOrderNo;

    /** 充值金额 */
    @Excel(name = "充值金额")
    private Long amt;

    /** 成本金额 */
    @Excel(name = "成本金额")
    private Long sysActAmt;

    /** 充值手机号 */
    @Excel(name = "充值手机号")
    private String tel;

    /** 官方流水号 */
    @Excel(name = "官方流水号")
    private String serialNumber;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    private String channelName;
    
    
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
    public void setResultDate(Date resultDate) 
    {
        this.resultDate = resultDate;
    }

    public Date getResultDate() 
    {
        return resultDate;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setIsNotify(Integer isNotify) 
    {
        this.isNotify = isNotify;
    }

    public Integer getIsNotify() 
    {
        return isNotify;
    }
    public void setIsSearch(Integer isSearch) 
    {
        this.isSearch = isSearch;
    }

    public Integer getIsSearch() 
    {
        return isSearch;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setSysOrderNo(String sysOrderNo) 
    {
        this.sysOrderNo = sysOrderNo;
    }

    public String getSysOrderNo() 
    {
        return sysOrderNo;
    }
    public void setChannelOrderNo(String channelOrderNo) 
    {
        this.channelOrderNo = channelOrderNo;
    }

    public String getChannelOrderNo() 
    {
        return channelOrderNo;
    }
    public void setAmt(Long amt) 
    {
        this.amt = amt;
    }

    public Long getAmt() 
    {
        return amt;
    }
    public void setSysActAmt(Long sysActAmt) 
    {
        this.sysActAmt = sysActAmt;
    }

    public Long getSysActAmt() 
    {
        return sysActAmt;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setSerialNumber(String serialNumber) 
    {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() 
    {
        return serialNumber;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelId", getChannelId())
            .append("channelCode", getChannelCode())
            .append("createTime", getCreateTime())
            .append("resultDate", getResultDate())
            .append("status", getStatus())
            .append("isNotify", getIsNotify())
            .append("isSearch", getIsSearch())
            .append("orderId", getOrderId())
            .append("sysOrderNo", getSysOrderNo())
            .append("channelOrderNo", getChannelOrderNo())
            .append("amt", getAmt())
            .append("sysActAmt", getSysActAmt())
            .append("tel", getTel())
            .append("serialNumber", getSerialNumber())
            .append("version", getVersion())
            .toString();
    }
}
