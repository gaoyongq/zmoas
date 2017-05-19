package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.SKUInfoDao;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.business.product.SKUInfo;


import java.util.List;

/**
 * Created by lp on 2016/11/14.
 */
public class SKUInfoDaoImpl extends BaseDaoImpl<SKUInfo> implements SKUInfoDao {

    private final static String NAMESPACE = "com.zm.mall.dao.business.product.SKUInfoDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }


    @Override
    public Integer selectSkuCount(SKUInfo skuInfo) {
        return sqlTemplate.selectOne(getNameSpace("selectSkuCount"),skuInfo);
    }

    @Override
    public List<SKUInfo> selectById(Long productId) {
        return sqlTemplate.selectList(getNameSpace("selectSkuCount"), productId);
    }

    @Override
    public Integer insertSKUInfo(SKUInfo skuInfo) {
        return sqlTemplate.insert(getNameSpace("insertSKUInfo"), skuInfo);
    }

    @Override
    public SKUInfo selSKUInfo(SKUInfo skuInfo) {
        return sqlTemplate.selectOne(getNameSpace("selSKUInfo"), skuInfo);
    }

    @Override
    public Double selPrice( Long productId) {
        return sqlTemplate.selectOne(getNameSpace("selPrice"), productId);
    }

    @Override
    public List<SKUInfo> selSkuById(SKUInfo skuInfo) {
        return sqlTemplate.selectList(getNameSpace("selSkuById"), skuInfo);
    }

    @Override
    public Integer delSKUInfo(ProductInfo productInfo) {
        return sqlTemplate.delete(getNameSpace("delSKUInfo"), productInfo);
    }
    @Override
    public List<SKUInfo> selSkuInfoById(SKUInfo skuInfo) {
        return sqlTemplate.selectList(getNameSpace("selSkuInfoById"), skuInfo);
    }
}
