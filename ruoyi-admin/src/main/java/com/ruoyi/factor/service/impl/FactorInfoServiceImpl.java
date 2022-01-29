package com.ruoyi.factor.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.factor.mapper.FactorInfoMapper;
import com.ruoyi.factor.domain.FactorInfo;
import com.ruoyi.factor.service.IFactorInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 路由因子Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
@Service
public class FactorInfoServiceImpl implements IFactorInfoService 
{
    @Autowired
    private FactorInfoMapper factorInfoMapper;

    /**
     * 查询路由因子
     * 
     * @param id 路由因子ID
     * @return 路由因子
     */
    @Override
    public FactorInfo selectFactorInfoById(Long id)
    {
        return factorInfoMapper.selectFactorInfoById(id);
    }

    /**
     * 查询路由因子列表
     * 
     * @param factorInfo 路由因子
     * @return 路由因子
     */
    @Override
    public List<FactorInfo> selectFactorInfoList(FactorInfo factorInfo)
    {
        return factorInfoMapper.selectFactorInfoList(factorInfo);
    }

    /**
     * 新增路由因子
     * 
     * @param factorInfo 路由因子
     * @return 结果
     */
    @Override
    public int insertFactorInfo(FactorInfo factorInfo)
    {
        factorInfo.setCreateTime(DateUtils.getNowDate());
        return factorInfoMapper.insertFactorInfo(factorInfo);
    }

    /**
     * 修改路由因子
     * 
     * @param factorInfo 路由因子
     * @return 结果
     */
    @Override
    public int updateFactorInfo(FactorInfo factorInfo)
    {
        factorInfo.setUpdateTime(DateUtils.getNowDate());
        return factorInfoMapper.updateFactorInfo(factorInfo);
    }

    /**
     * 删除路由因子对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFactorInfoByIds(String ids)
    {
        return factorInfoMapper.deleteFactorInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除路由因子信息
     * 
     * @param id 路由因子ID
     * @return 结果
     */
    @Override
    public int deleteFactorInfoById(Long id)
    {
        return factorInfoMapper.deleteFactorInfoById(id);
    }
}
