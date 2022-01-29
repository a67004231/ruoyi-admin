package com.ruoyi.channel.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.merchant.domain.MerAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.channel.mapper.ChannelAccountMapper;
import com.ruoyi.channel.mapper.ChannelInfoMapper;
import com.ruoyi.channel.domain.ChannelAccount;
import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 通道信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
@Service
public class ChannelInfoServiceImpl implements IChannelInfoService 
{
    @Autowired
    private ChannelInfoMapper channelInfoMapper;
    @Autowired
    private ChannelAccountMapper channelAccountMapper;

    /**
     * 查询通道信息
     * 
     * @param id 通道信息ID
     * @return 通道信息
     */
    @Override
    public ChannelInfo selectChannelInfoById(Long id)
    {
        return channelInfoMapper.selectChannelInfoById(id);
    }

    /**
     * 查询通道信息列表
     * 
     * @param channelInfo 通道信息
     * @return 通道信息
     */
    @Override
    public List<ChannelInfo> selectChannelInfoList(ChannelInfo channelInfo)
    {
        return channelInfoMapper.selectChannelInfoList(channelInfo);
    }

    /**
     * 新增通道信息
     * 
     * @param channelInfo 通道信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertChannelInfo(ChannelInfo channelInfo)
    {
        channelInfo.setCreateTime(DateUtils.getNowDate());
        channelInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
        int insertChannelInfo = channelInfoMapper.insertChannelInfo(channelInfo);
        channelInfo=channelInfoMapper.selectChannelInfoByChannelCode(channelInfo.getChannelCode());
        ChannelAccount channelAccount=new ChannelAccount();
        channelAccount.setChannelId(channelInfo.getId());
        channelAccount.setCreditAmt(0l);
        channelAccount.setCreditBalanceAmt(0l);
        channelAccount.setCreditFixAmt(0l);
        channelAccount.setCreditUseAmt(0l);
        channelAccount.setVersion(0l);
        channelAccountMapper.insertChannelAccount(channelAccount);
        return insertChannelInfo;
    }

    /**
     * 修改通道信息
     * 
     * @param channelInfo 通道信息
     * @return 结果
     */
    @Override
    public int updateChannelInfo(ChannelInfo channelInfo)
    {
        channelInfo.setUpdateTime(DateUtils.getNowDate());
        return channelInfoMapper.updateChannelInfo(channelInfo);
    }

    /**
     * 删除通道信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChannelInfoByIds(String ids)
    {
        return channelInfoMapper.deleteChannelInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除通道信息信息
     * 
     * @param id 通道信息ID
     * @return 结果
     */
    @Override
    public int deleteChannelInfoById(Long id)
    {
        return channelInfoMapper.deleteChannelInfoById(id);
    }

    @Override
    public int changeStatus(ChannelInfo channelInfo) {
        return channelInfoMapper.updateChannelInfo(channelInfo);
    }
}
