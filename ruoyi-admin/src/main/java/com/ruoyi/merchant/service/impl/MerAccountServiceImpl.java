package com.ruoyi.merchant.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.merchant.mapper.MerAccountMapper;
import com.ruoyi.merchant.domain.MerAccount;
import com.ruoyi.merchant.service.IMerAccountService;
import com.ruoyi.common.core.text.Convert;

/**
 * 商户账户Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-03
 */
@Service
public class MerAccountServiceImpl implements IMerAccountService 
{
    @Autowired
    private MerAccountMapper merAccountMapper;

    /**
     * 查询商户账户
     * 
     * @param id 商户账户ID
     * @return 商户账户
     */
    @Override
    public MerAccount selectMerAccountById(Long id)
    {
        return merAccountMapper.selectMerAccountById(id);
    }

    /**
     * 查询商户账户列表
     * 
     * @param merAccount 商户账户
     * @return 商户账户
     */
    @Override
    public List<MerAccount> selectMerAccountList(MerAccount merAccount)
    {
        return merAccountMapper.selectMerAccountList(merAccount);
    }

    /**
     * 新增商户账户
     * 
     * @param merAccount 商户账户
     * @return 结果
     */
    @Override
    public int insertMerAccount(MerAccount merAccount)
    {
        return merAccountMapper.insertMerAccount(merAccount);
    }

    /**
     * 修改商户账户
     * 
     * @param merAccount 商户账户
     * @return 结果
     */
    @Override
    public int updateMerAccount(MerAccount merAccount)
    {
        return merAccountMapper.updateMerAccount(merAccount);
    }

    /**
     * 删除商户账户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMerAccountByIds(String ids)
    {
        return merAccountMapper.deleteMerAccountByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商户账户信息
     * 
     * @param id 商户账户ID
     * @return 结果
     */
    @Override
    public int deleteMerAccountById(Long id)
    {
        return merAccountMapper.deleteMerAccountById(id);
    }
}
