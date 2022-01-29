package com.ruoyi.merchant.service;

import java.util.List;
import com.ruoyi.merchant.domain.MerInfo;

/**
 * 商户基础信息Service接口
 * 
 * @author ruoyi
 * @date 2021-04-30
 */
public interface IMerInfoService 
{
    /**
     * 查询商户基础信息
     * 
     * @param id 商户基础信息ID
     * @return 商户基础信息
     */
    public MerInfo selectMerInfoById(Long id);

    /**
     * 查询商户基础信息列表
     * 
     * @param merInfo 商户基础信息
     * @return 商户基础信息集合
     */
    public List<MerInfo> selectMerInfoList(MerInfo merInfo);

    /**
     * 新增商户基础信息
     * 
     * @param merInfo 商户基础信息
     * @return 结果
     */
    public int insertMerInfo(MerInfo merInfo);

    /**
     * 修改商户基础信息
     * 
     * @param merInfo 商户基础信息
     * @return 结果
     */
    public int updateMerInfo(MerInfo merInfo);

    /**
     * 批量删除商户基础信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerInfoByIds(String ids);

    /**
     * 删除商户基础信息信息
     * 
     * @param id 商户基础信息ID
     * @return 结果
     */
    public int deleteMerInfoById(Long id);
}
