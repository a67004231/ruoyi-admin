package com.ruoyi.channel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 抢单并发量对象 job_order_count
 * 
 * @author ruoyi
 * @date 2021-07-14
 */
public class JobOrderCount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 订单条数 */
    @Excel(name = "订单条数")
    private Integer orderCount;
    private Integer amt;
    private String service;
    private Integer status;
    private String skuId;
    
    
    public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public Integer getAmt() {
		return amt;
	}

	public void setAmt(Integer amt) {
		this.amt = amt;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderCount(Integer orderCount) 
    {
        this.orderCount = orderCount;
    }

    public Integer getOrderCount() 
    {
        return orderCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderCount", getOrderCount())
            .toString();
    }
}
