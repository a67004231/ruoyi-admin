package com.ruoyi.capital.service;

import java.util.List;
import com.ruoyi.capital.domain.CapitalInfo;

/**
 * 资金信息Service接口
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
public interface ICapitalInfoService 
{
    /**
     * 查询资金信息
     * 
     * @param id 资金信息ID
     * @return 资金信息
     */
    public CapitalInfo selectCapitalInfoById(Long id);

    /**
     * 查询资金信息列表
     * 
     * @param capitalInfo 资金信息
     * @return 资金信息集合
     */
    public List<CapitalInfo> selectCapitalInfoList(CapitalInfo capitalInfo);

    /**
     * 查询通道资金
     *
     * @param capitalInfo 查询通道资金
     * @return 查询通道资金
     */
    public CapitalInfo selectCapitalByChannel(CapitalInfo capitalInfo);

    /**
     * 查询资金信息列表
     *
     * @param capitalInfo 资金信息
     * @return 资金信息集合
     */
    public List<CapitalInfo> selectCapitalInfoListLog(CapitalInfo capitalInfo);

    List<CapitalInfo> selectCapitalList(CapitalInfo capitalInfo);

    /**
     * 新增资金信息
     * 
     * @param capitalInfo 资金信息
     * @return 结果
     */
    public int insertCapitalInfo(CapitalInfo capitalInfo);

    /**
     * 修改资金信息
     * 
     * @param capitalInfo 资金信息
     * @return 结果
     */
    public int updateCapitalInfo(CapitalInfo capitalInfo);

    /**
     * 批量删除资金信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCapitalInfoByIds(String ids);

    /**
     * 删除资金信息信息
     * 
     * @param id 资金信息ID
     * @return 结果
     */
    public int deleteCapitalInfoById(Long id);

    List<CapitalInfo> selectCapitalByYes(CapitalInfo capitalInfo);

    List<CapitalInfo> selectByMerIdAndChannelId(CapitalInfo capitalInfo);

    List<CapitalInfo> selectCapitalInfoToday(CapitalInfo capitalInfo);
}
