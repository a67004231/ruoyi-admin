package com.ruoyi.capital.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.capital.mapper.CapitalPayLogMapper;
import com.ruoyi.capital.domain.CapitalPayLog;
import com.ruoyi.capital.service.ICapitalPayLogService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资金转账记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
@Service
public class CapitalPayLogServiceImpl implements ICapitalPayLogService 
{
    @Autowired
    private CapitalPayLogMapper capitalPayLogMapper;

    @Override
    public CapitalPayLog selectCapitalTotalToday(CapitalPayLog capitalPayLog) {
        return capitalPayLogMapper.selectCapitalTotalToday(capitalPayLog);
    }

    /**
     * 查询资金转账记录
     * 
     * @param id 资金转账记录ID
     * @return 资金转账记录
     */
    @Override
    public CapitalPayLog selectCapitalPayLogById(Long id)
    {
        return capitalPayLogMapper.selectCapitalPayLogById(id);
    }

    /**
     * 查询资金转账记录列表
     * 
     * @param capitalPayLog 资金转账记录
     * @return 资金转账记录
     */
    @Override
    public List<CapitalPayLog> selectCapitalPayLogList(CapitalPayLog capitalPayLog)
    {
        return capitalPayLogMapper.selectCapitalPayLogList(capitalPayLog);
    }

    /**
     * 新增资金转账记录
     * 
     * @param capitalPayLog 资金转账记录
     * @return 结果
     */
    @Override
    public int insertCapitalPayLog(CapitalPayLog capitalPayLog)
    {
        capitalPayLog.setCreateTime(DateUtils.getNowDate());
        return capitalPayLogMapper.insertCapitalPayLog(capitalPayLog);
    }

    /**
     * 修改资金转账记录
     * 
     * @param capitalPayLog 资金转账记录
     * @return 结果
     */
    @Override
    public int updateCapitalPayLog(CapitalPayLog capitalPayLog)
    {
        capitalPayLog.setUpdateTime(DateUtils.getNowDate());
        return capitalPayLogMapper.updateCapitalPayLog(capitalPayLog);
    }

    /**
     * 删除资金转账记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCapitalPayLogByIds(String ids)
    {
        return capitalPayLogMapper.deleteCapitalPayLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除资金转账记录信息
     * 
     * @param id 资金转账记录ID
     * @return 结果
     */
    @Override
    public int deleteCapitalPayLogById(Long id)
    {
        return capitalPayLogMapper.deleteCapitalPayLogById(id);
    }

    @Override
    public CapitalPayLog selectCapitalTotalYes(CapitalPayLog capitalPayLog) {
        return capitalPayLogMapper.selectCapitalTotalYes(capitalPayLog);
    }
}
