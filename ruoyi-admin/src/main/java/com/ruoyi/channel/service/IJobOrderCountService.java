package com.ruoyi.channel.service;

import java.util.List;
import com.ruoyi.channel.domain.JobOrderCount;

/**
 * 抢单并发量Service接口
 * 
 * @author ruoyi
 * @date 2021-07-14
 */
public interface IJobOrderCountService 
{
    /**
     * 查询抢单并发量
     * 
     * @param id 抢单并发量ID
     * @return 抢单并发量
     */
    public JobOrderCount selectJobOrderCountById(Long id);

    /**
     * 查询抢单并发量列表
     * 
     * @param jobOrderCount 抢单并发量
     * @return 抢单并发量集合
     */
    public List<JobOrderCount> selectJobOrderCountList(JobOrderCount jobOrderCount);

    /**
     * 新增抢单并发量
     * 
     * @param jobOrderCount 抢单并发量
     * @return 结果
     */
    public int insertJobOrderCount(JobOrderCount jobOrderCount);

    /**
     * 修改抢单并发量
     * 
     * @param jobOrderCount 抢单并发量
     * @return 结果
     */
    public int updateJobOrderCount(JobOrderCount jobOrderCount);

    /**
     * 批量删除抢单并发量
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJobOrderCountByIds(String ids);

    /**
     * 删除抢单并发量信息
     * 
     * @param id 抢单并发量ID
     * @return 结果
     */
    public int deleteJobOrderCountById(Long id);
}
