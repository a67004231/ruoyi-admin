package com.ruoyi.merchant.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.merchant.mapper.MerChannelRouteMapper;
import com.ruoyi.merchant.domain.MerChannelRoute;
import com.ruoyi.merchant.service.IMerChannelRouteService;
import com.ruoyi.common.core.text.Convert;

/**
 * 商户通道路由Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-08-20
 */
@Service
public class MerChannelRouteServiceImpl implements IMerChannelRouteService 
{
    @Autowired
    private MerChannelRouteMapper merChannelRouteMapper;

    /**
     * 查询商户通道路由
     * 
     * @param id 商户通道路由ID
     * @return 商户通道路由
     */
    @Override
    public MerChannelRoute selectMerChannelRouteById(Long id)
    {
        return merChannelRouteMapper.selectMerChannelRouteById(id);
    }

    /**
     * 查询商户通道路由列表
     * 
     * @param merChannelRoute 商户通道路由
     * @return 商户通道路由
     */
    @Override
    public List<MerChannelRoute> selectMerChannelRouteList(MerChannelRoute merChannelRoute)
    {
        return merChannelRouteMapper.selectMerChannelRouteList(merChannelRoute);
    }

    /**
     * 新增商户通道路由
     * 
     * @param merChannelRoute 商户通道路由
     * @return 结果
     */
    @Override
    public int insertMerChannelRoute(MerChannelRoute merChannelRoute)
    {
        merChannelRoute.setCreateTime(DateUtils.getNowDate());
        return merChannelRouteMapper.insertMerChannelRoute(merChannelRoute);
    }

    /**
     * 修改商户通道路由
     * 
     * @param merChannelRoute 商户通道路由
     * @return 结果
     */
    @Override
    public int updateMerChannelRoute(MerChannelRoute merChannelRoute)
    {
        merChannelRoute.setUpdateTime(DateUtils.getNowDate());
        return merChannelRouteMapper.updateMerChannelRoute(merChannelRoute);
    }

    /**
     * 删除商户通道路由对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMerChannelRouteByIds(String ids)
    {
        return merChannelRouteMapper.deleteMerChannelRouteByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商户通道路由信息
     * 
     * @param id 商户通道路由ID
     * @return 结果
     */
    @Override
    public int deleteMerChannelRouteById(Long id)
    {
        return merChannelRouteMapper.deleteMerChannelRouteById(id);
    }
}
