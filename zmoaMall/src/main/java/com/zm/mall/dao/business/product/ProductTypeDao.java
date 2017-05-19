package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.ProductType;

import java.util.List;

/**
 * Created by lp on 2016/11/18.
 */
public interface ProductTypeDao extends BaseDao<ProductType>{

    List<ProductType> selAllProductType(String platformId, Long shopId);
    /**
     * 根据产品查
     * @return
     */
    List<ProductType> selectTypeById(ProductType productType);
}
