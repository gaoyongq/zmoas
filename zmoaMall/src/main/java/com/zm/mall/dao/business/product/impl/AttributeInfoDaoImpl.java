package com.zm.mall.dao.business.product.impl;


import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.AttributeInfoDao;
import com.zm.mall.domain.business.product.AttributeInfo;
import com.zm.mall.domain.business.product.ProductInfo;

import java.util.List;

/**
 * Created by lp on 2016/11/19.
 */
public class AttributeInfoDaoImpl extends BaseDaoImpl<AttributeInfo> implements AttributeInfoDao {
    private final static String NAMESPACE = "com.zm.mall.dao.business.product.AttributeInfoDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public List<AttributeInfo> selProperty(AttributeInfo attributeInfo) {
        return sqlTemplate.selectList(getNameSpace("selProperty"), attributeInfo);
    }

    @Override
    public List<AttributeInfo> selSpec(AttributeInfo attributeInfo) {
        return sqlTemplate.selectList(getNameSpace("selSpec"), attributeInfo);
    }

    @Override
    public List<AttributeInfo> selSkuById(ProductInfo productInfo) {
        return sqlTemplate.selectList(getNameSpace("selSkuById"), productInfo);
    }

    @Override
    public List<AttributeInfo> selSpecById(ProductInfo productInfo) {
        return sqlTemplate.selectList(getNameSpace("selSpecById"), productInfo);
    }
    /**
     * 白洋洋  根据类型Id和规格属性名确定属性Id,AttributeId,查询出对应的ValueStr
     */
    @Override
    public List<AttributeInfo> selectAttributeValue(AttributeInfo attributeInfo) {
        return sqlTemplate.selectList(getNameSpace("selectValueStr"),attributeInfo);
    }}
