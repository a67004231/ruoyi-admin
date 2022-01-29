package com.ruoyi.channel.mapper;

import java.util.List;
import com.ruoyi.channel.domain.ChannelInfo;

/**
 * 通道信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
public interface ChannelInfoMapper 
{
    /**
     * 查询通道信息
     * 
     * @param id 通道信息ID
     * @return 通道信息
     */
    public ChannelInfo selectChannelInfoById(Long id);
    /**
     * 查询通道信息
     * 
     * @param id 通道信息ID
     * @return 通道信息
     */
    public ChannelInfo selectChannelInfoByChannelCode(String channelCode);

    /**
     * 查询通道信息列表
     * 
     * @param channelInfo 通道信息
     * @return 通道信息集合
     */
    public List<ChannelInfo> selectChannelInfoList(ChannelInfo channelInfo);

    /**
     * 新增通道信息
     * 
     * @param channelInfo 通道信息
     * @return 结果
     */
    public int insertChannelInfo(ChannelInfo channelInfo);

    /**
     * 修改通道信息
     * 
     * @param channelInfo 通道信息
     * @return 结果
     */
    public int updateChannelInfo(ChannelInfo channelInfo);

    /**
     * 删除通道信息
     * 
     * @param id 通道信息ID
     * @return 结果
     */
    public int deleteChannelInfoById(Long id);

    /**
     * 批量删除通道信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChannelInfoByIds(String[] ids);
}
