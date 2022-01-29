package com.ruoyi.channel.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.factor.domain.FactorInfo;
import com.ruoyi.factor.mapper.FactorInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.channel.mapper.ChannelInfoMapper;
import com.ruoyi.channel.mapper.ChannelStartMapper;
import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.domain.ChannelStart;
import com.ruoyi.channel.service.IChannelStartService;
import com.ruoyi.common.core.text.Convert;

/**
 * 通道初始化Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-10
 */
@Service
public class ChannelStartServiceImpl implements IChannelStartService 
{
    @Autowired
    private ChannelStartMapper channelStartMapper;
    @Autowired
    private ChannelInfoMapper channelInfoMapper;
    @Autowired
    private FactorInfoMapper factorInfoMapper;
    /**
     * 查询通道初始化
     * 
     * @param id 通道初始化ID
     * @return 通道初始化
     */
    @Override
    public ChannelStart selectChannelStartById(Long id)
    {
        return channelStartMapper.selectChannelStartById(id);
    }

    /**
     * 查询通道初始化列表
     * 
     * @param channelStart 通道初始化
     * @return 通道初始化
     */
    @Override
    public List<ChannelStart> selectChannelStartList(ChannelStart channelStart)
    {
        return channelStartMapper.selectChannelStartList(channelStart);
    }

    /**
     * 新增通道初始化
     * 
     * @param channelStart 通道初始化
     * @return 结果
     */
    @Override
    public int insertChannelStart(ChannelStart channelStart)
    {
    	ChannelInfo channelInfo = channelInfoMapper.selectChannelInfoById(channelStart.getChannelId());
    	channelStart.setChannelCode(channelInfo.getChannelCode());
        channelStart.setCreateTime(DateUtils.getNowDate());
        channelStart.setAmt(channelStart.getAmt()*100);
        if(channelStart.getMerId()==0) {
        	channelStart.setMerId(null);
        }
        return channelStartMapper.insertChannelStart(channelStart);
    }

    /**
     * 修改通道初始化
     * 
     * @param channelStart 通道初始化
     * @return 结果
     */
    @Override
    public int updateChannelStart(ChannelStart channelStart)
    {
        channelStart.setUpdateTime(DateUtils.getNowDate());
        channelStart.setAmt(channelStart.getAmt()*100);
        return channelStartMapper.updateChannelStart(channelStart);
    }

    /**
     * 删除通道初始化对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChannelStartByIds(String ids)
    {
        return channelStartMapper.deleteChannelStartByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除通道初始化信息
     * 
     * @param id 通道初始化ID
     * @return 结果
     */
    @Override
    public int deleteChannelStartById(Long id)
    {
        return channelStartMapper.deleteChannelStartById(id);
    }

    @Override
    public int restartAll() {
        FactorInfo factorInfo = new FactorInfo();
        factorInfo.setWeight(0);
        List<FactorInfo> list = factorInfoMapper.selectFactorInfoList(factorInfo);
        int num=0;
        for(FactorInfo info:list){
            info.setWeight(1);
            num+=factorInfoMapper.updateFactorInfo(info);
        }
        return num;
    }
    @Override
    public int stopAll() {
        FactorInfo factorInfo = new FactorInfo();
        factorInfo.setWeight(1);
        List<FactorInfo> list = factorInfoMapper.selectFactorInfoList(factorInfo);
        int num=0;
        for(FactorInfo info:list){
            info.setWeight(0);
            num+=factorInfoMapper.updateFactorInfo(info);
        }
        return num;
    }
}
