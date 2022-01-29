package com.ruoyi.order.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ruoyi.company.domain.CompanyInfo;
import com.ruoyi.company.service.ICompanyInfoService;
import com.ruoyi.merchant.domain.ChannelMerAuth;
import com.ruoyi.merchant.service.IChannelMerAuthService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.order.domain.ChannelOrderInfo;
import com.ruoyi.order.domain.OrderInfo;
import com.ruoyi.order.domain.OrderInfoReport;
import com.ruoyi.order.domain.SysAreaDto;
import com.ruoyi.order.service.IChannelOrderInfoService;
import com.ruoyi.order.service.IOrderInfoService;
import com.ruoyi.system.domain.SysArea;
import com.ruoyi.system.service.ISysAreaService;
import com.ruoyi.system.service.impl.SysAreaServiceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.merchant.service.IMerProductAuthService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充值通道订单Controller
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
@Controller
@RequestMapping("/order/info")
public class OrderInfoController extends BaseController
{
    private String prefix = "order/info";
    @Autowired
    private IChannelInfoService channelInfoService;
    @Autowired
    private IMerInfoService merInfoService;
    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    private ISysAreaService sysAreaService;
    @Autowired
    private IChannelOrderInfoService channelOrderInfoService;
    @Autowired
    private ICompanyInfoService companyInfoService;
    @Resource
    RabbitTemplate rabbitTemplate;
    @Value("${order.search.queue.post}")
	private  String searchOrderQueue;
    @Value("${order.notify.queue}")
    private  String notifyOrderQueue;
    @Autowired
    private IChannelMerAuthService channelMerAuthService;
    @RequiresPermissions("order:info:view")
    @GetMapping()
    public String info(ModelMap mmap)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        ChannelMerAuth channelMerAuth = new ChannelMerAuth();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==1) {
                channelMerAuth.setMerId(sysUser.getUserTypeId());
                List<ChannelMerAuth>  channelList =channelMerAuthService.selectChannelMerAuthList(channelMerAuth);
                mmap.put("channelList",channelList);
            }else if(sysUser.getType().intValue()==2) {
                channelMerAuth.setChannelId(sysUser.getUserTypeId());
                List<ChannelMerAuth>  merList =channelMerAuthService.selectChannelMerAuthList(channelMerAuth);
                mmap.put("merList",merList);
            }else if(sysUser.getType().intValue()==3){
                ChannelInfo channelInfo = new ChannelInfo();
                List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
                MerInfo merInfo = new MerInfo();
                List<MerInfo> merList = merInfoService.selectMerInfoList(merInfo);
                mmap.put("channelList",channelList);
                mmap.put("merList",merList);
                CompanyInfo companyInfo =  new CompanyInfo();
                List<CompanyInfo> companyList = companyInfoService.selectCompanyInfoList(companyInfo);
                mmap.put("companyList",companyList);
            }else if(sysUser.getType().intValue()==4){
                ChannelInfo channelInfo = new ChannelInfo();
                channelInfo.setCompanyId(sysUser.getUserTypeId());
                List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
                MerInfo merInfo = new MerInfo();
                merInfo.setCompanyId(sysUser.getUserTypeId());
                List<MerInfo> merList = merInfoService.selectMerInfoList(merInfo);
                mmap.put("channelList",channelList);
                mmap.put("merList",merList);
                CompanyInfo companyInfo =  new CompanyInfo();
                companyInfo.setId(sysUser.getUserTypeId());
                List<CompanyInfo> companyList = companyInfoService.selectCompanyInfoList(companyInfo);
                mmap.put("companyList",companyList);
            }
        }
        SysArea sysArea=new SysArea();
        sysArea.setLevel(1l);
        List<SysArea> provinceList = sysAreaService.selectSysAreaList(sysArea);
        List<SysAreaDto> areaDtoList=new ArrayList<SysAreaDto>(); 
        for (SysArea province : provinceList) {
			SysAreaDto proDto=new SysAreaDto();
			proDto.setV(province.getId().toString());
			proDto.setN(province.getName());
			sysArea.setLevel(2l);
			sysArea.setParentId(province.getId());
			sysArea.setId(null);
	        List<SysArea> cityList = sysAreaService.selectSysAreaList(sysArea);
	        List<SysAreaDto> cityDtoList=new ArrayList<SysAreaDto>();
	        for (SysArea city : cityList) {
	        	SysAreaDto cityDto=new SysAreaDto();
	        	cityDto.setV(city.getId().toString());
	        	cityDto.setN(city.getName());
	        	cityDtoList.add(cityDto);
			}
	        proDto.setS(cityDtoList);
	        areaDtoList.add(proDto);
		}
        String areaDtoStr = JSONUtil.toJsonStr(areaDtoList);
        System.out.println(areaDtoStr);
        String format = DateUtil.format(new Date(), "yyyy-MM-dd");
        mmap.put("startTime", format+" 00:00:00");
        mmap.put("areaDtoStr", areaDtoStr);
        mmap.put("endTime", format+" 23:59:59");
        mmap.put("type",sysUser.getType());
        return prefix + "/info";
    }
    @RequiresPermissions("order:info:view")
    @GetMapping("/infoForSlow")
    public String infoForSlow(ModelMap mmap)
    {
    	SysUser sysUser = ShiroUtils.getSysUser();
    	ChannelMerAuth channelMerAuth = new ChannelMerAuth();
    	if(sysUser.getType()!=null) {
    		if(sysUser.getType().intValue()==1) {
    			channelMerAuth.setMerId(sysUser.getUserTypeId());
    			List<ChannelMerAuth>  channelList =channelMerAuthService.selectChannelMerAuthList(channelMerAuth);
    			mmap.put("channelList",channelList);
    		}else if(sysUser.getType().intValue()==2) {
    			channelMerAuth.setChannelId(sysUser.getUserTypeId());
    			List<ChannelMerAuth>  merList =channelMerAuthService.selectChannelMerAuthList(channelMerAuth);
    			mmap.put("merList",merList);
    		}else if(sysUser.getType().intValue()==3){
    			ChannelInfo channelInfo = new ChannelInfo();
    			List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
    			MerInfo merInfo = new MerInfo();
    			List<MerInfo> merList = merInfoService.selectMerInfoList(merInfo);
    			mmap.put("channelList",channelList);
    			mmap.put("merList",merList);
                CompanyInfo companyInfo =  new CompanyInfo();
                List<CompanyInfo> companyList = companyInfoService.selectCompanyInfoList(companyInfo);
                mmap.put("companyList",companyList);
    		}else if(sysUser.getType().intValue()==4){
                ChannelInfo channelInfo = new ChannelInfo();
                channelInfo.setCompanyId(sysUser.getUserTypeId());
                List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
                MerInfo merInfo = new MerInfo();
                merInfo.setCompanyId(sysUser.getUserTypeId());
                List<MerInfo> merList = merInfoService.selectMerInfoList(merInfo);
                mmap.put("channelList",channelList);
                mmap.put("merList",merList);
                CompanyInfo companyInfo =  new CompanyInfo();
                companyInfo.setId(sysUser.getUserTypeId());
                List<CompanyInfo> companyList = companyInfoService.selectCompanyInfoList(companyInfo);
                mmap.put("companyList",companyList);
            }
    	}
    	String format = DateUtil.format(new Date(), "yyyy-MM-dd");
    	mmap.put("startTime", format+" 00:00:00");
    	mmap.put("endTime", format+" 23:59:59");
    	mmap.put("type",sysUser.getType());
    	mmap.put("productType","5");
    	return prefix + "/infoForSlow";
    }

    /**
     * 查询充值通道订单列表
     */
    @RequiresPermissions("order:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OrderInfo orderInfo)
    {
    	SysUser sysUser = ShiroUtils.getSysUser();
        startPage();
        if(sysUser.getType()!=null) {
        	if(sysUser.getType().intValue()==1) {
        		orderInfo.setMerId(sysUser.getUserTypeId());
        	}else if(sysUser.getType().intValue()==2) {
        		orderInfo.setChannelId(sysUser.getUserTypeId());
        	}else if(sysUser.getType().intValue()==4) {
                orderInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
        return getDataTable(list);
    }
    @GetMapping("/reportPage")
    public String reportPage(ModelMap mmap)
    {
    	String format = DateUtil.format(new Date(), "yyyy-MM-dd");
    	MerInfo merInfo = new MerInfo();
    	merInfo.setStatus(1);
    	SysUser sysUser = ShiroUtils.getSysUser();
    	if(sysUser.getType()!=null) {
        	if(sysUser.getType().intValue()==1) {
        		merInfo.setId(sysUser.getUserTypeId());
        	}else if(sysUser.getType().intValue()==4) {
        		merInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
    	List<MerInfo> merList = merInfoService.selectMerInfoList(merInfo);
        mmap.put("merList",merList);
        mmap.put("startTime", format+" 00:00:00");
        mmap.put("endTime", format+" 23:59:59");
        return prefix + "/orderReport";
    }
    @GetMapping("/merReportPage")
    public String merReportPage(ModelMap mmap)
    {
    	String format = DateUtil.format(new Date(), "yyyy-MM-dd");
    	MerInfo merInfo = new MerInfo();
//    	merInfo.setStatus(1);
    	ChannelInfo channelInfo = new ChannelInfo();
//    	channelInfo.setStatus(1);
    	SysUser sysUser = ShiroUtils.getSysUser();
    	if(sysUser.getType()!=null) {
        	if(sysUser.getType().intValue()==1) {
        		merInfo.setId(sysUser.getUserTypeId());
        	}else if(sysUser.getType().intValue()==4) {
        		merInfo.setCompanyId(sysUser.getUserTypeId());
        		channelInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
    	List<MerInfo> merList = merInfoService.selectMerInfoList(merInfo);
    	List<ChannelInfo> channelInfoList = channelInfoService.selectChannelInfoList(channelInfo);
    	mmap.put("merList",merList);
    	mmap.put("channelInfoList",channelInfoList);
    	mmap.put("startTime", format+" 00:00:00");
    	mmap.put("endTime", format+" 23:59:59");
    	return prefix + "/merOrderReport";
    }
    @GetMapping("/channelReportPage")
    public String channelReportPage(ModelMap mmap)
    {
    	String format = DateUtil.format(new Date(), "yyyy-MM-dd");
    	MerInfo merInfo = new MerInfo();
//    	merInfo.setStatus(1);
    	ChannelInfo channelInfo = new ChannelInfo();
//    	channelInfo.setStatus(1);
    	SysUser sysUser = ShiroUtils.getSysUser();
    	if(sysUser.getType()!=null) {
    		if(sysUser.getType().intValue()==1) {
    			merInfo.setId(sysUser.getUserTypeId());
    		}else if(sysUser.getType().intValue()==4) {
    			merInfo.setCompanyId(sysUser.getUserTypeId());
    			channelInfo.setCompanyId(sysUser.getUserTypeId());
    		}
    	}
    	List<MerInfo> merList = merInfoService.selectMerInfoList(merInfo);
    	List<ChannelInfo> channelInfoList = channelInfoService.selectChannelInfoList(channelInfo);
    	mmap.put("merList",merList);
    	mmap.put("channelInfoList",channelInfoList);
    	mmap.put("startTime", format+" 00:00:00");
    	mmap.put("endTime", format+" 23:59:59");
    	return prefix + "/channelOrderReport";
    }
    /**
     * 查询充值通道订单列表
     */
    @RequiresPermissions("order:info:list")
    @PostMapping("/listReport")
    @ResponseBody
    public TableDataInfo listReport(OrderInfo orderInfo)
    {
    	SysUser sysUser = ShiroUtils.getSysUser();
    	startPage();
    	if(sysUser.getType()!=null) {
    		if(sysUser.getType().intValue()==1) {
        		orderInfo.setMerId(sysUser.getUserTypeId());
        	}else if(sysUser.getType().intValue()==4) {
        		orderInfo.setCompanyId(sysUser.getUserTypeId());
            }
    	}
    	
    	List<OrderInfoReport> list = orderInfoService.selectOrderInfoListReport(orderInfo);
    	return getDataTable(list);
    }
    @RequiresPermissions("order:info:list")
    @PostMapping("/listMerReport")
    @ResponseBody
    public TableDataInfo listMerReport(OrderInfo orderInfo)
    {
    	SysUser sysUser = ShiroUtils.getSysUser();
    	startPage();
    	if(sysUser.getType()!=null) {
    		if(sysUser.getType().intValue()==1) {
    			orderInfo.setMerId(sysUser.getUserTypeId());
    		}else if(sysUser.getType().intValue()==2) {
    			orderInfo.setChannelId(sysUser.getUserTypeId());
    		}
    	}
    	if(sysUser.getType()!=null) {
        	if(sysUser.getType().intValue()==1) {
        		orderInfo.setMerId(sysUser.getUserTypeId());
        	}else if(sysUser.getType().intValue()==4) {
        		orderInfo.setCompanyId(sysUser.getUserTypeId());
            }
        }
    	List<OrderInfoReport> list = orderInfoService.selectMerOrderInfoListReport(orderInfo);
    	return getDataTable(list);
    }
    @RequiresPermissions("order:info:list")
    @PostMapping("/listMerAmtReport")
    @ResponseBody
    public TableDataInfo listMerAmtReport(OrderInfo orderInfo)
    {
    	SysUser sysUser = ShiroUtils.getSysUser();
    	startPage();
    	if(sysUser.getType()!=null) {
    		if(sysUser.getType().intValue()==1) {
    			orderInfo.setMerId(sysUser.getUserTypeId());
    		}else if(sysUser.getType().intValue()==2) {
    			orderInfo.setChannelId(sysUser.getUserTypeId());
    		}
    	}
    	if(sysUser.getType()!=null) {
    		if(sysUser.getType().intValue()==1) {
    			orderInfo.setMerId(sysUser.getUserTypeId());
    		}else if(sysUser.getType().intValue()==4) {
    			orderInfo.setCompanyId(sysUser.getUserTypeId());
    		}
    	}
    	List<OrderInfoReport> list = orderInfoService.selectMerOrderInfoListAmtReport(orderInfo);
    	return getDataTable(list);
    }
    @RequiresPermissions("order:info:list")
    @PostMapping("/listChannelReport")
    @ResponseBody
    public TableDataInfo listChannelReport(OrderInfo orderInfo)
    {
    	SysUser sysUser = ShiroUtils.getSysUser();
    	startPage();
    	if(sysUser.getType()!=null) {
    		if(sysUser.getType().intValue()==1) {
    			orderInfo.setMerId(sysUser.getUserTypeId());
    		}else if(sysUser.getType().intValue()==2) {
    			orderInfo.setChannelId(sysUser.getUserTypeId());
    		}
    	}
    	if(sysUser.getType()!=null) {
    		if(sysUser.getType().intValue()==1) {
    			orderInfo.setMerId(sysUser.getUserTypeId());
    		}else if(sysUser.getType().intValue()==4) {
    			orderInfo.setCompanyId(sysUser.getUserTypeId());
    		}
    	}
    	List<OrderInfoReport> list = orderInfoService.selectChannelOrderInfoListReport(orderInfo);
    	return getDataTable(list);
    }
    @RequiresPermissions("order:info:list")
    @PostMapping("/listChannelAmtReport")
    @ResponseBody
    public TableDataInfo listChannelAmtReport(OrderInfo orderInfo)
    {
    	SysUser sysUser = ShiroUtils.getSysUser();
    	startPage();
    	if(sysUser.getType()!=null) {
    		if(sysUser.getType().intValue()==1) {
    			orderInfo.setMerId(sysUser.getUserTypeId());
    		}else if(sysUser.getType().intValue()==2) {
    			orderInfo.setChannelId(sysUser.getUserTypeId());
    		}
    	}
    	if(sysUser.getType()!=null) {
    		if(sysUser.getType().intValue()==1) {
    			orderInfo.setMerId(sysUser.getUserTypeId());
    		}else if(sysUser.getType().intValue()==4) {
    			orderInfo.setCompanyId(sysUser.getUserTypeId());
    		}
    	}
    	List<OrderInfoReport> list = orderInfoService.selectChannelOrderInfoListAmtReport(orderInfo);
    	return getDataTable(list);
    }

    /**
     * 导出充值通道订单列表
     */
    @RequiresPermissions("order:info:export")
    @Log(title = "充值通道订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrderInfo orderInfo)
    {
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
        ExcelUtil<OrderInfo> util = new ExcelUtil<OrderInfo>(OrderInfo.class);
        return util.exportExcel(list, "充值通道订单数据");
    }

    /**
     * 新增充值通道订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存充值通道订单
     */
    @RequiresPermissions("order:info:add")
    @Log(title = "充值通道订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OrderInfo orderInfo )
    {
        return toAjax(orderInfoService.insertOrderInfo(orderInfo));
    }

    /**
     * 修改充值通道订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OrderInfo orderInfo = orderInfoService.selectOrderInfoById(id);
        mmap.put("orderInfo", orderInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存充值通道订单
     */
    @RequiresPermissions("order:info:edit")
    @Log(title = "充值通道订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OrderInfo orderInfo)
    {
        return toAjax(orderInfoService.updateOrderInfo(orderInfo));
    }

    /**
     * 删除充值通道订单
     */
    @RequiresPermissions("order:info:remove")
    @Log(title = "充值通道订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(orderInfoService.deleteOrderInfoByIds(ids));
    }
    /**
     * 补发通知
     */
    @Log(title = "补发通知", businessType = BusinessType.DELETE)
    @PostMapping( "/noticeAgain")
    @ResponseBody
    public String noticeAgain(String ids)
    {
    	String rsp="success";
    	try {
    		String[] idArray = ids.split(",");
    		for (String id : idArray) {
    			OrderInfo orderInfo = orderInfoService.selectOrderInfoById(Long.valueOf(id));
    			orderInfo.setAmt(orderInfo.getAmt()*100);
            	rabbitTemplate.convertAndSend(notifyOrderQueue, JSONUtil.toJsonStr(orderInfo));
			}
		} catch (Exception e) {
			e.printStackTrace();
			rsp="false"; 
		}
        return rsp;
    }
    /**
     * 同步状态
     */
    @Log(title = "同步状态", businessType = BusinessType.DELETE)
    @PostMapping( "/statusAgain")
    @ResponseBody
    public String statusAgain(String ids)
    {
    	String rsp="success";
    	try {
    		String[] idArray = ids.split(",");
    		for (String id : idArray) {
    			OrderInfo orderInfo = orderInfoService.selectOrderInfoById(Long.valueOf(id));
    			ChannelOrderInfo channelOrder=new ChannelOrderInfo();
    			channelOrder.setSysOrderNo(orderInfo.getSysOrderNo());
    			channelOrder.setStatus(1);
    			List<ChannelOrderInfo> channelOrderInfoList = channelOrderInfoService.selectChannelOrderInfoList(channelOrder);
    			if(channelOrderInfoList!=null&&channelOrderInfoList.size()>0) {
    				rabbitTemplate.convertAndSend(searchOrderQueue, JSONUtil.toJsonStr(channelOrderInfoList.get(0)));
    			}
    			
			}
		} catch (Exception e) {
			e.printStackTrace();
			rsp="false"; 
		}
        return rsp;
    }
    /**
     * 查询通道路由日志
     */
    @GetMapping("/searchLog/{sysOrderNo}")
    public String capitalLog(@PathVariable("sysOrderNo") String sysOrderNo,ModelMap modelMap)
    {
        OrderInfo orderInfo = new OrderInfo();
        SysUser sysUser = ShiroUtils.getSysUser();
        orderInfo.setSysOrderNo(sysOrderNo);
        modelMap.put("orderInfo",orderInfo);
        return  prefix +"/factorLog";
    }
    /**
     * 查询通道路由日志
     */
    @GetMapping("/searchChannelOrder/{sysOrderNo}")
    public String searchChannelOrder(@PathVariable("sysOrderNo") String sysOrderNo,ModelMap modelMap)
    {
    	OrderInfo orderInfo = new OrderInfo();
    	SysUser sysUser = ShiroUtils.getSysUser();
    	orderInfo.setSysOrderNo(sysOrderNo);
    	modelMap.put("orderInfo",orderInfo);
    	return  prefix +"/channelOrder";
    }
}
