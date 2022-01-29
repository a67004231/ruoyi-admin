package com.ruoyi.factor.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.factor.mapper.FactorTypeMapper;
import com.ruoyi.factor.domain.FactorType;
import com.ruoyi.factor.service.IFactorTypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 路由因子类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
@Service
public class FactorTypeServiceImpl implements IFactorTypeService 
{
    @Autowired
    private FactorTypeMapper factorTypeMapper;

    /**
     * 查询路由因子类型
     * 
     * @param id 路由因子类型ID
     * @return 路由因子类型
     */
    @Override
    public FactorType selectFactorTypeById(Long id)
    {
        return factorTypeMapper.selectFactorTypeById(id);
    }

    /**
     * 查询路由因子类型列表
     * 
     * @param factorType 路由因子类型
     * @return 路由因子类型
     */
    @Override
    public List<FactorType> selectFactorTypeList(FactorType factorType)
    {
        return factorTypeMapper.selectFactorTypeList(factorType);
    }

    /**
     * 新增路由因子类型
     * 
     * @param factorType 路由因子类型
     * @return 结果
     */
    @Override
    public int insertFactorType(FactorType factorType)
    {
        factorType.setCreateTime(DateUtils.getNowDate());
        return factorTypeMapper.insertFactorType(factorType);
    }

    /**
     * 修改路由因子类型
     * 
     * @param factorType 路由因子类型
     * @return 结果
     */
    @Override
    public int updateFactorType(FactorType factorType)
    {
        factorType.setUpdateTime(DateUtils.getNowDate());
        return factorTypeMapper.updateFactorType(factorType);
    }

    /**
     * 删除路由因子类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFactorTypeByIds(String ids)
    {
        return factorTypeMapper.deleteFactorTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除路由因子类型信息
     * 
     * @param id 路由因子类型ID
     * @return 结果
     */
    @Override
    public int deleteFactorTypeById(Long id)
    {
        return factorTypeMapper.deleteFactorTypeById(id);
    }
}
