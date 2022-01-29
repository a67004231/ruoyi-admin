package com.ruoyi.channel.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.channel.mapper.ChannelSettingInfoMapper;
import com.ruoyi.channel.domain.ChannelSettingInfo;
import com.ruoyi.channel.service.IChannelSettingInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 通道账户Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-11
 */
@Service
public class ChannelSettingInfoServiceImpl implements IChannelSettingInfoService 
{
    @Autowired
    private ChannelSettingInfoMapper channelSettingInfoMapper;

    /**
     * 查询通道账户
     * 
     * @param id 通道账户ID
     * @return 通道账户
     */
    @Override
    public ChannelSettingInfo selectChannelSettingInfoById(Long id)
    {
        return channelSettingInfoMapper.selectChannelSettingInfoById(id);
    }

    /**
     * 查询通道账户列表
     * 
     * @param channelSettingInfo 通道账户
     * @return 通道账户
     */
    @Override
    public List<ChannelSettingInfo> selectChannelSettingInfoList(ChannelSettingInfo channelSettingInfo)
    {
        return channelSettingInfoMapper.selectChannelSettingInfoList(channelSettingInfo);
    }

    /**
     * 新增通道账户
     * 
     * @param channelSettingInfo 通道账户
     * @return 结果
     */
    @Override
    public int insertChannelSettingInfo(ChannelSettingInfo channelSettingInfo)
    {
        channelSettingInfo.setCreateTime(DateUtils.getNowDate());
        return channelSettingInfoMapper.insertChannelSettingInfo(channelSettingInfo);
    }

    /**
     * 修改通道账户
     * 
     * @param channelSettingInfo 通道账户
     * @return 结果
     */
    @Override
    public int updateChannelSettingInfo(ChannelSettingInfo channelSettingInfo)
    {
        channelSettingInfo.setUpdateTime(DateUtils.getNowDate());
        return channelSettingInfoMapper.updateChannelSettingInfo(channelSettingInfo);
    }

    /**
     * 删除通道账户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChannelSettingInfoByIds(String ids)
    {
        return channelSettingInfoMapper.deleteChannelSettingInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除通道账户信息
     * 
     * @param id 通道账户ID
     * @return 结果
     */
    @Override
    public int deleteChannelSettingInfoById(Long id)
    {
        return channelSettingInfoMapper.deleteChannelSettingInfoById(id);
    }

    @Override
    public int changeStatus(ChannelSettingInfo channelSettingInfo) {
        return channelSettingInfoMapper.updateChannelSettingInfo(channelSettingInfo);
    }
}
