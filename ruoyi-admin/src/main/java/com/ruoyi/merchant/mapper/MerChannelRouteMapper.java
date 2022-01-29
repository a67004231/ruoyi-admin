package com.ruoyi.merchant.mapper;

import java.util.List;
import com.ruoyi.merchant.domain.MerChannelRoute;

/**
 * 商户通道路由Mapper接口
 * 
 * @author ruoyi
 * @date 2021-08-20
 */
public interface MerChannelRouteMapper 
{
    /**
     * 查询商户通道路由
     * 
     * @param id 商户通道路由ID
     * @return 商户通道路由
     */
    public MerChannelRoute selectMerChannelRouteById(Long id);

    /**
     * 查询商户通道路由列表
     * 
     * @param merChannelRoute 商户通道路由
     * @return 商户通道路由集合
     */
    public List<MerChannelRoute> selectMerChannelRouteList(MerChannelRoute merChannelRoute);

    /**
     * 新增商户通道路由
     * 
     * @param merChannelRoute 商户通道路由
     * @return 结果
     */
    public int insertMerChannelRoute(MerChannelRoute merChannelRoute);

    /**
     * 修改商户通道路由
     * 
     * @param merChannelRoute 商户通道路由
     * @return 结果
     */
    public int updateMerChannelRoute(MerChannelRoute merChannelRoute);

    /**
     * 删除商户通道路由
     * 
     * @param id 商户通道路由ID
     * @return 结果
     */
    public int deleteMerChannelRouteById(Long id);

    /**
     * 批量删除商户通道路由
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerChannelRouteByIds(String[] ids);
}
