package com.ruoyi.factor.service;

import java.util.List;
import com.ruoyi.factor.domain.FactorLog;

/**
 * 通道路由日志Service接口
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
public interface IFactorLogService 
{
    /**
     * 查询通道路由日志
     * 
     * @param id 通道路由日志ID
     * @return 通道路由日志
     */
    public FactorLog selectFactorLogById(Long id);

    /**
     * 查询通道路由日志列表
     * 
     * @param factorLog 通道路由日志
     * @return 通道路由日志集合
     */
    public List<FactorLog> selectFactorLogList(FactorLog factorLog);

    /**
     * 新增通道路由日志
     * 
     * @param factorLog 通道路由日志
     * @return 结果
     */
    public int insertFactorLog(FactorLog factorLog);

    /**
     * 修改通道路由日志
     * 
     * @param factorLog 通道路由日志
     * @return 结果
     */
    public int updateFactorLog(FactorLog factorLog);

    /**
     * 批量删除通道路由日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFactorLogByIds(String ids);

    /**
     * 删除通道路由日志信息
     * 
     * @param id 通道路由日志ID
     * @return 结果
     */
    public int deleteFactorLogById(Long id);
}
