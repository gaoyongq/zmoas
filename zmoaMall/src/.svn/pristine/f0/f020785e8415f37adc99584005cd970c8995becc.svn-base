package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.SKURelationDao;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.business.product.SKURelation;

/**
 * Created by lp on 2016/11/25.
 */
public class SKURelationDaoImpl extends BaseDaoImpl<SKURelation> implements SKURelationDao{
    private final static String NAMESPACE = "com.zm.mall.dao.business.product.SKURelationDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public Integer insetSKURelation(SKURelation skuRelation) {
        return sqlTemplate.insert(getNameSpace("insetSKURelation"), skuRelation);
    }

    @Override
    public Integer delSKURelation(ProductInfo productInfo) {
        return sqlTemplate.delete(getNameSpace("delSKURelation"), productInfo);
    }
}
