package com.ruoyi.channel.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.channel.mapper.ChannelProductAuthMapper;
import com.ruoyi.channel.domain.ChannelProductAuth;
import com.ruoyi.channel.service.IChannelProductAuthService;
import com.ruoyi.common.core.text.Convert;

/**
 * 通道产品授权Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Service
public class ChannelProductAuthServiceImpl implements IChannelProductAuthService 
{
    @Autowired
    private ChannelProductAuthMapper channelProductAuthMapper;

    /**
     * 查询通道产品授权
     * 
     * @param id 通道产品授权ID
     * @return 通道产品授权
     */
    @Override
    public ChannelProductAuth selectChannelProductAuthById(Long id)
    {
        return channelProductAuthMapper.selectChannelProductAuthById(id);
    }

    /**
     * 查询通道产品授权列表
     * 
     * @param channelProductAuth 通道产品授权
     * @return 通道产品授权
     */
    @Override
    public List<ChannelProductAuth> selectChannelProductAuthList(ChannelProductAuth channelProductAuth)
    {
        return channelProductAuthMapper.selectChannelProductAuthList(channelProductAuth);
    }

    /**
     * 新增通道产品授权
     * 
     * @param channelProductAuth 通道产品授权
     * @return 结果
     */
    @Override
    public int insertChannelProductAuth(ChannelProductAuth channelProductAuth)
    {
        channelProductAuth.setCreateTime(DateUtils.getNowDate());
        channelProductAuth.setUpdateTime(DateUtils.getNowDate());
        return channelProductAuthMapper.insertChannelProductAuth(channelProductAuth);
    }

    /**
     * 修改通道产品授权
     * 
     * @param channelProductAuth 通道产品授权
     * @return 结果
     */
    @Override
    public int updateChannelProductAuth(ChannelProductAuth channelProductAuth)
    {
        channelProductAuth.setUpdateTime(DateUtils.getNowDate());
        return channelProductAuthMapper.updateChannelProductAuth(channelProductAuth);
    }

    /**
     * 删除通道产品授权对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChannelProductAuthByIds(String ids)
    {
        return channelProductAuthMapper.deleteChannelProductAuthByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除通道产品授权信息
     * 
     * @param id 通道产品授权ID
     * @return 结果
     */
    @Override
    public int deleteChannelProductAuthById(Long id)
    {
        return channelProductAuthMapper.deleteChannelProductAuthById(id);
    }
}
