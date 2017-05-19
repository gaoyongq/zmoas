package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.SKUItemDao;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.business.product.SKUInfo;
import com.zm.mall.domain.business.product.SKUItem;

import java.util.List;

/**
 * Created by lp on 2016/11/25.
 */
public class SKUItemDaoImpl extends BaseDaoImpl<SKUItem> implements SKUItemDao {
    private final static String NAMESPACE = "com.zm.mall.dao.business.product.SKUItemDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public Integer insertSKUItem(SKUItem skuItem) {
        return sqlTemplate.insert(getNameSpace("insertSKUItem"), skuItem);
    }

    @Override
    public SKUItem selSKUItem(SKUItem skuItem) {
        return sqlTemplate.selectOne(getNameSpace("selSKUItem"),skuItem);
    }

    @Override
    public Integer delSKUItem(ProductInfo productInfo) {
        return sqlTemplate.delete(getNameSpace("delSKUItem"), productInfo);
    }

    @Override
    public List<SKUItem> selSkuItemById(SKUInfo skuInfo) {
        return sqlTemplate.selectList(getNameSpace("selSkuItemById"), skuInfo);
    }
}
