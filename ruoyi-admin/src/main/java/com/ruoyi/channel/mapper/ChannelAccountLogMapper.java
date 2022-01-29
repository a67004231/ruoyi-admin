package com.ruoyi.channel.mapper;

import java.util.List;
import com.ruoyi.channel.domain.ChannelAccountLog;

/**
 * 通道账户变更记录Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
public interface ChannelAccountLogMapper 
{
    /**
     * 查询通道账户变更记录
     * 
     * @param id 通道账户变更记录ID
     * @return 通道账户变更记录
     */
    public ChannelAccountLog selectChannelAccountLogById(Long id);

    /**
     * 查询通道账户变更记录列表
     * 
     * @param channelAccountLog 通道账户变更记录
     * @return 通道账户变更记录集合
     */
    public List<ChannelAccountLog> selectChannelAccountLogList(ChannelAccountLog channelAccountLog);

    /**
     * 新增通道账户变更记录
     * 
     * @param channelAccountLog 通道账户变更记录
     * @return 结果
     */
    public int insertChannelAccountLog(ChannelAccountLog channelAccountLog);

    /**
     * 修改通道账户变更记录
     * 
     * @param channelAccountLog 通道账户变更记录
     * @return 结果
     */
    public int updateChannelAccountLog(ChannelAccountLog channelAccountLog);

    /**
     * 删除通道账户变更记录
     * 
     * @param id 通道账户变更记录ID
     * @return 结果
     */
    public int deleteChannelAccountLogById(Long id);

    /**
     * 批量删除通道账户变更记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChannelAccountLogByIds(String[] ids);
}
