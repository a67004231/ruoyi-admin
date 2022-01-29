package com.ruoyi.merchant.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.merchant.mapper.MerProductAuthMapper;
import com.ruoyi.merchant.domain.MerProductAuth;
import com.ruoyi.merchant.service.IMerProductAuthService;
import com.ruoyi.common.core.text.Convert;

/**
 * 商户产品授权Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Service
public class MerProductAuthServiceImpl implements IMerProductAuthService 
{
    @Autowired
    private MerProductAuthMapper merProductAuthMapper;

    /**
     * 查询商户产品授权
     * 
     * @param id 商户产品授权ID
     * @return 商户产品授权
     */
    @Override
    public MerProductAuth selectMerProductAuthById(Long id)
    {
        return merProductAuthMapper.selectMerProductAuthById(id);
    }

    /**
     * 查询商户产品授权列表
     * 
     * @param merProductAuth 商户产品授权
     * @return 商户产品授权
     */
    @Override
    public List<MerProductAuth> selectMerProductAuthList(MerProductAuth merProductAuth)
    {
        return merProductAuthMapper.selectMerProductAuthList(merProductAuth);
    }

    /**
     * 新增商户产品授权
     * 
     * @param merProductAuth 商户产品授权
     * @return 结果
     */
    @Override
    public int insertMerProductAuth(MerProductAuth merProductAuth)
    {
        merProductAuth.setCreateTime(DateUtils.getNowDate());
        merProductAuth.setUpdateTime(DateUtils.getNowDate());
        return merProductAuthMapper.insertMerProductAuth(merProductAuth);
    }

    /**
     * 修改商户产品授权
     * 
     * @param merProductAuth 商户产品授权
     * @return 结果
     */
    @Override
    public int updateMerProductAuth(MerProductAuth merProductAuth)
    {
        merProductAuth.setUpdateTime(DateUtils.getNowDate());
        return merProductAuthMapper.updateMerProductAuth(merProductAuth);
    }

    /**
     * 删除商户产品授权对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMerProductAuthByIds(String ids)
    {
        return merProductAuthMapper.deleteMerProductAuthByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商户产品授权信息
     * 
     * @param id 商户产品授权ID
     * @return 结果
     */
    @Override
    public int deleteMerProductAuthById(Long id)
    {
        return merProductAuthMapper.deleteMerProductAuthById(id);
    }

    @Override
    public int changeRate(MerProductAuth merProductAuth) {
        return merProductAuthMapper.updateMerProductAuth(merProductAuth);
    }
}
