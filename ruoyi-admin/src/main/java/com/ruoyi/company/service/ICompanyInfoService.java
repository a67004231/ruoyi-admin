package com.ruoyi.company.service;

import java.util.List;
import com.ruoyi.company.domain.CompanyInfo;

/**
 * 公司信息Service接口
 * 
 * @author ruoyi
 * @date 2021-08-11
 */
public interface ICompanyInfoService 
{
    /**
     * 查询公司信息
     * 
     * @param id 公司信息ID
     * @return 公司信息
     */
    public CompanyInfo selectCompanyInfoById(Long id);

    /**
     * 查询公司信息列表
     * 
     * @param companyInfo 公司信息
     * @return 公司信息集合
     */
    public List<CompanyInfo> selectCompanyInfoList(CompanyInfo companyInfo);

    /**
     * 新增公司信息
     * 
     * @param companyInfo 公司信息
     * @return 结果
     */
    public int insertCompanyInfo(CompanyInfo companyInfo);

    /**
     * 修改公司信息
     * 
     * @param companyInfo 公司信息
     * @return 结果
     */
    public int updateCompanyInfo(CompanyInfo companyInfo);

    /**
     * 批量删除公司信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCompanyInfoByIds(String ids);

    /**
     * 删除公司信息信息
     * 
     * @param id 公司信息ID
     * @return 结果
     */
    public int deleteCompanyInfoById(Long id);

    int changeStatus(CompanyInfo companyInfo);
}
