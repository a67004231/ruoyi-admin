package com.ruoyi.channel.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.channel.domain.JobOrderCount;
import com.ruoyi.channel.service.IJobOrderCountService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 抢单并发量Controller
 * 
 * @author ruoyi
 * @date 2021-07-14
 */
@Controller
@RequestMapping("/job/count")
public class JobOrderCountController extends BaseController
{
    private String prefix = "job/count";

    @Autowired
    private IJobOrderCountService jobOrderCountService;

    @RequiresPermissions("job:count:view")
    @GetMapping()
    public String count()
    {
        return prefix + "/count";
    }

    /**
     * 查询抢单并发量列表
     */
    @RequiresPermissions("job:count:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(JobOrderCount jobOrderCount)
    {
        startPage();
        List<JobOrderCount> list = jobOrderCountService.selectJobOrderCountList(jobOrderCount);
        return getDataTable(list);
    }

    /**
     * 导出抢单并发量列表
     */
    @RequiresPermissions("job:count:export")
    @Log(title = "抢单并发量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JobOrderCount jobOrderCount)
    {
        List<JobOrderCount> list = jobOrderCountService.selectJobOrderCountList(jobOrderCount);
        ExcelUtil<JobOrderCount> util = new ExcelUtil<JobOrderCount>(JobOrderCount.class);
        return util.exportExcel(list, "抢单并发量数据");
    }

    /**
     * 新增抢单并发量
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存抢单并发量
     */
    @RequiresPermissions("job:count:add")
    @Log(title = "抢单并发量", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(JobOrderCount jobOrderCount)
    {
        return toAjax(jobOrderCountService.insertJobOrderCount(jobOrderCount));
    }

    /**
     * 修改抢单并发量
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        JobOrderCount jobOrderCount = jobOrderCountService.selectJobOrderCountById(id);
        mmap.put("jobOrderCount", jobOrderCount);
        return prefix + "/edit";
    }

    /**
     * 修改保存抢单并发量
     */
    @RequiresPermissions("job:count:edit")
    @Log(title = "抢单并发量", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(JobOrderCount jobOrderCount)
    {
        return toAjax(jobOrderCountService.updateJobOrderCount(jobOrderCount));
    }

    /**
     * 删除抢单并发量
     */
    @RequiresPermissions("job:count:remove")
    @Log(title = "抢单并发量", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(jobOrderCountService.deleteJobOrderCountByIds(ids));
    }
}
