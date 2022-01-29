package com.ruoyi.capital.mapper;

import java.util.List;
import com.ruoyi.capital.domain.CapitalPayLog;

/**
 * 资金转账记录Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
public interface CapitalPayLogMapper 
{

    public CapitalPayLog selectCapitalTotalToday(CapitalPayLog capitalPayLog);
    /**
     * 查询资金转账记录
     * 
     * @param id 资金转账记录ID
     * @return 资金转账记录
     */
    public CapitalPayLog selectCapitalPayLogById(Long id);

    /**
     * 查询资金转账记录列表
     * 
     * @param capitalPayLog 资金转账记录
     * @return 资金转账记录集合
     */
    public List<CapitalPayLog> selectCapitalPayLogList(CapitalPayLog capitalPayLog);

    /**
     * 新增资金转账记录
     * 
     * @param capitalPayLog 资金转账记录
     * @return 结果
     */
    public int insertCapitalPayLog(CapitalPayLog capitalPayLog);

    /**
     * 修改资金转账记录
     * 
     * @param capitalPayLog 资金转账记录
     * @return 结果
     */
    public int updateCapitalPayLog(CapitalPayLog capitalPayLog);

    /**
     * 删除资金转账记录
     * 
     * @param id 资金转账记录ID
     * @return 结果
     */
    public int deleteCapitalPayLogById(Long id);

    /**
     * 批量删除资金转账记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCapitalPayLogByIds(String[] ids);

    CapitalPayLog selectCapitalTotalYes(CapitalPayLog capitalPayLog);
}
