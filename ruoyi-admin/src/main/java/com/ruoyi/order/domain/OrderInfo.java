package com.ruoyi.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充值通道订单对象 order_info
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
public class OrderInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;

    /** 系统订单号 */
    @Excel(name = "系统订单号")
    private String sysOrderNo;

    /** 商户订单号 */
    @Excel(name = "商户订单号")
    private String merOrderNo;
    /** 商户订单号 */
    @Excel(name = "商户订单号")
    private String merOrderNoShow;

    /** 通道订单号 */
    private String thirdOrderNo;

    /** 商户编号 */
    @Excel(name = "商户编号")
    private String merNo;

    /** 通道商户编码 */
    private String thirdMerchantCode;

    /** 通道编码 */
    private String thirdCode;

    /** 充值手机号 */
    @Excel(name = "充值手机号")
    private String tel;

    /** 产品ID */
    private Long productId;

    /** 手机号所属省份 */
    private String province;
    /** 手机号所属省份ID */
    private Long areaId;
    /** 手机号所属城市 */
    private String city;
    /** 手机号所属城市ID */
    private Long cityId;

    /** 充值金额 */
    @Excel(name = "充值金额")
    private Long amt;

    /** 系统成本金额，单位：分 */
    @Excel(name = "系统成本金额，单位：分")
    private BigDecimal sysActAmt;

    /** 通道实扣余额，单位：分 */
    private Long channelAmtAdd;

    /** 通道可用余额，单位：分 */
    private Long channelAmtRs;

    /** 结果时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结果时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date resultTime;

    /** 提交通道时返回结果（json） */
    private String postMsg;

    /** 查询或异步返回通道返回结果（json） */
    private String asynMsg;

    /** 通道响应码 */
    private String channelDealCode;

    /** 通道响应描述 */
    private String channelDealMsg;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 商户ID */
    private Long merId;

    /** 费率 */
    private String rate;

    /** 通道编码 */
    private String channelCode;

    /** 通道ID */
    private Long channelId;

    /** 费率类型 */
    private String rateType;

    /** 手续费 单位分 */
    private Long fee;

    /** 查询结果次数 */
    private Integer searchCount;

    /** 通知商户次数 */
    private Integer noticeCount;

    /** 通知商户状态 */
    private Integer noticeStatus;

    /** 通知商户地址 */
    private String noticeUrl;

    /** 预估时间(秒) */
    private Long maxTime;

    /** 已使用时间(秒) */
    private Long useTime;
    /**
     * 下单通道扣款，单位：分
     */
    private Long merAmtAdd;
    /**
     * 耗时
     */
    private Long timeConsuming;
    
    /**
     * 成功商户扣款，单位：分
     */
    private Long merAmtRs;
    private Long amtS;
    
    private Long amtE;
    /**
     * 运营商
     */
    private String serviceName;
    /**
     * 省份
     */
    private String pronvince;
    /**
     * 产品类型
     */
    private String productType;
    
    private String merShortName;
    
    private String channelName;
    /**公司ID**/
    private Long companyId;
    /**公司名**/
    private String companyName;
    private BigDecimal merActAmt;

    public BigDecimal getMerActAmt() {
        return merActAmt;
    }

    public void setMerActAmt(BigDecimal merActAmt) {
        this.merActAmt = merActAmt;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    
    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getMerShortName() {
		return merShortName;
	}

	public void setMerShortName(String merShortName) {
		this.merShortName = merShortName;
	}

	public Long getTimeConsuming() {
		return timeConsuming;
	}

	public void setTimeConsuming(Long timeConsuming) {
		this.timeConsuming = timeConsuming;
	}

	public String getPronvince() {
		return pronvince;
	}

	public void setPronvince(String pronvince) {
		this.pronvince = pronvince;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getMerOrderNoShow() {
		return merOrderNoShow;
	}

	public void setMerOrderNoShow(String merOrderNoShow) {
		this.merOrderNoShow = merOrderNoShow;
	}

	public Long getAmtS() {
		return amtS;
	}

	public void setAmtS(Long amtS) {
		this.amtS = amtS;
	}

	public Long getAmtE() {
		return amtE;
	}

	public void setAmtE(Long amtE) {
		this.amtE = amtE;
	}

	public Long getMerAmtAdd() {
		return merAmtAdd;
	}

	public void setMerAmtAdd(Long merAmtAdd) {
		this.merAmtAdd = merAmtAdd;
	}

	public Long getMerAmtRs() {
		return merAmtRs;
	}

	public void setMerAmtRs(Long merAmtRs) {
		this.merAmtRs = merAmtRs;
	}

	public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
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
    public void setThirdOrderNo(String thirdOrderNo) 
    {
        this.thirdOrderNo = thirdOrderNo;
    }

    public String getThirdOrderNo() 
    {
        return thirdOrderNo;
    }
    public void setMerNo(String merNo) 
    {
        this.merNo = merNo;
    }

    public String getMerNo() 
    {
        return merNo;
    }
    public void setThirdMerchantCode(String thirdMerchantCode) 
    {
        this.thirdMerchantCode = thirdMerchantCode;
    }

    public String getThirdMerchantCode() 
    {
        return thirdMerchantCode;
    }
    public void setThirdCode(String thirdCode) 
    {
        this.thirdCode = thirdCode;
    }

    public String getThirdCode() 
    {
        return thirdCode;
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
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setAmt(Long amt) 
    {
        this.amt = amt;
    }

    public Long getAmt() 
    {
        return amt;
    }

    public BigDecimal getSysActAmt() {
		return sysActAmt;
	}

	public void setSysActAmt(BigDecimal sysActAmt) {
		this.sysActAmt = sysActAmt;
	}

	public Long getChannelAmtAdd() {
		return channelAmtAdd;
	}

	public void setChannelAmtAdd(Long channelAmtAdd) {
		this.channelAmtAdd = channelAmtAdd;
	}

	public Long getChannelAmtRs() {
		return channelAmtRs;
	}

	public void setChannelAmtRs(Long channelAmtRs) {
		this.channelAmtRs = channelAmtRs;
	}

	public void setResultTime(Date resultTime) 
    {
        this.resultTime = resultTime;
    }

    public Date getResultTime() 
    {
        return resultTime;
    }
    public void setPostMsg(String postMsg) 
    {
        this.postMsg = postMsg;
    }

    public String getPostMsg() 
    {
        return postMsg;
    }
    public void setAsynMsg(String asynMsg) 
    {
        this.asynMsg = asynMsg;
    }

    public String getAsynMsg() 
    {
        return asynMsg;
    }
    public void setChannelDealCode(String channelDealCode) 
    {
        this.channelDealCode = channelDealCode;
    }

    public String getChannelDealCode() 
    {
        return channelDealCode;
    }
    public void setChannelDealMsg(String channelDealMsg) 
    {
        this.channelDealMsg = channelDealMsg;
    }

    public String getChannelDealMsg() 
    {
        return channelDealMsg;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setMerId(Long merId) 
    {
        this.merId = merId;
    }

    public Long getMerId() 
    {
        return merId;
    }
    public void setRate(String rate) 
    {
        this.rate = rate;
    }

    public String getRate() 
    {
        return rate;
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
    public void setRateType(String rateType) 
    {
        this.rateType = rateType;
    }

    public String getRateType() 
    {
        return rateType;
    }
    public void setFee(Long fee) 
    {
        this.fee = fee;
    }

    public Long getFee() 
    {
        return fee;
    }
    public void setSearchCount(Integer searchCount) 
    {
        this.searchCount = searchCount;
    }

    public Integer getSearchCount() 
    {
        return searchCount;
    }
    public void setNoticeCount(Integer noticeCount) 
    {
        this.noticeCount = noticeCount;
    }

    public Integer getNoticeCount() 
    {
        return noticeCount;
    }
    public void setNoticeStatus(Integer noticeStatus) 
    {
        this.noticeStatus = noticeStatus;
    }

    public Integer getNoticeStatus() 
    {
        return noticeStatus;
    }
    public void setNoticeUrl(String noticeUrl) 
    {
        this.noticeUrl = noticeUrl;
    }

    public String getNoticeUrl() 
    {
        return noticeUrl;
    }
    public void setMaxTime(Long maxTime) 
    {
        this.maxTime = maxTime;
    }

    public Long getMaxTime() 
    {
        return maxTime;
    }
    public void setUseTime(Long useTime) 
    {
        this.useTime = useTime;
    }

    public Long getUseTime() 
    {
        return useTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sysOrderNo", getSysOrderNo())
            .append("merOrderNo", getMerOrderNo())
            .append("thirdOrderNo", getThirdOrderNo())
            .append("merNo", getMerNo())
            .append("thirdMerchantCode", getThirdMerchantCode())
            .append("thirdCode", getThirdCode())
            .append("tel", getTel())
            .append("productId", getProductId())
            .append("province", getProvince())
            .append("amt", getAmt())
            .append("sysActAmt", getSysActAmt())
            .append("channelAmtAdd", getChannelAmtAdd())
            .append("channelAmtRs", getChannelAmtRs())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("resultTime", getResultTime())
            .append("postMsg", getPostMsg())
            .append("asynMsg", getAsynMsg())
            .append("channelDealCode", getChannelDealCode())
            .append("channelDealMsg", getChannelDealMsg())
            .append("status", getStatus())
            .append("merId", getMerId())
            .append("rate", getRate())
            .append("channelCode", getChannelCode())
            .append("channelId", getChannelId())
            .append("rateType", getRateType())
            .append("fee", getFee())
            .append("searchCount", getSearchCount())
            .append("noticeCount", getNoticeCount())
            .append("noticeStatus", getNoticeStatus())
            .append("noticeUrl", getNoticeUrl())
            .append("maxTime", getMaxTime())
            .append("useTime", getUseTime())
            .toString();
    }
}
