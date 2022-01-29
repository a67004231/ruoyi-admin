package com.ruoyi.channel.mapper;

import java.util.List;
import com.ruoyi.channel.domain.ChannelStart;

/**
 * 通道初始化Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-10
 */
public interface ChannelStartMapper 
{
    /**
     * 查询通道初始化
     * 
     * @param id 通道初始化ID
     * @return 通道初始化
     */
    public ChannelStart selectChannelStartById(Long id);

    /**
     * 查询通道初始化列表
     * 
     * @param channelStart 通道初始化
     * @return 通道初始化集合
     */
    public List<ChannelStart> selectChannelStartList(ChannelStart channelStart);

    /**
     * 新增通道初始化
     * 
     * @param channelStart 通道初始化
     * @return 结果
     */
    public int insertChannelStart(ChannelStart channelStart);

    /**
     * 修改通道初始化
     * 
     * @param channelStart 通道初始化
     * @return 结果
     */
    public int updateChannelStart(ChannelStart channelStart);

    /**
     * 删除通道初始化
     * 
     * @param id 通道初始化ID
     * @return 结果
     */
    public int deleteChannelStartById(Long id);

    /**
     * 批量删除通道初始化
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChannelStartByIds(String[] ids);
}
