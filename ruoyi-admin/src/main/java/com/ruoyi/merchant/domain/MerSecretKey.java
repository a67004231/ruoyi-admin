package com.ruoyi.merchant.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商户秘钥管理对象 mer_secret_key
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public class MerSecretKey extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 秘钥ID */
    private Long id;

    /** 商户id */
    @Excel(name = "商户id")
    private Long merId;

    /** 秘钥类型 */
    @Excel(name = "秘钥类型")
    private Long signType;

    /** MD5秘钥 */
    @Excel(name = "MD5秘钥")
    private String md5Key;

    /** RSA公钥 */
    @Excel(name = "RSA公钥")
    private String rsaPublicKey;

    /** RSA私钥 */
    @Excel(name = "RSA私钥")
    private String rsaPrivateKey;

    /** 商户编码 */
    @Excel(name = "商户编码")
    private String merNo;

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
    public void setSignType(Long signType) 
    {
        this.signType = signType;
    }

    public Long getSignType() 
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
    public void setMerNo(String merNo) 
    {
        this.merNo = merNo;
    }

    public String getMerNo() 
    {
        return merNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merId", getMerId())
            .append("signType", getSignType())
            .append("md5Key", getMd5Key())
            .append("rsaPublicKey", getRsaPublicKey())
            .append("rsaPrivateKey", getRsaPrivateKey())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("merNo", getMerNo())
            .toString();
    }
}
