package com.ruoyi.company.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公司信息对象 company_info
 * 
 * @author ruoyi
 * @date 2021-08-11
 */
public class CompanyInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 公司编码 */
    @Excel(name = "公司编码")
    private String companyNo;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;
    @Excel(name = "路由类型")
    private Integer routeType;

    
    public Integer getRouteType() {
		return routeType;
	}

	public void setRouteType(Integer routeType) {
		this.routeType = routeType;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCompanyNo(String companyNo) 
    {
        this.companyNo = companyNo;
    }

    public String getCompanyNo() 
    {
        return companyNo;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
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
            .append("companyNo", getCompanyNo())
            .append("companyName", getCompanyName())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
