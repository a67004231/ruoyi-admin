package com.ruoyi.order.service;

import java.util.List;
import com.ruoyi.order.domain.ChannelOrderInfo;

/**
 * 通道订单Service接口
 * 
 * @author ruoyi
 * @date 2021-06-15
 */
public interface IChannelOrderInfoService 
{
    /**
     * 查询通道订单
     * 
     * @param id 通道订单ID
     * @return 通道订单
     */
    public ChannelOrderInfo selectChannelOrderInfoById(Long id);

    /**
     * 查询通道订单列表
     * 
     * @param channelOrderInfo 通道订单
     * @return 通道订单集合
     */
    public List<ChannelOrderInfo> selectChannelOrderInfoList(ChannelOrderInfo channelOrderInfo);

    /**
     * 新增通道订单
     * 
     * @param channelOrderInfo 通道订单
     * @return 结果
     */
    public int insertChannelOrderInfo(ChannelOrderInfo channelOrderInfo);

    /**
     * 修改通道订单
     * 
     * @param channelOrderInfo 通道订单
     * @return 结果
     */
    public int updateChannelOrderInfo(ChannelOrderInfo channelOrderInfo);

    /**
     * 批量删除通道订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChannelOrderInfoByIds(String ids);

    /**
     * 删除通道订单信息
     * 
     * @param id 通道订单ID
     * @return 结果
     */
    public int deleteChannelOrderInfoById(Long id);
}
