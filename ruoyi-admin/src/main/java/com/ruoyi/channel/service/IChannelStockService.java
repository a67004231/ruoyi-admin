package com.ruoyi.channel.service;

import java.util.List;
import com.ruoyi.channel.domain.ChannelStock;

/**
 * 通道库存信息Service接口
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
public interface IChannelStockService 
{
    /**
     * 查询通道库存信息
     * 
     * @param id 通道库存信息ID
     * @return 通道库存信息
     */
    public ChannelStock selectChannelStockById(Long id);

    /**
     * 查询通道库存信息列表
     * 
     * @param channelStock 通道库存信息
     * @return 通道库存信息集合
     */
    public List<ChannelStock> selectChannelStockList(ChannelStock channelStock);

    /**
     * 新增通道库存信息
     * 
     * @param channelStock 通道库存信息
     * @return 结果
     */
    public int insertChannelStock(ChannelStock channelStock);

    /**
     * 修改通道库存信息
     * 
     * @param channelStock 通道库存信息
     * @return 结果
     */
    public int updateChannelStock(ChannelStock channelStock);

    /**
     * 批量删除通道库存信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChannelStockByIds(String ids);

    /**
     * 删除通道库存信息信息
     * 
     * @param id 通道库存信息ID
     * @return 结果
     */
    public int deleteChannelStockById(Long id);
}
