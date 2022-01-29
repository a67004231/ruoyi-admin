package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.ChannelOrderInfoMapper;
import com.ruoyi.order.domain.ChannelOrderInfo;
import com.ruoyi.order.service.IChannelOrderInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 通道订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-15
 */
@Service
public class ChannelOrderInfoServiceImpl implements IChannelOrderInfoService 
{
    @Autowired
    private ChannelOrderInfoMapper channelOrderInfoMapper;

    /**
     * 查询通道订单
     * 
     * @param id 通道订单ID
     * @return 通道订单
     */
    @Override
    public ChannelOrderInfo selectChannelOrderInfoById(Long id)
    {
        return channelOrderInfoMapper.selectChannelOrderInfoById(id);
    }

    /**
     * 查询通道订单列表
     * 
     * @param channelOrderInfo 通道订单
     * @return 通道订单
     */
    @Override
    public List<ChannelOrderInfo> selectChannelOrderInfoList(ChannelOrderInfo channelOrderInfo)
    {
        return channelOrderInfoMapper.selectChannelOrderInfoList(channelOrderInfo);
    }

    /**
     * 新增通道订单
     * 
     * @param channelOrderInfo 通道订单
     * @return 结果
     */
    @Override
    public int insertChannelOrderInfo(ChannelOrderInfo channelOrderInfo)
    {
        channelOrderInfo.setCreateTime(DateUtils.getNowDate());
        return channelOrderInfoMapper.insertChannelOrderInfo(channelOrderInfo);
    }

    /**
     * 修改通道订单
     * 
     * @param channelOrderInfo 通道订单
     * @return 结果
     */
    @Override
    public int updateChannelOrderInfo(ChannelOrderInfo channelOrderInfo)
    {
        return channelOrderInfoMapper.updateChannelOrderInfo(channelOrderInfo);
    }

    /**
     * 删除通道订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChannelOrderInfoByIds(String ids)
    {
        return channelOrderInfoMapper.deleteChannelOrderInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除通道订单信息
     * 
     * @param id 通道订单ID
     * @return 结果
     */
    @Override
    public int deleteChannelOrderInfoById(Long id)
    {
        return channelOrderInfoMapper.deleteChannelOrderInfoById(id);
    }
}
