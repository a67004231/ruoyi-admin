package com.ruoyi.capital.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.capital.mapper.CapitalAccInfoMapper;
import com.ruoyi.capital.domain.CapitalAccInfo;
import com.ruoyi.capital.service.ICapitalAccInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * capitalAccInfoService业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-20
 */
@Service
public class CapitalAccInfoServiceImpl implements ICapitalAccInfoService 
{
    @Autowired
    private CapitalAccInfoMapper capitalAccInfoMapper;

    @Override
    public List<CapitalAccInfo> selectComListByMerId(CapitalAccInfo capitalAccInfo) {
        return capitalAccInfoMapper.selectComListByMerId(capitalAccInfo);
    }

    /**
     * 查询capitalAccInfo
     * 
     * @param id capitalAccInfoID
     * @return capitalAccInfo
     */
    @Override
    public CapitalAccInfo selectCapitalAccInfoById(Long id)
    {
        return capitalAccInfoMapper.selectCapitalAccInfoById(id);
    }

    /**
     * 查询capitalAccInfo列表
     * 
     * @param capitalAccInfo capitalAccInfo
     * @return capitalAccInfo
     */
    @Override
    public List<CapitalAccInfo> selectCapitalAccInfoList(CapitalAccInfo capitalAccInfo)
    {
        return capitalAccInfoMapper.selectCapitalAccInfoList(capitalAccInfo);
    }

    /**
     * 新增capitalAccInfo
     * 
     * @param capitalAccInfo capitalAccInfo
     * @return 结果
     */
    @Override
    public int insertCapitalAccInfo(CapitalAccInfo capitalAccInfo)
    {
        capitalAccInfo.setCreateTime(DateUtils.getNowDate());
        return capitalAccInfoMapper.insertCapitalAccInfo(capitalAccInfo);
    }

    /**
     * 修改capitalAccInfo
     * 
     * @param capitalAccInfo capitalAccInfo
     * @return 结果
     */
    @Override
    public int updateCapitalAccInfo(CapitalAccInfo capitalAccInfo)
    {
        capitalAccInfo.setUpdateTime(DateUtils.getNowDate());
        return capitalAccInfoMapper.updateCapitalAccInfo(capitalAccInfo);
    }

    /**
     * 删除capitalAccInfo对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCapitalAccInfoByIds(String ids)
    {
        return capitalAccInfoMapper.deleteCapitalAccInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除capitalAccInfo信息
     * 
     * @param id capitalAccInfoID
     * @return 结果
     */
    @Override
    public int deleteCapitalAccInfoById(Long id)
    {
        return capitalAccInfoMapper.deleteCapitalAccInfoById(id);
    }
}
