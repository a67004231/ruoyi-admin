package com.ruoyi.factor.mapper;

import java.util.List;
import com.ruoyi.factor.domain.FactorType;

/**
 * 路由因子类型Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
public interface FactorTypeMapper 
{
    /**
     * 查询路由因子类型
     * 
     * @param id 路由因子类型ID
     * @return 路由因子类型
     */
    public FactorType selectFactorTypeById(Long id);

    /**
     * 查询路由因子类型列表
     * 
     * @param factorType 路由因子类型
     * @return 路由因子类型集合
     */
    public List<FactorType> selectFactorTypeList(FactorType factorType);

    /**
     * 新增路由因子类型
     * 
     * @param factorType 路由因子类型
     * @return 结果
     */
    public int insertFactorType(FactorType factorType);

    /**
     * 修改路由因子类型
     * 
     * @param factorType 路由因子类型
     * @return 结果
     */
    public int updateFactorType(FactorType factorType);

    /**
     * 删除路由因子类型
     * 
     * @param id 路由因子类型ID
     * @return 结果
     */
    public int deleteFactorTypeById(Long id);

    /**
     * 批量删除路由因子类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFactorTypeByIds(String[] ids);
}
