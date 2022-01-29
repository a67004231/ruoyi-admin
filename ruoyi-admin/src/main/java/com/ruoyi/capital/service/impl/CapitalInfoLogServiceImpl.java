package com.ruoyi.capital.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.capital.mapper.CapitalInfoLogMapper;
import com.ruoyi.capital.domain.CapitalInfoLog;
import com.ruoyi.capital.service.ICapitalInfoLogService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资金日志Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
@Service
public class CapitalInfoLogServiceImpl implements ICapitalInfoLogService 
{
    @Autowired
    private CapitalInfoLogMapper capitalInfoLogMapper;

    /**
     * 查询资金日志
     * 
     * @param id 资金日志ID
     * @return 资金日志
     */
    @Override
    public CapitalInfoLog selectCapitalInfoLogById(Long id)
    {
        return capitalInfoLogMapper.selectCapitalInfoLogById(id);
    }

    /**
     * 查询资金日志列表
     * 
     * @param capitalInfoLog 资金日志
     * @return 资金日志
     */
    @Override
    public List<CapitalInfoLog> selectCapitalInfoLogList(CapitalInfoLog capitalInfoLog)
    {
        return capitalInfoLogMapper.selectCapitalInfoLogList(capitalInfoLog);
    }

    /**
     * 新增资金日志
     * 
     * @param capitalInfoLog 资金日志
     * @return 结果
     */
    @Override
    public int insertCapitalInfoLog(CapitalInfoLog capitalInfoLog)
    {
        capitalInfoLog.setCreateTime(DateUtils.getNowDate());
        return capitalInfoLogMapper.insertCapitalInfoLog(capitalInfoLog);
    }

    /**
     * 修改资金日志
     * 
     * @param capitalInfoLog 资金日志
     * @return 结果
     */
    @Override
    public int updateCapitalInfoLog(CapitalInfoLog capitalInfoLog)
    {
        capitalInfoLog.setUpdateTime(DateUtils.getNowDate());
        return capitalInfoLogMapper.updateCapitalInfoLog(capitalInfoLog);
    }

    /**
     * 删除资金日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCapitalInfoLogByIds(String ids)
    {
        return capitalInfoLogMapper.deleteCapitalInfoLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除资金日志信息
     * 
     * @param id 资金日志ID
     * @return 结果
     */
    @Override
    public int deleteCapitalInfoLogById(Long id)
    {
        return capitalInfoLogMapper.deleteCapitalInfoLogById(id);
    }

    @Override
    public CapitalInfoLog selectCapitalTotal(CapitalInfoLog capitalInfoLog) {
        return capitalInfoLogMapper.selectCapitalTotal(capitalInfoLog);
    }
}
