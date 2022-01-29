package com.ruoyi.merchant.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.merchant.mapper.MerSecretKeyMapper;
import com.ruoyi.merchant.domain.MerSecretKey;
import com.ruoyi.merchant.service.IMerSecretKeyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 商户秘钥管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Service
public class MerSecretKeyServiceImpl implements IMerSecretKeyService 
{
    @Autowired
    private MerSecretKeyMapper merSecretKeyMapper;

    /**
     * 查询商户秘钥管理
     * 
     * @param id 商户秘钥管理ID
     * @return 商户秘钥管理
     */
    @Override
    public MerSecretKey selectMerSecretKeyById(Long id)
    {
        return merSecretKeyMapper.selectMerSecretKeyById(id);
    }

    /**
     * 查询商户秘钥管理列表
     * 
     * @param merSecretKey 商户秘钥管理
     * @return 商户秘钥管理
     */
    @Override
    public List<MerSecretKey> selectMerSecretKeyList(MerSecretKey merSecretKey)
    {
        return merSecretKeyMapper.selectMerSecretKeyList(merSecretKey);
    }

    /**
     * 新增商户秘钥管理
     * 
     * @param merSecretKey 商户秘钥管理
     * @return 结果
     */
    @Override
    public int insertMerSecretKey(MerSecretKey merSecretKey)
    {
        merSecretKey.setCreateTime(DateUtils.getNowDate());
        return merSecretKeyMapper.insertMerSecretKey(merSecretKey);
    }

    /**
     * 修改商户秘钥管理
     * 
     * @param merSecretKey 商户秘钥管理
     * @return 结果
     */
    @Override
    public int updateMerSecretKey(MerSecretKey merSecretKey)
    {
        merSecretKey.setUpdateTime(DateUtils.getNowDate());
        return merSecretKeyMapper.updateMerSecretKey(merSecretKey);
    }

    /**
     * 删除商户秘钥管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMerSecretKeyByIds(String ids)
    {
        return merSecretKeyMapper.deleteMerSecretKeyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商户秘钥管理信息
     * 
     * @param id 商户秘钥管理ID
     * @return 结果
     */
    @Override
    public int deleteMerSecretKeyById(Long id)
    {
        return merSecretKeyMapper.deleteMerSecretKeyById(id);
    }
}
