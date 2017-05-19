package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.business.product.ProductStationModes;

import java.util.List;

/**
 * Created by lp on 2017/3/6.
 */
public class ProductStationModesDaoImpl extends BaseDaoImpl<ProductStationModes> implements com.zm.mall.dao.business.product.ProductStationModesDao {
    private static final String NAMESPACE="com.zm.mall.dao.business.product.ProductStationModesDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public Integer AddStationModes(ProductStationModes productStationModes) {
        return sqlTemplate.insert(getNameSpace("AddStationModes"), productStationModes);
    }

    @Override
    public List<ProductStationModes> selProductStationModesByProductId(ProductInfo productInfo) {
        return sqlTemplate.selectList(getNameSpace("selProductStationModesByProductId"), productInfo);
    }

    @Override
    public List<ProductStationModes> selProductStationModesByStationId(ProductStationModes productStationModes) {
        return sqlTemplate.selectList(getNameSpace("selProductStationModesByStationId"), productStationModes);
    }

    @Override
    public Integer delProductStationModesByProductId(ProductInfo productInfo) {
        return sqlTemplate.delete(getNameSpace("delProductStationModesByProductId"), productInfo);
    }
}
