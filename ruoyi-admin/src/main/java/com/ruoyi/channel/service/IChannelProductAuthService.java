package com.ruoyi.channel.service;

import java.util.List;
import com.ruoyi.channel.domain.ChannelProductAuth;

/**
 * 通道产品授权Service接口
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public interface IChannelProductAuthService 
{
    /**
     * 查询通道产品授权
     * 
     * @param id 通道产品授权ID
     * @return 通道产品授权
     */
    public ChannelProductAuth selectChannelProductAuthById(Long id);

    /**
     * 查询通道产品授权列表
     * 
     * @param channelProductAuth 通道产品授权
     * @return 通道产品授权集合
     */
    public List<ChannelProductAuth> selectChannelProductAuthList(ChannelProductAuth channelProductAuth);

    /**
     * 新增通道产品授权
     * 
     * @param channelProductAuth 通道产品授权
     * @return 结果
     */
    public int insertChannelProductAuth(ChannelProductAuth channelProductAuth);

    /**
     * 修改通道产品授权
     * 
     * @param channelProductAuth 通道产品授权
     * @return 结果
     */
    public int updateChannelProductAuth(ChannelProductAuth channelProductAuth);

    /**
     * 批量删除通道产品授权
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChannelProductAuthByIds(String ids);

    /**
     * 删除通道产品授权信息
     * 
     * @param id 通道产品授权ID
     * @return 结果
     */
    public int deleteChannelProductAuthById(Long id);
}
