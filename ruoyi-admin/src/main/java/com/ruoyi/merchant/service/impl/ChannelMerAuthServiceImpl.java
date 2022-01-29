package com.ruoyi.merchant.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.merchant.mapper.ChannelMerAuthMapper;
import com.ruoyi.merchant.domain.ChannelMerAuth;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.service.IChannelMerAuthService;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 上下游关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-21
 */
@Service
public class ChannelMerAuthServiceImpl implements IChannelMerAuthService 
{
	@Autowired
    private IChannelInfoService channelInfoService;
    @Autowired
    private IMerInfoService merInfoService;
    @Autowired
    private ChannelMerAuthMapper channelMerAuthMapper;

    /**
     * 查询上下游关联
     * 
     * @param id 上下游关联ID
     * @return 上下游关联
     */
    @Override
    public ChannelMerAuth selectChannelMerAuthById(Long id)
    {
        return channelMerAuthMapper.selectChannelMerAuthById(id);
    }

    /**
     * 查询上下游关联列表
     * 
     * @param channelMerAuth 上下游关联
     * @return 上下游关联
     */
    @Override
    public List<ChannelMerAuth> selectChannelMerAuthList(ChannelMerAuth channelMerAuth)
    {
        return channelMerAuthMapper.selectChannelMerAuthList(channelMerAuth);
    }

    /**
     * 新增上下游关联
     * 
     * @param channelMerAuth 上下游关联
     * @return 结果
     */
    @Override
    public int insertChannelMerAuth(ChannelMerAuth channelMerAuth)
    {
    	Long channelId = channelMerAuth.getChannelId();
    	Long merId = channelMerAuth.getMerId();
    	ChannelInfo channelInfo = channelInfoService.selectChannelInfoById(channelId);
    	MerInfo merInfo = merInfoService.selectMerInfoById(merId);
    	channelMerAuth.setChannelCode(channelInfo.getChannelCode());
    	channelMerAuth.setChannelName(channelInfo.getChannelName());
    	channelMerAuth.setMerName(merInfo.getMerName());
    	
        return channelMerAuthMapper.insertChannelMerAuth(channelMerAuth);
    }

    /**
     * 修改上下游关联
     * 
     * @param channelMerAuth 上下游关联
     * @return 结果
     */
    @Override
    public int updateChannelMerAuth(ChannelMerAuth channelMerAuth)
    {
        return channelMerAuthMapper.updateChannelMerAuth(channelMerAuth);
    }

    /**
     * 删除上下游关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChannelMerAuthByIds(String ids)
    {
        return channelMerAuthMapper.deleteChannelMerAuthByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除上下游关联信息
     * 
     * @param id 上下游关联ID
     * @return 结果
     */
    @Override
    public int deleteChannelMerAuthById(Long id)
    {
        return channelMerAuthMapper.deleteChannelMerAuthById(id);
    }

    @Override
    public int changeStatus(ChannelMerAuth channelMerAuth) {
        return channelMerAuthMapper.updateChannelMerAuth(channelMerAuth);
    }
}
