package com.ruoyi.capital.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.capital.mapper.CapitalInfoMapper;
import com.ruoyi.capital.domain.CapitalInfo;
import com.ruoyi.capital.service.ICapitalInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资金信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
@Service
public class CapitalInfoServiceImpl implements ICapitalInfoService 
{
    @Autowired
    private CapitalInfoMapper capitalInfoMapper;

    /**
     * 查询资金信息
     * 
     * @param id 资金信息ID
     * @return 资金信息
     */
    @Override
    public CapitalInfo selectCapitalInfoById(Long id)
    {
        return capitalInfoMapper.selectCapitalInfoById(id);
    }

    /**
     * 查询资金信息列表
     * 
     * @param capitalInfo 资金信息
     * @return 资金信息
     */
    @Override
    public List<CapitalInfo> selectCapitalInfoList(CapitalInfo capitalInfo)
    {
        return capitalInfoMapper.selectCapitalInfoList(capitalInfo);
    }

    @Override
    public CapitalInfo selectCapitalByChannel(CapitalInfo capitalInfo) {
        return capitalInfoMapper.selectCapitalByChannel(capitalInfo);
    }

    @Override
    public List<CapitalInfo> selectCapitalInfoListLog(CapitalInfo capitalInfo) {
        return capitalInfoMapper.selectCapitalInfoListLog(capitalInfo);
    }
    @Override
    public List<CapitalInfo> selectCapitalList(CapitalInfo capitalInfo)
    {
        return capitalInfoMapper.selectCapitalList(capitalInfo);
    }
    /**
     * 新增资金信息
     * 
     * @param capitalInfo 资金信息
     * @return 结果
     */
    @Override
    public int insertCapitalInfo(CapitalInfo capitalInfo)
    {
        return capitalInfoMapper.insertCapitalInfo(capitalInfo);
    }

    /**
     * 修改资金信息
     * 
     * @param capitalInfo 资金信息
     * @return 结果
     */
    @Override
    public int updateCapitalInfo(CapitalInfo capitalInfo)
    {
        capitalInfo.setUpdateTime(DateUtils.getNowDate());
        return capitalInfoMapper.updateCapitalInfo(capitalInfo);
    }

    /**
     * 删除资金信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCapitalInfoByIds(String ids)
    {
        return capitalInfoMapper.deleteCapitalInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除资金信息信息
     * 
     * @param id 资金信息ID
     * @return 结果
     */
    @Override
    public int deleteCapitalInfoById(Long id)
    {
        return capitalInfoMapper.deleteCapitalInfoById(id);
    }

    @Override
    public List<CapitalInfo> selectCapitalByYes(CapitalInfo capitalInfo) {
        return capitalInfoMapper.selectCapitalByYes(capitalInfo);
    }


    @Override
    public List<CapitalInfo> selectByMerIdAndChannelId(CapitalInfo capitalInfo) {
        return capitalInfoMapper.selectByMerIdAndChannelId(capitalInfo);
    }

    @Override
    public List<CapitalInfo> selectCapitalInfoToday(CapitalInfo capitalInfo) {
        return capitalInfoMapper.selectCapitalInfoToday(capitalInfo);
    }
}
