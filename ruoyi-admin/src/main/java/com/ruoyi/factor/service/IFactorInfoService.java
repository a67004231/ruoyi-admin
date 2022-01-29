package com.ruoyi.factor.service;

import java.util.List;
import com.ruoyi.factor.domain.FactorInfo;

/**
 * 路由因子Service接口
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
public interface IFactorInfoService 
{
    /**
     * 查询路由因子
     * 
     * @param id 路由因子ID
     * @return 路由因子
     */
    public FactorInfo selectFactorInfoById(Long id);

    /**
     * 查询路由因子列表
     * 
     * @param factorInfo 路由因子
     * @return 路由因子集合
     */
    public List<FactorInfo> selectFactorInfoList(FactorInfo factorInfo);

    /**
     * 新增路由因子
     * 
     * @param factorInfo 路由因子
     * @return 结果
     */
    public int insertFactorInfo(FactorInfo factorInfo);

    /**
     * 修改路由因子
     * 
     * @param factorInfo 路由因子
     * @return 结果
     */
    public int updateFactorInfo(FactorInfo factorInfo);

    /**
     * 批量删除路由因子
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFactorInfoByIds(String ids);

    /**
     * 删除路由因子信息
     * 
     * @param id 路由因子ID
     * @return 结果
     */
    public int deleteFactorInfoById(Long id);
}
