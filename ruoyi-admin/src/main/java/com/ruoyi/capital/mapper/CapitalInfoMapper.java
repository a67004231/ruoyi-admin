package com.ruoyi.capital.mapper;

import java.util.List;
import com.ruoyi.capital.domain.CapitalInfo;

/**
 * 资金信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
public interface CapitalInfoMapper 
{
    /**
     * 查询资金信息
     * 
     * @param id 资金信息ID
     * @return 资金信息
     */
    public CapitalInfo selectCapitalInfoById(Long id);
    public List<CapitalInfo> selectByMerIdAndChannelId(CapitalInfo capitalInf);
    /**
     * 查询资金信息列表
     * 
     * @param capitalInfo 资金信息
     * @return 资金信息集合
     */
    public List<CapitalInfo> selectCapitalInfoList(CapitalInfo capitalInfo);
    public List<CapitalInfo> selectCapitalList(CapitalInfo capitalInfo);
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
     * 删除资金信息
     * 
     * @param id 资金信息ID
     * @return 结果
     */
    public int deleteCapitalInfoById(Long id);

    /**
     * 批量删除资金信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCapitalInfoByIds(String[] ids);

    List<CapitalInfo> selectCapitalInfoListLog(CapitalInfo capitalInfo);

    CapitalInfo selectCapitalByChannel(CapitalInfo capitalInfo);

    List<CapitalInfo> selectCapitalByYes(CapitalInfo capitalInfo);

    List<CapitalInfo> selectCapitalInfoToday(CapitalInfo capitalInfo);
}
