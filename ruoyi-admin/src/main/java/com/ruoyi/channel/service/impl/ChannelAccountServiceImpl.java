package com.ruoyi.channel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.channel.mapper.ChannelAccountMapper;
import com.ruoyi.channel.domain.ChannelAccount;
import com.ruoyi.channel.service.IChannelAccountService;
import com.ruoyi.common.core.text.Convert;

/**
 * 通道账户Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
@Service
public class ChannelAccountServiceImpl implements IChannelAccountService 
{
    @Autowired
    private ChannelAccountMapper channelAccountMapper;

    /**
     * 查询通道账户
     * 
     * @param id 通道账户ID
     * @return 通道账户
     */
    @Override
    public ChannelAccount selectChannelAccountById(Long id)
    {
        return channelAccountMapper.selectChannelAccountById(id);
    }

    /**
     * 查询通道账户列表
     * 
     * @param channelAccount 通道账户
     * @return 通道账户
     */
    @Override
    public List<ChannelAccount> selectChannelAccountList(ChannelAccount channelAccount)
    {
        return channelAccountMapper.selectChannelAccountList(channelAccount);
    }

    /**
     * 新增通道账户
     * 
     * @param channelAccount 通道账户
     * @return 结果
     */
    @Override
    public int insertChannelAccount(ChannelAccount channelAccount)
    {
        return channelAccountMapper.insertChannelAccount(channelAccount);
    }

    /**
     * 修改通道账户
     * 
     * @param channelAccount 通道账户
     * @return 结果
     */
    @Override
    public int updateChannelAccount(ChannelAccount channelAccount)
    {
        return channelAccountMapper.updateChannelAccount(channelAccount);
    }

    /**
     * 删除通道账户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChannelAccountByIds(String ids)
    {
        return channelAccountMapper.deleteChannelAccountByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除通道账户信息
     * 
     * @param id 通道账户ID
     * @return 结果
     */
    @Override
    public int deleteChannelAccountById(Long id)
    {
        return channelAccountMapper.deleteChannelAccountById(id);
    }
}
