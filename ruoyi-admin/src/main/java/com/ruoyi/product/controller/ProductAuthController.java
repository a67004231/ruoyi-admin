package com.ruoyi.product.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.channel.domain.ChannelInfo;
import com.ruoyi.channel.domain.ChannelProductAuth;
import com.ruoyi.channel.service.IChannelInfoService;
import com.ruoyi.channel.service.IChannelProductAuthService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.company.domain.CompanyInfo;
import com.ruoyi.company.service.ICompanyInfoService;
import com.ruoyi.merchant.domain.MerInfo;
import com.ruoyi.merchant.domain.MerProductAuth;
import com.ruoyi.merchant.service.IMerInfoService;
import com.ruoyi.merchant.service.IMerProductAuthService;
import com.ruoyi.product.domain.ProductAuthDto;
import com.ruoyi.product.domain.ProductInfo;
import com.ruoyi.product.service.IProductInfoService;
import com.ruoyi.system.domain.SysArea;
import com.ruoyi.system.service.ISysAreaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.product.domain.ProductAuth;
import com.ruoyi.product.service.IProductAuthService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品授权Controller
 * 
 * @author haoyu
 * @date 2021-09-08
 */
@Controller
@RequestMapping("/product/auth")
public class ProductAuthController extends BaseController
{
    private String prefix = "product/auth";

    @Autowired
    private IProductAuthService productAuthService;
    @Autowired
    private IChannelInfoService channelInfoService;
    @Autowired
    private IMerInfoService merInfoService;
    @Autowired
    private ICompanyInfoService companyInfoService;
    @Autowired
    private ISysAreaService sysAreaService;
    @Autowired
    private IProductInfoService productInfoService;
    @Autowired
    private IMerProductAuthService merProductAuthService;
    @Autowired
    private IChannelProductAuthService channelProductAuthService;
    @RequiresPermissions("product:auth:view")
    @GetMapping()
    public String auth(ModelMap mmap)
    {

        SysUser sysUser = ShiroUtils.getSysUser();
        ChannelInfo channelInfo=new ChannelInfo();
        CompanyInfo companyInfo = new CompanyInfo();
        MerInfo merInfo = new MerInfo();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==4) {
                channelInfo.setCompanyId(sysUser.getUserTypeId());
                merInfo.setCompanyId(sysUser.getUserTypeId());
                companyInfo.setId(sysUser.getUserTypeId());
            }
        }
        List<MerInfo> merList = merInfoService.selectMerInfoList(merInfo);
        mmap.put("merList",merList);
        List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
        mmap.put("channelList",channelList);
        return prefix + "/auth";
    }

    /**
     * 查询产品授权列表
     */
    @RequiresPermissions("product:auth:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProductAuth productAuth)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==4) {
                productAuth.setCompanyId(sysUser.getUserTypeId());
            }
        }
        List<ProductAuth> list = productAuthService.selectProductAuthList(productAuth);
        return getDataTable(list);
    }

    /**
     * 导出产品授权列表
     */
    @RequiresPermissions("product:auth:export")
    @Log(title = "产品授权", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductAuth productAuth)
    {
        List<ProductAuth> list = productAuthService.selectProductAuthList(productAuth);
        ExcelUtil<ProductAuth> util = new ExcelUtil<ProductAuth>(ProductAuth.class);
        return util.exportExcel(list, "产品授权数据");
    }

    /**
     * 新增商户产品授权
     */
    @GetMapping("/addMer")
    public String addMer(ModelMap mmap)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        ChannelInfo channelInfo=new ChannelInfo();
        CompanyInfo companyInfo = new CompanyInfo();
        MerInfo merInfo = new MerInfo();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==4) {
                channelInfo.setCompanyId(sysUser.getUserTypeId());
                merInfo.setCompanyId(sysUser.getUserTypeId());
                companyInfo.setId(sysUser.getUserTypeId());
            }
        }
        List<MerInfo> merList = merInfoService.selectMerInfoList(merInfo);
        SysArea sysArea = new SysArea();
        sysArea.setLevel(1L);
        List<SysArea> areaList = sysAreaService.selectSysAreaList(sysArea);
        mmap.put("merList",merList);
        mmap.put("areaList",areaList);
        return prefix + "/addMer";
    }
    /**
     * 新增通道产品授权
     */
    @GetMapping("/addChannel")
    public String addChannel(ModelMap mmap)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        ChannelInfo channelInfo=new ChannelInfo();
        CompanyInfo companyInfo = new CompanyInfo();
        MerInfo merInfo = new MerInfo();
        if(sysUser.getType()!=null) {
            if(sysUser.getType().intValue()==4) {
                channelInfo.setCompanyId(sysUser.getUserTypeId());
                merInfo.setCompanyId(sysUser.getUserTypeId());
                companyInfo.setId(sysUser.getUserTypeId());
            }
        }
        List<ChannelInfo> channelList = channelInfoService.selectChannelInfoList(channelInfo);
        SysArea sysArea = new SysArea();
        sysArea.setLevel(1L);
        List<SysArea> areaList = sysAreaService.selectSysAreaList(sysArea);
        mmap.put("channelList",channelList);
        mmap.put("areaList",areaList);
        return prefix + "/addChannel";
    }
    /**
     * 新增保存产品授权
     */
    @RequiresPermissions("product:auth:add")
    @Log(title = "产品授权", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @Transactional
    public AjaxResult addSave(ProductAuth productAuth)
    {
        if(productAuth.getRate()>9999||productAuth.getRate()<1000){
            return AjaxResult.error("费率必须为4位数");
        }
        if(StringUtils.isEmpty(productAuth.getOperators())){
            return AjaxResult.error("请选择运营商");
        }
        if(StringUtils.isEmpty(productAuth.getHasAmout())){
            return AjaxResult.error("请选择面额");
        }
        if(StringUtils.isEmpty(productAuth.getAreaIds())){
            return AjaxResult.error("请选择区域");
        }
        //默认
        productAuth.setRateType(1);
        //根据参数进行商户授权表遍历数据添加
        String[] operators = productAuth.getOperators().split(",");
        String[] amounts = productAuth.getHasAmout().split(",");
        String[] areaIds = productAuth.getAreaIds().split(",");
        try{
            for(String operator :operators){
                for(String amount:amounts){
                    for(String areaId:areaIds){
                        //组装参数进行产品匹配
                        String key = getKey(productAuth.getType(),operator,amount,areaId);
                        System.out.println(key);
                        if(StringUtils.isEmpty(key)){
                            continue;
                        }
                        ProductInfo productInfo = new ProductInfo();
                        productInfo.setCode(key);
                        List<ProductInfo> list = productInfoService.selectProductInfoList(productInfo);
                        if(list.size()>0){
                            ProductInfo product = list.get(0);
                            //tableType 1为商户产品授权，2为通道产品授权
                            if(productAuth.getTableType().equals(1)){
                                MerInfo merInfo = merInfoService.selectMerInfoById(productAuth.getMerId());
                                productAuth.setCompanyId(merInfo.getCompanyId());
                                productAuth.setMerNo(merInfo.getMerNo());
                                productAuth.setMerName(merInfo.getMerName());
                                productAuth.setCompanyId(merInfo.getCompanyId());
                                //插入商户产品授权或者更新
                                MerProductAuth merProductAuth = new MerProductAuth();
                                merProductAuth.setProductId(product.getId());
                                merProductAuth.setProductCode(product.getCode());
                                merProductAuth.setMerId(productAuth.getMerId());
                                List<MerProductAuth> mlist =  merProductAuthService.selectMerProductAuthList(merProductAuth);
                                merProductAuth.setRateType(productAuth.getRateType());
                                merProductAuth.setRate(String.valueOf(productAuth.getRate()));
                                if(mlist.size()>0){
                                    merProductAuth.setId(mlist.get(0).getId());
                                    merProductAuth.setUpdateTime(new Date());
                                    merProductAuthService.updateMerProductAuth(merProductAuth);
                                }else {
                                    merProductAuthService.insertMerProductAuth(merProductAuth);
                                }

                            }
                            else if(productAuth.getTableType().equals(2)){
                                ChannelInfo channelInfo = channelInfoService.selectChannelInfoById(productAuth.getChannelId());
                                productAuth.setCompanyId(channelInfo.getCompanyId());
                                productAuth.setChannelName(channelInfo.getCompanyName());
                                productAuth.setChannelCode(channelInfo.getChannelCode());
                                productAuth.setChannelName(channelInfo.getChannelName());
                                //插入通道产品授权或者更新
                                ChannelProductAuth channelProductAuth = new ChannelProductAuth();
                                channelProductAuth.setProductId(product.getId());
                                channelProductAuth.setProductCode(product.getCode());
                                channelProductAuth.setChannelId(productAuth.getChannelId());
                                List<ChannelProductAuth> clist = channelProductAuthService.selectChannelProductAuthList(channelProductAuth);
                                channelProductAuth.setChannelCode(productAuth.getChannelCode());
                                channelProductAuth.setRateType(productAuth.getRateType());
                                channelProductAuth.setRate(String.valueOf(productAuth.getRate()));
                                if(clist.size()>0){
                                    channelProductAuth.setId(clist.get(0).getId());
                                    channelProductAuth.setUpdateTime(new Date());
                                    channelProductAuthService.updateChannelProductAuth(channelProductAuth);
                                }else {
                                    channelProductAuthService.insertChannelProductAuth(channelProductAuth);
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return toAjax(productAuthService.insertProductAuth(productAuth));
    }
    public String getKey(Integer type,String operator,String amount,String areaId){
        String key ="";
        switch (type){
            case 1: key ="RG_FAST_"+operator+"_"+amount+"_"+areaId.substring(0,2);break;
            case 2: key ="RG_FIXED_"+operator+"_"+amount+"_"+areaId.substring(0,2);break;
            case 3: key ="RG_QQ"+"_"+amount+"_"+areaId.substring(0,2);break;
            case 4: key ="RG_DY"+"_"+amount+"_"+areaId.substring(0,2);break;
            case 5: key ="RG_SLOW_"+operator+"_"+amount+"_"+areaId.substring(0,2);break;
            case 6: key ="RG_FAST_ELECT"+"_"+amount+"_"+areaId;break;
            default:key="";break;
        }
        return key;
    }
    /**
     * 查看产品授权
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProductAuth productAuth = productAuthService.selectProductAuthById(id);
        mmap.put("productAuth", productAuth);
        SysArea sysArea = new SysArea();
        sysArea.setLevel(1L);
        List<SysArea> areaList = sysAreaService.selectSysAreaList(sysArea);
        mmap.put("areaList",areaList);
        mmap.put("type",productAuth.getTableType());
        return prefix + "/edit";
    }
    /**
     * 修改产品授权
     */
    @GetMapping("/editAuth/{id}")
    public String editAuth(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProductAuth productAuth = productAuthService.selectProductAuthById(id);
        mmap.put("productAuth", productAuth);
        SysArea sysArea = new SysArea();
        sysArea.setLevel(1L);
        List<SysArea> areaList = sysAreaService.selectSysAreaList(sysArea);
        mmap.put("areaList",areaList);
        mmap.put("type",productAuth.getTableType());
        return prefix + "/editAuth";
    }
    /**
     * 查询产品授权信息
     */
    @RequiresPermissions("product:auth:list")
    @PostMapping("/productList")
    @ResponseBody
    public TableDataInfo productList(ProductAuth productAuth)
    {
        startPage();
        List<ProductAuthDto> list = new ArrayList<>();
        if(StringUtils.isEmpty(productAuth.getOperators())||StringUtils.isEmpty(productAuth.getHasAmout())||StringUtils.isEmpty(productAuth.getAreaIds())){
            return getDataTable(list);
        }
        //默认
        productAuth.setRateType(1);
        //根据参数进行商户授权表遍历数据添加
        String[] operators = productAuth.getOperators().split(",");
        String[] amounts = productAuth.getHasAmout().split(",");
        String[] areaIds = productAuth.getAreaIds().split(",");
        try{
            for(String operator :operators){
                for(String amount:amounts){
                    for(String areaId:areaIds){
                        //组装参数进行产品匹配
                        String key = getKey(productAuth.getType(),operator,amount,areaId);
                        if(StringUtils.isEmpty(key)){
                            continue;
                        }
                        if(productAuth.getTableType().equals(1)){

                            //查询数据
                            ProductAuthDto productAuthDto = new ProductAuthDto();
                            productAuthDto.setProductCode(key);
                            productAuthDto.setMerId(productAuth.getMerId());
                            List<ProductAuthDto> mlist =  productAuthService.selectMerProductAuthList(productAuthDto);
                            if(mlist.size()>0){
                                list.add(mlist.get(0));
                            }
                        }
                        else if(productAuth.getTableType().equals(2)){
                            //查询数据
                            ProductAuthDto productAuthDto = new ProductAuthDto();
                            productAuthDto.setProductCode(key);
                            productAuthDto.setChannelId(productAuth.getChannelId());
                            List<ProductAuthDto> clist = productAuthService.selectChannelProductAuthList(productAuthDto);
                            if(clist.size()>0){
                                list.add(clist.get(0));
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return getDataTable(list);
    }
    /**
     * 费率修改
     */
    @RequiresPermissions("product:auth:edit")
    @Log(title = "费率修改", businessType = BusinessType.UPDATE)
    @PostMapping("/changeRate")
    @ResponseBody
    public AjaxResult changeRate(ProductAuth productAuth)
    {
        if(productAuth.getRate()>9999||productAuth.getRate()<1000){
            return AjaxResult.error("费率必须为4位数");
        }
        if(productAuth.getTableType().equals(1)){
            MerProductAuth merProductAuth = new MerProductAuth();
            merProductAuth.setId(productAuth.getId());
            merProductAuth.setRate(String.valueOf(productAuth.getRate()));
            return toAjax(merProductAuthService.updateMerProductAuth(merProductAuth));
        }
        else if(productAuth.getTableType().equals(2)){
            ChannelProductAuth channelProductAuth = new ChannelProductAuth();
            channelProductAuth.setId(productAuth.getId());
            channelProductAuth.setRate(String.valueOf(productAuth.getRate()));
            return toAjax(channelProductAuthService.updateChannelProductAuth(channelProductAuth));
        }
        return toAjax(0);
    }
    /**
     * 修改保存产品授权
     */
    @RequiresPermissions("product:auth:edit")
    @Log(title = "产品授权", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProductAuth productAuth)
    {

        return toAjax(productAuthService.updateProductAuth(productAuth));
    }

    /**
     * 删除产品授权
     */
    @RequiresPermissions("product:auth:remove")
    @Log(title = "产品授权", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    @Transactional
    public AjaxResult remove(String ids)
    {
        ProductAuth productAuth = productAuthService.selectProductAuthById(Long.valueOf(ids));
        //根据参数进行商户授权表遍历数据添加
        String[] operators = productAuth.getOperators().split(",");
        String[] amounts = productAuth.getHasAmout().split(",");
        String[] areaIds = productAuth.getAreaIds().split(",");
        try{
            for(String operator :operators){
                for(String amount:amounts){
                    for(String areaId:areaIds){
                        //组装参数进行产品匹配
                        String key = getKey(productAuth.getType(),operator,amount,areaId);
                        if(StringUtils.isEmpty(key)){
                            continue;
                        }
                        if(productAuth.getTableType().equals(1)){
                            //查询并删除商户产品授权
                            MerProductAuth merProductAuth = new MerProductAuth();
                            merProductAuth.setProductCode(key);
                            merProductAuth.setRateType(productAuth.getRateType());
                            merProductAuth.setRate(String.valueOf(productAuth.getRate()));
                            merProductAuth.setMerId(productAuth.getMerId());
                            List<MerProductAuth> merList = merProductAuthService.selectMerProductAuthList(merProductAuth);
                            if(merList.size()>0){
                                merProductAuthService.deleteMerProductAuthById(merList.get(0).getId());
                            }
                        }
                        else if(productAuth.getTableType().equals(2)){
                            //查询并删除通道产品授权
                            ChannelProductAuth channelProductAuth = new ChannelProductAuth();
                            channelProductAuth.setProductCode(key);
                            channelProductAuth.setRateType(productAuth.getRateType());
                            channelProductAuth.setRate(String.valueOf(productAuth.getRate()));
                            channelProductAuth.setChannelId(productAuth.getChannelId());
                            channelProductAuth.setChannelCode(productAuth.getChannelCode());
                            List<ChannelProductAuth> channelList = channelProductAuthService.selectChannelProductAuthList(channelProductAuth);
                            if(channelList.size()>0){
                                channelProductAuthService.deleteChannelProductAuthById(channelList.get(0).getId());
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return toAjax(productAuthService.deleteProductAuthById(Long.valueOf(ids)));
    }
}
