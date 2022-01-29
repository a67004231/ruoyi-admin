package com.ruoyi.product.mapper;

import java.util.List;
import com.ruoyi.product.domain.ProductInfo;

/**
 * 基础产品Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public interface ProductInfoMapper 
{
    /**
     * 查询基础产品
     * 
     * @param id 基础产品ID
     * @return 基础产品
     */
    public ProductInfo selectProductInfoById(Long id);

    /**
     * 查询基础产品列表
     * 
     * @param productInfo 基础产品
     * @return 基础产品集合
     */
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo);
    /**
     * 查询基础产品列表
     * 
     * @param productInfo 基础产品
     * @return 基础产品集合
     */
    public List<ProductInfo> selectProductInfoListForFun(ProductInfo productInfo);

    /**
     * 新增基础产品
     * 
     * @param productInfo 基础产品
     * @return 结果
     */
    public int insertProductInfo(ProductInfo productInfo);

    /**
     * 修改基础产品
     * 
     * @param productInfo 基础产品
     * @return 结果
     */
    public int updateProductInfo(ProductInfo productInfo);

    /**
     * 删除基础产品
     * 
     * @param id 基础产品ID
     * @return 结果
     */
    public int deleteProductInfoById(Long id);

    /**
     * 批量删除基础产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductInfoByIds(String[] ids);
}
