package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.ProductAttributeDao;
import com.zm.mall.domain.business.product.ProductAttribute;
import com.zm.mall.domain.business.product.ProductInfo;

/**
 * Created by lp on 2016/11/25.
 */
public class ProductAttributeDaoImpl extends BaseDaoImpl<ProductAttribute> implements ProductAttributeDao {
    private final static String NAMESPACE = "com.zm.mall.dao.business.product.ProductAttributeDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public Integer insertProductAttribute(ProductAttribute productAttribute) {
        return sqlTemplate.insert(getNameSpace("insertProductAttribute"),productAttribute);

    }
    @Override
    public Integer delProductAttribute(ProductInfo productInfo) {
        return sqlTemplate.insert(getNameSpace("delProductAttribute"),productInfo);
    }
}
