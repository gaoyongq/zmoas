package com.zm.mall.dao.business.product.impl;


import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.BrandInfoDao;
import com.zm.mall.domain.business.product.AttributeInfo;
import com.zm.mall.domain.business.product.BrandInfo;

import java.util.List;

/**
 * Created by lp on 2016/11/18.
 */
public class BrandInfoDaoImpl extends BaseDaoImpl<BrandInfo> implements BrandInfoDao {
    private final static String NAMESPACE = "com.zm.mall.dao.business.product.BrandInfoDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public List<BrandInfo> selBrandInfoByType(AttributeInfo attributeInfo) {
        return sqlTemplate.selectList(getNameSpace("selBrandInfoByType"),attributeInfo);
    }
    @Override
    public List<BrandInfo> selBrandInfoById(BrandInfo brandInfo) {
        return sqlTemplate.selectList(getNameSpace("selBrandInfoById"),brandInfo);
    }
}
