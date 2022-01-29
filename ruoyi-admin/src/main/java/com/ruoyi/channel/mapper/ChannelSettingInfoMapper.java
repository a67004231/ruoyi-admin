package com.ruoyi.channel.mapper;

import java.util.List;
import com.ruoyi.channel.domain.ChannelSettingInfo;

/**
 * 通道账户Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-11
 */
public interface ChannelSettingInfoMapper 
{
    /**
     * 查询通道账户
     * 
     * @param id 通道账户ID
     * @return 通道账户
     */
    public ChannelSettingInfo selectChannelSettingInfoById(Long id);

    /**
     * 查询通道账户列表
     * 
     * @param channelSettingInfo 通道账户
     * @return 通道账户集合
     */
    public List<ChannelSettingInfo> selectChannelSettingInfoList(ChannelSettingInfo channelSettingInfo);

    /**
     * 新增通道账户
     * 
     * @param channelSettingInfo 通道账户
     * @return 结果
     */
    public int insertChannelSettingInfo(ChannelSettingInfo channelSettingInfo);

    /**
     * 修改通道账户
     * 
     * @param channelSettingInfo 通道账户
     * @return 结果
     */
    public int updateChannelSettingInfo(ChannelSettingInfo channelSettingInfo);

    /**
     * 删除通道账户
     * 
     * @param id 通道账户ID
     * @return 结果
     */
    public int deleteChannelSettingInfoById(Long id);

    /**
     * 批量删除通道账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChannelSettingInfoByIds(String[] ids);

    int changeStatus(ChannelSettingInfo channelSettingInfo);
}
