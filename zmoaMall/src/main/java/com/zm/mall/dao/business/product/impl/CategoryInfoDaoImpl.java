package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.CategoryInfoDao;
import com.zm.mall.domain.business.product.CategoryInfo;


import java.util.List;

/**
 * Created by lp on 2016/11/14.
 */
public class CategoryInfoDaoImpl extends BaseDaoImpl<CategoryInfo> implements CategoryInfoDao {
    private final static String NAMESPACE = "com.zm.mall.dao.business.product.CategoryInfoDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public List<CategoryInfo> selectAll(CategoryInfo categoryInfo) {
        return sqlTemplate.selectList(getNameSpace("selectAll"),categoryInfo);
    }

    @Override
    public CategoryInfo price(CategoryInfo categoryInfo) {
        return sqlTemplate.selectOne(getNameSpace("price"),categoryInfo);
    }

    @Override
    public List<CategoryInfo> categoryInfoListById(CategoryInfo categoryInfo) {
        return sqlTemplate.selectList(getNameSpace("categoryInfoListById"),categoryInfo);
    }

    @Override
    public List<CategoryInfo> categoryInfoListByIdOnIn(String[] CategoryId) {
        return sqlTemplate.selectList(getNameSpace("categoryInfoListByIdOnIn"),CategoryId);
    }

    @Override
    public List<CategoryInfo> weCartAddAppSelectCategory(CategoryInfo categoryInfo) {
        return sqlTemplate.selectList(getNameSpace("weCartAddAppSelectCategory"),categoryInfo);
    }
}
