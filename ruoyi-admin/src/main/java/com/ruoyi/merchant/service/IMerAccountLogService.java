package com.ruoyi.merchant.service;

import java.util.List;
import com.ruoyi.merchant.domain.MerAccountLog;

/**
 * 账户变更记录Service接口
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
public interface IMerAccountLogService 
{
    /**
     * 查询账户变更记录
     * 
     * @param id 账户变更记录ID
     * @return 账户变更记录
     */
    public MerAccountLog selectMerAccountLogById(Long id);

    /**
     * 查询账户变更记录列表
     * 
     * @param merAccountLog 账户变更记录
     * @return 账户变更记录集合
     */
    public List<MerAccountLog> selectMerAccountLogList(MerAccountLog merAccountLog);

    /**
     * 新增账户变更记录
     * 
     * @param merAccountLog 账户变更记录
     * @return 结果
     */
    public int insertMerAccountLog(MerAccountLog merAccountLog);

    /**
     * 修改账户变更记录
     * 
     * @param merAccountLog 账户变更记录
     * @return 结果
     */
    public int updateMerAccountLog(MerAccountLog merAccountLog);

    /**
     * 批量删除账户变更记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerAccountLogByIds(String ids);

    /**
     * 删除账户变更记录信息
     * 
     * @param id 账户变更记录ID
     * @return 结果
     */
    public int deleteMerAccountLogById(Long id);
}
