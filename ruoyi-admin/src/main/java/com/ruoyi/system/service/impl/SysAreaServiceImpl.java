package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysAreaMapper;
import com.ruoyi.system.domain.SysArea;
import com.ruoyi.system.service.ISysAreaService;
import com.ruoyi.common.core.text.Convert;

/**
 * 地区Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-08-06
 */
@Service
public class SysAreaServiceImpl implements ISysAreaService 
{
    @Autowired
    private SysAreaMapper sysAreaMapper;

    /**
     * 查询地区
     * 
     * @param id 地区ID
     * @return 地区
     */
    @Override
    public SysArea selectSysAreaById(Long id)
    {
        return sysAreaMapper.selectSysAreaById(id);
    }

    /**
     * 查询地区列表
     * 
     * @param sysArea 地区
     * @return 地区
     */
    @Override
    public List<SysArea> selectSysAreaList(SysArea sysArea)
    {
        return sysAreaMapper.selectSysAreaList(sysArea);
    }

    /**
     * 新增地区
     * 
     * @param sysArea 地区
     * @return 结果
     */
    @Override
    public int insertSysArea(SysArea sysArea)
    {
        return sysAreaMapper.insertSysArea(sysArea);
    }

    /**
     * 修改地区
     * 
     * @param sysArea 地区
     * @return 结果
     */
    @Override
    public int updateSysArea(SysArea sysArea)
    {
        return sysAreaMapper.updateSysArea(sysArea);
    }

    /**
     * 删除地区对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysAreaByIds(String ids)
    {
        return sysAreaMapper.deleteSysAreaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除地区信息
     * 
     * @param id 地区ID
     * @return 结果
     */
    @Override
    public int deleteSysAreaById(Long id)
    {
        return sysAreaMapper.deleteSysAreaById(id);
    }
}
