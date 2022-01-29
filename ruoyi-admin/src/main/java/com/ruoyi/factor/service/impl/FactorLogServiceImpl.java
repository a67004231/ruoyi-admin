package com.ruoyi.factor.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.factor.mapper.FactorLogMapper;
import com.ruoyi.factor.domain.FactorLog;
import com.ruoyi.factor.service.IFactorLogService;
import com.ruoyi.common.core.text.Convert;

/**
 * 通道路由日志Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
@Service
public class FactorLogServiceImpl implements IFactorLogService 
{
    @Autowired
    private FactorLogMapper factorLogMapper;

    /**
     * 查询通道路由日志
     * 
     * @param id 通道路由日志ID
     * @return 通道路由日志
     */
    @Override
    public FactorLog selectFactorLogById(Long id)
    {
        return factorLogMapper.selectFactorLogById(id);
    }

    /**
     * 查询通道路由日志列表
     * 
     * @param factorLog 通道路由日志
     * @return 通道路由日志
     */
    @Override
    public List<FactorLog> selectFactorLogList(FactorLog factorLog)
    {
        return factorLogMapper.selectFactorLogList(factorLog);
    }

    /**
     * 新增通道路由日志
     * 
     * @param factorLog 通道路由日志
     * @return 结果
     */
    @Override
    public int insertFactorLog(FactorLog factorLog)
    {
        factorLog.setCreateTime(DateUtils.getNowDate());
        return factorLogMapper.insertFactorLog(factorLog);
    }

    /**
     * 修改通道路由日志
     * 
     * @param factorLog 通道路由日志
     * @return 结果
     */
    @Override
    public int updateFactorLog(FactorLog factorLog)
    {
        return factorLogMapper.updateFactorLog(factorLog);
    }

    /**
     * 删除通道路由日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFactorLogByIds(String ids)
    {
        return factorLogMapper.deleteFactorLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除通道路由日志信息
     * 
     * @param id 通道路由日志ID
     * @return 结果
     */
    @Override
    public int deleteFactorLogById(Long id)
    {
        return factorLogMapper.deleteFactorLogById(id);
    }
}
