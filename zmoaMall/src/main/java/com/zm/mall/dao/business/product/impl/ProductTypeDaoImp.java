package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.ProductTypeDao;
import com.zm.mall.domain.business.product.ProductType;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lp on 2016/11/18.
 */
public class ProductTypeDaoImp extends BaseDaoImpl<ProductType> implements ProductTypeDao {

    private final static String NAMESPACE = "com.zm.mall.dao.business.product.ProductTypeDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public List<ProductType> selAllProductType(String pluteformid, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pluteformid", pluteformid);
        map.put("shopId", shopId);
        return sqlTemplate.selectList(getNameSpace("selAllProductType"), map);
    }
    @Override
    public List<ProductType> selectTypeById(ProductType productType) {
        return sqlTemplate.selectList(getNameSpace("selectTypeById"),productType);
    }
}
