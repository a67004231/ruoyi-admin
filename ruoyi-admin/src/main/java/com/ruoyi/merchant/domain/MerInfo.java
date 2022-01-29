package com.ruoyi.merchant.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商户基础信息对象 mer_info
 * 
 * @author ruoyi
 * @date 2021-04-30
 */

public class MerInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 商户编号 */
    @Excel(name = "商户编号")
    private String merNo;

    /** 商户全称 */
    @Excel(name = "商户全称")
    private String merName;

    /** 商户简称 */
    @Excel(name = "商户简称")
    private String merShortName;

    /** 商户类型 */
    @Excel(name = "商户类型")
    private Integer merType;

    /** 用户ID */
    private Long userId;

    /** 紧急联系人手机号 */
    private String tel;

    /** 紧急联系人手机号HASH */
    private String telHash;

    /** 紧急联系人手机号掩码 */
    private String telDisplay;

    /** 紧急联系人邮箱 */
    private String email;

    /** 紧急联系人手机号HASH */
    private String emailHash;

    /** 紧急联系人手机号掩码 */
    private String emailDisplay;

    /** 商户状态，0：停用；1：启用； */
    @Excel(name = "商户状态，0：停用；1：启用；")
    private Integer status;
    /**MD5秘钥**/
    private String md5key;
    /**部门ID**/
    private Long deptId;
    /** 授信总金额，单位：分 */
    @Excel(name = "授信总金额，单位：分")
    private Long creditAmt;

    /** 已使用授信额度，单位：分 */
    @Excel(name = "已使用授信额度，单位：分")
    private Long creditUseAmt;

    /** 授信余额，单位：分 */
    @Excel(name = "授信余额，单位：分")
    private Long creditBalanceAmt;

    /** 处理中授信资金，单位：分 */
    @Excel(name = "处理中授信资金，单位：分")
    private Long creditFixAmt;


    /**公司ID**/
    private Long companyId;
    /**公司名**/
    private String companyName;

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

    public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getMd5key() {
		return md5key;
	}

	public void setMd5key(String md5key) {
		this.md5key = md5key;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setMerShortName(String merShortName) 
    {
        this.merShortName = merShortName;
    }

    public String getMerShortName() 
    {
        return merShortName;
    }
    public void setMerType(Integer merType) 
    {
        this.merType = merType;
    }

    public Integer getMerType() 
    {
        return merType;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setTelHash(String telHash) 
    {
        this.telHash = telHash;
    }

    public String getTelHash() 
    {
        return telHash;
    }
    public void setTelDisplay(String telDisplay) 
    {
        this.telDisplay = telDisplay;
    }

    public String getTelDisplay() 
    {
        return telDisplay;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setEmailHash(String emailHash) 
    {
        this.emailHash = emailHash;
    }

    public String getEmailHash() 
    {
        return emailHash;
    }
    public void setEmailDisplay(String emailDisplay) 
    {
        this.emailDisplay = emailDisplay;
    }

    public String getEmailDisplay() 
    {
        return emailDisplay;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public Long getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(Long creditAmt) {
        this.creditAmt = creditAmt;
    }

    public Long getCreditUseAmt() {
        return creditUseAmt;
    }

    public void setCreditUseAmt(Long creditUseAmt) {
        this.creditUseAmt = creditUseAmt;
    }

    public Long getCreditBalanceAmt() {
        return creditBalanceAmt;
    }

    public void setCreditBalanceAmt(Long creditBalanceAmt) {
        this.creditBalanceAmt = creditBalanceAmt;
    }

    public Long getCreditFixAmt() {
        return creditFixAmt;
    }

    public void setCreditFixAmt(Long creditFixAmt) {
        this.creditFixAmt = creditFixAmt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merNo", getMerNo())
            .append("merName", getMerName())
            .append("merShortName", getMerShortName())
            .append("merType", getMerType())
            .append("userId", getUserId())
            .append("tel", getTel())
            .append("telHash", getTelHash())
            .append("telDisplay", getTelDisplay())
            .append("email", getEmail())
            .append("emailHash", getEmailHash())
            .append("emailDisplay", getEmailDisplay())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .toString();
    }
}
