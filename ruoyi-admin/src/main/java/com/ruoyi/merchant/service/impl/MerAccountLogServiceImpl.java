package com.ruoyi.merchant.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.merchant.mapper.MerAccountLogMapper;
import com.ruoyi.merchant.domain.MerAccountLog;
import com.ruoyi.merchant.service.IMerAccountLogService;
import com.ruoyi.common.core.text.Convert;

/**
 * 账户变更记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
@Service
public class MerAccountLogServiceImpl implements IMerAccountLogService 
{
    @Autowired
    private MerAccountLogMapper merAccountLogMapper;

    /**
     * 查询账户变更记录
     * 
     * @param id 账户变更记录ID
     * @return 账户变更记录
     */
    @Override
    public MerAccountLog selectMerAccountLogById(Long id)
    {
        return merAccountLogMapper.selectMerAccountLogById(id);
    }

    /**
     * 查询账户变更记录列表
     * 
     * @param merAccountLog 账户变更记录
     * @return 账户变更记录
     */
    @Override
    public List<MerAccountLog> selectMerAccountLogList(MerAccountLog merAccountLog)
    {
        return merAccountLogMapper.selectMerAccountLogList(merAccountLog);
    }

    /**
     * 新增账户变更记录
     * 
     * @param merAccountLog 账户变更记录
     * @return 结果
     */
    @Override
    public int insertMerAccountLog(MerAccountLog merAccountLog)
    {
        merAccountLog.setCreateTime(DateUtils.getNowDate());
        return merAccountLogMapper.insertMerAccountLog(merAccountLog);
    }

    /**
     * 修改账户变更记录
     * 
     * @param merAccountLog 账户变更记录
     * @return 结果
     */
    @Override
    public int updateMerAccountLog(MerAccountLog merAccountLog)
    {
        return merAccountLogMapper.updateMerAccountLog(merAccountLog);
    }

    /**
     * 删除账户变更记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMerAccountLogByIds(String ids)
    {
        return merAccountLogMapper.deleteMerAccountLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除账户变更记录信息
     * 
     * @param id 账户变更记录ID
     * @return 结果
     */
    @Override
    public int deleteMerAccountLogById(Long id)
    {
        return merAccountLogMapper.deleteMerAccountLogById(id);
    }
}
