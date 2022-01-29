package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.OrderInfoMapper;
import com.ruoyi.order.domain.OrderInfo;
import com.ruoyi.order.domain.OrderInfoReport;
import com.ruoyi.order.service.IOrderInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 充值通道订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
@Service
public class OrderInfoServiceImpl implements IOrderInfoService 
{
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 查询充值通道订单
     * 
     * @param id 充值通道订单ID
     * @return 充值通道订单
     */
    @Override
    public OrderInfo selectOrderInfoById(Long id)
    {
        return orderInfoMapper.selectOrderInfoById(id);
    }

    /**
     * 查询充值通道订单列表
     * 
     * @param orderInfo 充值通道订单
     * @return 充值通道订单
     */
    @Override
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo)
    {
        return orderInfoMapper.selectOrderInfoList(orderInfo);
    }
    /**
     * 查询充值通道订单列表
     * 
     * @param orderInfo 充值通道订单
     * @return 充值通道订单
     */
    @Override
    public List<OrderInfoReport> selectOrderInfoListReport(OrderInfo orderInfo)
    {
    	return orderInfoMapper.selectOrderInfoListReport(orderInfo);
    }

    /**
     * 新增充值通道订单
     * 
     * @param orderInfo 充值通道订单
     * @return 结果
     */
    @Override
    public int insertOrderInfo(OrderInfo orderInfo)
    {
        orderInfo.setCreateTime(DateUtils.getNowDate());
        return orderInfoMapper.insertOrderInfo(orderInfo);
    }

    /**
     * 修改充值通道订单
     * 
     * @param orderInfo 充值通道订单
     * @return 结果
     */
    @Override
    public int updateOrderInfo(OrderInfo orderInfo)
    {
        orderInfo.setUpdateTime(DateUtils.getNowDate());
        return orderInfoMapper.updateOrderInfo(orderInfo);
    }

    /**
     * 删除充值通道订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrderInfoByIds(String ids)
    {
        return orderInfoMapper.deleteOrderInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除充值通道订单信息
     * 
     * @param id 充值通道订单ID
     * @return 结果
     */
    @Override
    public int deleteOrderInfoById(Long id)
    {
        return orderInfoMapper.deleteOrderInfoById(id);
    }

	@Override
	public List<OrderInfoReport> selectMerOrderInfoListReport(OrderInfo orderInfo) {
		// TODO Auto-generated method stub
		return orderInfoMapper.selectMerOrderInfoListReport(orderInfo);
	}
	@Override
	public List<OrderInfoReport> selectMerOrderInfoListAmtReport(OrderInfo orderInfo) {
		// TODO Auto-generated method stub
		return orderInfoMapper.selectMerOrderInfoListAmtReport(orderInfo);
	}

	@Override
	public List<OrderInfoReport> selectChannelOrderInfoListReport(OrderInfo orderInfo) {
		// TODO Auto-generated method stub
		return orderInfoMapper.selectChannelOrderInfoListReport(orderInfo);
	}
	@Override
	public List<OrderInfoReport> selectChannelOrderInfoListAmtReport(OrderInfo orderInfo) {
		// TODO Auto-generated method stub
		return orderInfoMapper.selectChannelOrderInfoListAmtReport(orderInfo);
	}
}
