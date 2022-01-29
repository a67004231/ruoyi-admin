package com.ruoyi.capital.service;

import java.util.List;
import com.ruoyi.capital.domain.CapitalAccInfo;

/**
 * capitalAccInfoService接口
 * 
 * @author ruoyi
 * @date 2021-05-20
 */
public interface ICapitalAccInfoService 
{

    /**
     * 查询关联查询
     *
     * @param capitalAccInfo capitalAccInfo
     * @return capitalAccInfo集合
     */
    public List<CapitalAccInfo> selectComListByMerId(CapitalAccInfo capitalAccInfo);
    /**
     * 查询capitalAccInfo
     * 
     * @param id capitalAccInfoID
     * @return capitalAccInfo
     */
    public CapitalAccInfo selectCapitalAccInfoById(Long id);

    /**
     * 查询capitalAccInfo列表
     * 
     * @param capitalAccInfo capitalAccInfo
     * @return capitalAccInfo集合
     */
    public List<CapitalAccInfo> selectCapitalAccInfoList(CapitalAccInfo capitalAccInfo);

    /**
     * 新增capitalAccInfo
     * 
     * @param capitalAccInfo capitalAccInfo
     * @return 结果
     */
    public int insertCapitalAccInfo(CapitalAccInfo capitalAccInfo);

    /**
     * 修改capitalAccInfo
     * 
     * @param capitalAccInfo capitalAccInfo
     * @return 结果
     */
    public int updateCapitalAccInfo(CapitalAccInfo capitalAccInfo);

    /**
     * 批量删除capitalAccInfo
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCapitalAccInfoByIds(String ids);

    /**
     * 删除capitalAccInfo信息
     * 
     * @param id capitalAccInfoID
     * @return 结果
     */
    public int deleteCapitalAccInfoById(Long id);
}
