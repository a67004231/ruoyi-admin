package com.ruoyi.product.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.product.domain.ProductAuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.ProductAuthMapper;
import com.ruoyi.product.domain.ProductAuth;
import com.ruoyi.product.service.IProductAuthService;
import com.ruoyi.common.core.text.Convert;

/**
 * 产品授权Service业务层处理
 * 
 * @author haoyu
 * @date 2021-09-08
 */
@Service
public class ProductAuthServiceImpl implements IProductAuthService 
{
    @Autowired
    private ProductAuthMapper productAuthMapper;

    /**
     * 查询产品授权
     * 
     * @param id 产品授权ID
     * @return 产品授权
     */
    @Override
    public ProductAuth selectProductAuthById(Long id)
    {
        return productAuthMapper.selectProductAuthById(id);
    }

    /**
     * 查询产品授权列表
     * 
     * @param productAuth 产品授权
     * @return 产品授权
     */
    @Override
    public List<ProductAuth> selectProductAuthList(ProductAuth productAuth)
    {
        return productAuthMapper.selectProductAuthList(productAuth);
    }

    /**
     * 新增产品授权
     * 
     * @param productAuth 产品授权
     * @return 结果
     */
    @Override
    public int insertProductAuth(ProductAuth productAuth)
    {
        productAuth.setCreateTime(DateUtils.getNowDate());
        return productAuthMapper.insertProductAuth(productAuth);
    }

    /**
     * 修改产品授权
     * 
     * @param productAuth 产品授权
     * @return 结果
     */
    @Override
    public int updateProductAuth(ProductAuth productAuth)
    {
        productAuth.setUpdateTime(DateUtils.getNowDate());
        return productAuthMapper.updateProductAuth(productAuth);
    }

    /**
     * 删除产品授权对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProductAuthByIds(String ids)
    {
        return productAuthMapper.deleteProductAuthByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品授权信息
     * 
     * @param id 产品授权ID
     * @return 结果
     */
    @Override
    public int deleteProductAuthById(Long id)
    {
        return productAuthMapper.deleteProductAuthById(id);
    }

    @Override
    public List<ProductAuthDto> selectMerProductAuthList(ProductAuthDto productAuthDto) {
        return productAuthMapper.selectMerProductAuthList(productAuthDto);
    }

    @Override
    public List<ProductAuthDto> selectChannelProductAuthList(ProductAuthDto productAuthDto) {
        return productAuthMapper.selectChannelProductAuthList(productAuthDto);
    }
}
