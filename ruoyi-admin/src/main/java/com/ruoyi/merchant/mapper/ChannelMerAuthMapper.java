package com.ruoyi.merchant.mapper;

import java.util.List;
import com.ruoyi.merchant.domain.ChannelMerAuth;

/**
 * 上下游关联Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
public interface ChannelMerAuthMapper 
{
    /**
     * 查询上下游关联
     * 
     * @param id 上下游关联ID
     * @return 上下游关联
     */
    public ChannelMerAuth selectChannelMerAuthById(Long id);

    /**
     * 查询上下游关联列表
     * 
     * @param channelMerAuth 上下游关联
     * @return 上下游关联集合
     */
    public List<ChannelMerAuth> selectChannelMerAuthList(ChannelMerAuth channelMerAuth);

    /**
     * 新增上下游关联
     * 
     * @param channelMerAuth 上下游关联
     * @return 结果
     */
    public int insertChannelMerAuth(ChannelMerAuth channelMerAuth);

    /**
     * 修改上下游关联
     * 
     * @param channelMerAuth 上下游关联
     * @return 结果
     */
    public int updateChannelMerAuth(ChannelMerAuth channelMerAuth);

    /**
     * 删除上下游关联
     * 
     * @param id 上下游关联ID
     * @return 结果
     */
    public int deleteChannelMerAuthById(Long id);

    /**
     * 批量删除上下游关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChannelMerAuthByIds(String[] ids);
}
