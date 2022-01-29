package com.ruoyi.capital.mapper;

import java.util.List;
import com.ruoyi.capital.domain.CapitalInfoLog;

/**
 * 资金日志Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
public interface CapitalInfoLogMapper 
{
    /**
     * 查询资金日志
     * 
     * @param id 资金日志ID
     * @return 资金日志
     */
    public CapitalInfoLog selectCapitalInfoLogById(Long id);

    /**
     * 查询资金日志列表
     * 
     * @param capitalInfoLog 资金日志
     * @return 资金日志集合
     */
    public List<CapitalInfoLog> selectCapitalInfoLogList(CapitalInfoLog capitalInfoLog);

    /**
     * 新增资金日志
     * 
     * @param capitalInfoLog 资金日志
     * @return 结果
     */
    public int insertCapitalInfoLog(CapitalInfoLog capitalInfoLog);

    /**
     * 修改资金日志
     * 
     * @param capitalInfoLog 资金日志
     * @return 结果
     */
    public int updateCapitalInfoLog(CapitalInfoLog capitalInfoLog);

    /**
     * 删除资金日志
     * 
     * @param id 资金日志ID
     * @return 结果
     */
    public int deleteCapitalInfoLogById(Long id);

    /**
     * 批量删除资金日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCapitalInfoLogByIds(String[] ids);

    CapitalInfoLog selectCapitalTotal(CapitalInfoLog capitalInfoLog);
}
