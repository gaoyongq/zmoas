package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.RelatedProductDao;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.business.product.RelatedProduct;

/**
 * Created by lp on 2016/11/27.
 */
public class RelatedProductDaoImpl extends BaseDaoImpl<RelatedProduct> implements RelatedProductDao {

    private final static String NAMESPACE = "com.zm.mall.dao.business.product.RelatedProductDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public Integer insertRelatedProduct(RelatedProduct relatedProduct) {
        return sqlTemplate.selectOne(getNameSpace("insertRelatedProduct"), relatedProduct);
    }

    @Override
    public Integer delRelatedProduct(ProductInfo productInfo) {
        return sqlTemplate.delete(getNameSpace("delRelatedProduct"), productInfo);
    }
}
