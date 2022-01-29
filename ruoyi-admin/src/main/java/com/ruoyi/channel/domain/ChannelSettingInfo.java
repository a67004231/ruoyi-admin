package com.ruoyi.channel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 通道账户对象 channel_setting_info
 * 
 * @author ruoyi
 * @date 2021-05-11
 */
public class ChannelSettingInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 通道编码 */
    @Excel(name = "通道编码")
    private String channelCode;

    /** 通道ID */
    private Long channelId;

    private String channelName;

    /** 公司ID */
    private Long companyId;

    private String companyName;
    /** 通道商户编码 */
    private String appid;

    /** 秘钥类型 */
    private Integer signType;

    /** MD5秘钥 */
    @Excel(name = "MD5秘钥")
    private String md5Key;

    /** RSA公钥 */
    private String rsaPublicKey;

    /** RSA私钥 */
    private String rsaPrivateKey;

    /** 通道状态 */
    @Excel(name = "通道状态")
    private Integer status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    
    public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public void setAppid(String appid) 
    {
        this.appid = appid;
    }

    public String getAppid() 
    {
        return appid;
    }
    public void setSignType(Integer signType) 
    {
        this.signType = signType;
    }

    public Integer getSignType() 
    {
        return signType;
    }
    public void setMd5Key(String md5Key) 
    {
        this.md5Key = md5Key;
    }

    public String getMd5Key() 
    {
        return md5Key;
    }
    public void setRsaPublicKey(String rsaPublicKey) 
    {
        this.rsaPublicKey = rsaPublicKey;
    }

    public String getRsaPublicKey() 
    {
        return rsaPublicKey;
    }
    public void setRsaPrivateKey(String rsaPrivateKey) 
    {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public String getRsaPrivateKey() 
    {
        return rsaPrivateKey;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelCode", getChannelCode())
            .append("channelId", getChannelId())
            .append("companyId", getCompanyId())
            .append("appid", getAppid())
            .append("signType", getSignType())
            .append("md5Key", getMd5Key())
            .append("rsaPublicKey", getRsaPublicKey())
            .append("rsaPrivateKey", getRsaPrivateKey())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .toString();
    }
}
