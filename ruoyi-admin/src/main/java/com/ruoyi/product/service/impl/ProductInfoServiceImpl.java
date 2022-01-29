package com.ruoyi.product.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.ProductInfoMapper;
import com.ruoyi.product.domain.ProductInfo;
import com.ruoyi.product.service.IProductInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 基础产品Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Service
public class ProductInfoServiceImpl implements IProductInfoService 
{
    @Autowired
    private ProductInfoMapper productInfoMapper;

    /**
     * 查询基础产品
     * 
     * @param id 基础产品ID
     * @return 基础产品
     */
    @Override
    public ProductInfo selectProductInfoById(Long id)
    {
        return productInfoMapper.selectProductInfoById(id);
    }

    /**
     * 查询基础产品列表
     * 
     * @param productInfo 基础产品
     * @return 基础产品
     */
    @Override
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo)
    {
        return productInfoMapper.selectProductInfoList(productInfo);
    }
    @Override
    public List<ProductInfo> selectProductInfoListForFun(ProductInfo productInfo)
    {
    	return productInfoMapper.selectProductInfoListForFun(productInfo);
    }

    /**
     * 新增基础产品
     * 
     * @param productInfo 基础产品
     * @return 结果
     */
    @Override
    public int insertProductInfo(ProductInfo productInfo)
    {
        productInfo.setCreateTime(DateUtils.getNowDate());
        return productInfoMapper.insertProductInfo(productInfo);
    }

    /**
     * 修改基础产品
     * 
     * @param productInfo 基础产品
     * @return 结果
     */
    @Override
    public int updateProductInfo(ProductInfo productInfo)
    {
        productInfo.setUpdateTime(DateUtils.getNowDate());
        return productInfoMapper.updateProductInfo(productInfo);
    }

    /**
     * 删除基础产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProductInfoByIds(String ids)
    {
        return productInfoMapper.deleteProductInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除基础产品信息
     * 
     * @param id 基础产品ID
     * @return 结果
     */
    @Override
    public int deleteProductInfoById(Long id)
    {
        return productInfoMapper.deleteProductInfoById(id);
    }
}
