package com.ruoyi.channel.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.channel.mapper.ChannelAccountLogMapper;
import com.ruoyi.channel.domain.ChannelAccountLog;
import com.ruoyi.channel.service.IChannelAccountLogService;
import com.ruoyi.common.core.text.Convert;

/**
 * 通道账户变更记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
@Service
public class ChannelAccountLogServiceImpl implements IChannelAccountLogService 
{
    @Autowired
    private ChannelAccountLogMapper channelAccountLogMapper;

    /**
     * 查询通道账户变更记录
     * 
     * @param id 通道账户变更记录ID
     * @return 通道账户变更记录
     */
    @Override
    public ChannelAccountLog selectChannelAccountLogById(Long id)
    {
        return channelAccountLogMapper.selectChannelAccountLogById(id);
    }

    /**
     * 查询通道账户变更记录列表
     * 
     * @param channelAccountLog 通道账户变更记录
     * @return 通道账户变更记录
     */
    @Override
    public List<ChannelAccountLog> selectChannelAccountLogList(ChannelAccountLog channelAccountLog)
    {
        return channelAccountLogMapper.selectChannelAccountLogList(channelAccountLog);
    }

    /**
     * 新增通道账户变更记录
     * 
     * @param channelAccountLog 通道账户变更记录
     * @return 结果
     */
    @Override
    public int insertChannelAccountLog(ChannelAccountLog channelAccountLog)
    {
        channelAccountLog.setCreateTime(DateUtils.getNowDate());
        return channelAccountLogMapper.insertChannelAccountLog(channelAccountLog);
    }

    /**
     * 修改通道账户变更记录
     * 
     * @param channelAccountLog 通道账户变更记录
     * @return 结果
     */
    @Override
    public int updateChannelAccountLog(ChannelAccountLog channelAccountLog)
    {
        return channelAccountLogMapper.updateChannelAccountLog(channelAccountLog);
    }

    /**
     * 删除通道账户变更记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChannelAccountLogByIds(String ids)
    {
        return channelAccountLogMapper.deleteChannelAccountLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除通道账户变更记录信息
     * 
     * @param id 通道账户变更记录ID
     * @return 结果
     */
    @Override
    public int deleteChannelAccountLogById(Long id)
    {
        return channelAccountLogMapper.deleteChannelAccountLogById(id);
    }
}
