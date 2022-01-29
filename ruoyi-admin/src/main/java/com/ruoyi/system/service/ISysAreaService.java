package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysArea;

/**
 * 地区Service接口
 * 
 * @author ruoyi
 * @date 2021-08-06
 */
public interface ISysAreaService 
{
    /**
     * 查询地区
     * 
     * @param id 地区ID
     * @return 地区
     */
    public SysArea selectSysAreaById(Long id);

    /**
     * 查询地区列表
     * 
     * @param sysArea 地区
     * @return 地区集合
     */
    public List<SysArea> selectSysAreaList(SysArea sysArea);

    /**
     * 新增地区
     * 
     * @param sysArea 地区
     * @return 结果
     */
    public int insertSysArea(SysArea sysArea);

    /**
     * 修改地区
     * 
     * @param sysArea 地区
     * @return 结果
     */
    public int updateSysArea(SysArea sysArea);

    /**
     * 批量删除地区
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysAreaByIds(String ids);

    /**
     * 删除地区信息
     * 
     * @param id 地区ID
     * @return 结果
     */
    public int deleteSysAreaById(Long id);
}
