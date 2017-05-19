package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.AttributeValueDao;
import com.zm.mall.domain.business.product.AttributeValue;
import com.zm.mall.domain.business.product.ProductInfo;

/**
 * Created by lp on 2016/11/25.
 */
public class AttributeValueDaoImpl extends BaseDaoImpl<AttributeValue> implements AttributeValueDao {
    private final static String NAMESPACE = "com.zm.mall.dao.business.product.AttributeValueDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public Integer insertAttributeValue(AttributeValue attributeValue) {
        return sqlTemplate.insert(getNameSpace("insertAttributeValue"), attributeValue);
    }

    @Override
    public AttributeValue selAttributeValueId(AttributeValue attributeValue) {
        return sqlTemplate.selectOne(getNameSpace("selAttributeValueId"), attributeValue);
    }

    @Override
    public Integer delAttributeValue(ProductInfo productInfo) {
        return sqlTemplate.delete(getNameSpace("delAttributeValue"), productInfo);
    }
}
