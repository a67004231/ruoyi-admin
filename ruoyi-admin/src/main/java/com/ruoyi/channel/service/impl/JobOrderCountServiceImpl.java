package com.ruoyi.channel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.channel.mapper.JobOrderCountMapper;
import com.ruoyi.channel.domain.JobOrderCount;
import com.ruoyi.channel.service.IJobOrderCountService;
import com.ruoyi.common.core.text.Convert;

/**
 * 抢单并发量Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-07-14
 */
@Service
public class JobOrderCountServiceImpl implements IJobOrderCountService 
{
    @Autowired
    private JobOrderCountMapper jobOrderCountMapper;

    /**
     * 查询抢单并发量
     * 
     * @param id 抢单并发量ID
     * @return 抢单并发量
     */
    @Override
    public JobOrderCount selectJobOrderCountById(Long id)
    {
        return jobOrderCountMapper.selectJobOrderCountById(id);
    }

    /**
     * 查询抢单并发量列表
     * 
     * @param jobOrderCount 抢单并发量
     * @return 抢单并发量
     */
    @Override
    public List<JobOrderCount> selectJobOrderCountList(JobOrderCount jobOrderCount)
    {
        return jobOrderCountMapper.selectJobOrderCountList(jobOrderCount);
    }

    /**
     * 新增抢单并发量
     * 
     * @param jobOrderCount 抢单并发量
     * @return 结果
     */
    @Override
    public int insertJobOrderCount(JobOrderCount jobOrderCount)
    {
        return jobOrderCountMapper.insertJobOrderCount(jobOrderCount);
    }

    /**
     * 修改抢单并发量
     * 
     * @param jobOrderCount 抢单并发量
     * @return 结果
     */
    @Override
    public int updateJobOrderCount(JobOrderCount jobOrderCount)
    {
        return jobOrderCountMapper.updateJobOrderCount(jobOrderCount);
    }

    /**
     * 删除抢单并发量对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJobOrderCountByIds(String ids)
    {
        return jobOrderCountMapper.deleteJobOrderCountByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除抢单并发量信息
     * 
     * @param id 抢单并发量ID
     * @return 结果
     */
    @Override
    public int deleteJobOrderCountById(Long id)
    {
        return jobOrderCountMapper.deleteJobOrderCountById(id);
    }
}
