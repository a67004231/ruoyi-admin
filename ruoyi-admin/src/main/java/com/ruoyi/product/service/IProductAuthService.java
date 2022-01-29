package com.ruoyi.product.service;

import java.util.List;
import com.ruoyi.product.domain.ProductAuth;
import com.ruoyi.product.domain.ProductAuthDto;

/**
 * 产品授权Service接口
 * 
 * @author haoyu
 * @date 2021-09-08
 */
public interface IProductAuthService 
{
    /**
     * 查询产品授权
     * 
     * @param id 产品授权ID
     * @return 产品授权
     */
    public ProductAuth selectProductAuthById(Long id);

    /**
     * 查询产品授权列表
     * 
     * @param productAuth 产品授权
     * @return 产品授权集合
     */
    public List<ProductAuth> selectProductAuthList(ProductAuth productAuth);

    /**
     * 新增产品授权
     * 
     * @param productAuth 产品授权
     * @return 结果
     */
    public int insertProductAuth(ProductAuth productAuth);

    /**
     * 修改产品授权
     * 
     * @param productAuth 产品授权
     * @return 结果
     */
    public int updateProductAuth(ProductAuth productAuth);

    /**
     * 批量删除产品授权
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductAuthByIds(String ids);

    /**
     * 删除产品授权信息
     * 
     * @param id 产品授权ID
     * @return 结果
     */
    public int deleteProductAuthById(Long id);

    List<ProductAuthDto> selectMerProductAuthList(ProductAuthDto productAuthDto);

    List<ProductAuthDto> selectChannelProductAuthList(ProductAuthDto productAuthDto);
}
