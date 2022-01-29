package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.OrderInfo;
import com.ruoyi.order.domain.OrderInfoReport;

/**
 * 充值通道订单Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
public interface OrderInfoMapper 
{
    /**
     * 查询充值通道订单
     * 
     * @param id 充值通道订单ID
     * @return 充值通道订单
     */
    public OrderInfo selectOrderInfoById(Long id);

    /**
     * 查询充值通道订单列表
     * 
     * @param orderInfo 充值通道订单
     * @return 充值通道订单集合
     */
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo);
    /**
     * 查询充值通道订单列表
     * 
     * @param orderInfo 充值通道订单
     * @return 充值通道订单集合
     */
    public List<OrderInfoReport> selectOrderInfoListReport(OrderInfo orderInfo);
    /**
     * 根据商户分组统计订单
     * 
     * @param orderInfo 充值通道订单
     * @return 充值通道订单集合
     */
    public List<OrderInfoReport> selectMerOrderInfoListReport(OrderInfo orderInfo);
    /**
     * 根据商户分组统计订单
     * 
     * @param orderInfo 充值通道订单
     * @return 充值通道订单集合
     */
    public List<OrderInfoReport> selectMerOrderInfoListAmtReport(OrderInfo orderInfo);
    /**
     * 根据通道分组统计订单
     * 
     * @param orderInfo 充值通道订单
     * @return 充值通道订单集合
     */
    public List<OrderInfoReport> selectChannelOrderInfoListReport(OrderInfo orderInfo);
    /**
     * 根据通道分组统计订单
     * 
     * @param orderInfo 充值通道订单
     * @return 充值通道订单集合
     */
    public List<OrderInfoReport> selectChannelOrderInfoListAmtReport(OrderInfo orderInfo);
    /**
     * 新增充值通道订单
     * 
     * @param orderInfo 充值通道订单
     * @return 结果
     */
    public int insertOrderInfo(OrderInfo orderInfo);

    /**
     * 修改充值通道订单
     * 
     * @param orderInfo 充值通道订单
     * @return 结果
     */
    public int updateOrderInfo(OrderInfo orderInfo);

    /**
     * 删除充值通道订单
     * 
     * @param id 充值通道订单ID
     * @return 结果
     */
    public int deleteOrderInfoById(Long id);

    /**
     * 批量删除充值通道订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderInfoByIds(String[] ids);
}
