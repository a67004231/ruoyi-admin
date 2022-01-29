package com.ruoyi.channel.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.channel.mapper.ChannelStockMapper;
import com.ruoyi.channel.domain.ChannelStock;
import com.ruoyi.channel.service.IChannelStockService;
import com.ruoyi.common.core.text.Convert;

/**
 * 通道库存信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
@Service
public class ChannelStockServiceImpl implements IChannelStockService 
{
    @Autowired
    private ChannelStockMapper channelStockMapper;

    /**
     * 查询通道库存信息
     * 
     * @param id 通道库存信息ID
     * @return 通道库存信息
     */
    @Override
    public ChannelStock selectChannelStockById(Long id)
    {
        return channelStockMapper.selectChannelStockById(id);
    }

    /**
     * 查询通道库存信息列表
     * 
     * @param channelStock 通道库存信息
     * @return 通道库存信息
     */
    @Override
    public List<ChannelStock> selectChannelStockList(ChannelStock channelStock)
    {
        return channelStockMapper.selectChannelStockList(channelStock);
    }

    /**
     * 新增通道库存信息
     * 
     * @param channelStock 通道库存信息
     * @return 结果
     */
    @Override
    public int insertChannelStock(ChannelStock channelStock)
    {
        channelStock.setCreateTime(DateUtils.getNowDate());
        return channelStockMapper.insertChannelStock(channelStock);
    }

    /**
     * 修改通道库存信息
     * 
     * @param channelStock 通道库存信息
     * @return 结果
     */
    @Override
    public int updateChannelStock(ChannelStock channelStock)
    {
        channelStock.setUpdateTime(DateUtils.getNowDate());
        return channelStockMapper.updateChannelStock(channelStock);
    }

    /**
     * 删除通道库存信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChannelStockByIds(String ids)
    {
        return channelStockMapper.deleteChannelStockByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除通道库存信息信息
     * 
     * @param id 通道库存信息ID
     * @return 结果
     */
    @Override
    public int deleteChannelStockById(Long id)
    {
        return channelStockMapper.deleteChannelStockById(id);
    }
}
