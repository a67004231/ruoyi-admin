package com.ruoyi.channel.service;

import java.util.List;
import com.ruoyi.channel.domain.ChannelAccount;

/**
 * 通道账户Service接口
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
public interface IChannelAccountService 
{
    /**
     * 查询通道账户
     * 
     * @param id 通道账户ID
     * @return 通道账户
     */
    public ChannelAccount selectChannelAccountById(Long id);

    /**
     * 查询通道账户列表
     * 
     * @param channelAccount 通道账户
     * @return 通道账户集合
     */
    public List<ChannelAccount> selectChannelAccountList(ChannelAccount channelAccount);

    /**
     * 新增通道账户
     * 
     * @param channelAccount 通道账户
     * @return 结果
     */
    public int insertChannelAccount(ChannelAccount channelAccount);

    /**
     * 修改通道账户
     * 
     * @param channelAccount 通道账户
     * @return 结果
     */
    public int updateChannelAccount(ChannelAccount channelAccount);

    /**
     * 批量删除通道账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChannelAccountByIds(String ids);

    /**
     * 删除通道账户信息
     * 
     * @param id 通道账户ID
     * @return 结果
     */
    public int deleteChannelAccountById(Long id);
}
