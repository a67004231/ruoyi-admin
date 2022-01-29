package com.ruoyi.merchant.service;

import java.util.List;
import com.ruoyi.merchant.domain.MerSecretKey;

/**
 * 商户秘钥管理Service接口
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public interface IMerSecretKeyService 
{
    /**
     * 查询商户秘钥管理
     * 
     * @param id 商户秘钥管理ID
     * @return 商户秘钥管理
     */
    public MerSecretKey selectMerSecretKeyById(Long id);

    /**
     * 查询商户秘钥管理列表
     * 
     * @param merSecretKey 商户秘钥管理
     * @return 商户秘钥管理集合
     */
    public List<MerSecretKey> selectMerSecretKeyList(MerSecretKey merSecretKey);

    /**
     * 新增商户秘钥管理
     * 
     * @param merSecretKey 商户秘钥管理
     * @return 结果
     */
    public int insertMerSecretKey(MerSecretKey merSecretKey);

    /**
     * 修改商户秘钥管理
     * 
     * @param merSecretKey 商户秘钥管理
     * @return 结果
     */
    public int updateMerSecretKey(MerSecretKey merSecretKey);

    /**
     * 批量删除商户秘钥管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerSecretKeyByIds(String ids);

    /**
     * 删除商户秘钥管理信息
     * 
     * @param id 商户秘钥管理ID
     * @return 结果
     */
    public int deleteMerSecretKeyById(Long id);
}
