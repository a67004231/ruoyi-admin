package com.ruoyi.company.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.company.mapper.CompanyInfoMapper;
import com.ruoyi.company.domain.CompanyInfo;
import com.ruoyi.company.service.ICompanyInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 公司信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-08-11
 */
@Service
public class CompanyInfoServiceImpl implements ICompanyInfoService 
{
    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    /**
     * 查询公司信息
     * 
     * @param id 公司信息ID
     * @return 公司信息
     */
    @Override
    public CompanyInfo selectCompanyInfoById(Long id)
    {
        return companyInfoMapper.selectCompanyInfoById(id);
    }

    /**
     * 查询公司信息列表
     * 
     * @param companyInfo 公司信息
     * @return 公司信息
     */
    @Override
    public List<CompanyInfo> selectCompanyInfoList(CompanyInfo companyInfo)
    {
        return companyInfoMapper.selectCompanyInfoList(companyInfo);
    }

    /**
     * 新增公司信息
     * 
     * @param companyInfo 公司信息
     * @return 结果
     */
    @Override
    public int insertCompanyInfo(CompanyInfo companyInfo)
    {
        companyInfo.setCreateTime(DateUtils.getNowDate());
        return companyInfoMapper.insertCompanyInfo(companyInfo);
    }

    /**
     * 修改公司信息
     * 
     * @param companyInfo 公司信息
     * @return 结果
     */
    @Override
    public int updateCompanyInfo(CompanyInfo companyInfo)
    {
        companyInfo.setUpdateTime(DateUtils.getNowDate());
        return companyInfoMapper.updateCompanyInfo(companyInfo);
    }

    /**
     * 删除公司信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCompanyInfoByIds(String ids)
    {
        return companyInfoMapper.deleteCompanyInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除公司信息信息
     * 
     * @param id 公司信息ID
     * @return 结果
     */
    @Override
    public int deleteCompanyInfoById(Long id)
    {
        return companyInfoMapper.deleteCompanyInfoById(id);
    }

    @Override
    public int changeStatus(CompanyInfo companyInfo) {
        return companyInfoMapper.updateCompanyInfo(companyInfo);
    }
}
