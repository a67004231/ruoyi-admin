package com.ruoyi.merchant.mapper;

import java.util.List;
import com.ruoyi.merchant.domain.MerAccount;

/**
 * 商户账户Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-03
 */
public interface MerAccountMapper 
{
    /**
     * 查询商户账户
     * 
     * @param id 商户账户ID
     * @return 商户账户
     */
    public MerAccount selectMerAccountById(Long id);

    /**
     * 查询商户账户列表
     * 
     * @param merAccount 商户账户
     * @return 商户账户集合
     */
    public List<MerAccount> selectMerAccountList(MerAccount merAccount);

    /**
     * 新增商户账户
     * 
     * @param merAccount 商户账户
     * @return 结果
     */
    public int insertMerAccount(MerAccount merAccount);

    /**
     * 修改商户账户
     * 
     * @param merAccount 商户账户
     * @return 结果
     */
    public int updateMerAccount(MerAccount merAccount);

    /**
     * 删除商户账户
     * 
     * @param id 商户账户ID
     * @return 结果
     */
    public int deleteMerAccountById(Long id);

    /**
     * 批量删除商户账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerAccountByIds(String[] ids);
}
