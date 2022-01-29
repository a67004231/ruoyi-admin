package com.ruoyi.merchant.service;

import java.util.List;
import com.ruoyi.merchant.domain.MerProductAuth;

/**
 * 商户产品授权Service接口
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public interface IMerProductAuthService 
{
    /**
     * 查询商户产品授权
     * 
     * @param id 商户产品授权ID
     * @return 商户产品授权
     */
    public MerProductAuth selectMerProductAuthById(Long id);

    /**
     * 查询商户产品授权列表
     * 
     * @param merProductAuth 商户产品授权
     * @return 商户产品授权集合
     */
    public List<MerProductAuth> selectMerProductAuthList(MerProductAuth merProductAuth);

    /**
     * 新增商户产品授权
     * 
     * @param merProductAuth 商户产品授权
     * @return 结果
     */
    public int insertMerProductAuth(MerProductAuth merProductAuth);

    /**
     * 修改商户产品授权
     * 
     * @param merProductAuth 商户产品授权
     * @return 结果
     */
    public int updateMerProductAuth(MerProductAuth merProductAuth);

    /**
     * 批量删除商户产品授权
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerProductAuthByIds(String ids);

    /**
     * 删除商户产品授权信息
     * 
     * @param id 商户产品授权ID
     * @return 结果
     */
    public int deleteMerProductAuthById(Long id);

    int changeRate(MerProductAuth merProductAuth);
}
