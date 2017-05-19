package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.ProductCategoriesDao;
import com.zm.mall.domain.business.product.ProductCategories;
import com.zm.mall.domain.business.product.ProductInfo;


import java.util.List;

/**
 * Created by lp on 2016/11/14.
 */
public class ProductCategoriesDaoImpl extends BaseDaoImpl<ProductCategories> implements ProductCategoriesDao {
    private final static String NAMESPACE = "com.zm.mall.dao.business.product.ProductCategoriesDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public ProductCategories selectByCategoryId(Integer categoryId) {
        return sqlTemplate.selectOne(getNameSpace("selectByCategoryId"),categoryId);
    }

    @Override
    public List<ProductCategories> selectByProductId(ProductInfo productInfo) {
        return sqlTemplate.selectList(getNameSpace("selectByProductId"),productInfo);
    }

    @Override
    public ProductCategories selectByCategoryPath(String categoriesPath) {
        return sqlTemplate.selectOne(getNameSpace("selectByCategoryPath"),categoriesPath);
    }

    @Override
    public Integer InsertPath(ProductCategories productCategories) {
        return sqlTemplate.insert(getNameSpace("InsertPath"), productCategories);
    }

    @Override
    public Integer delProductCategoriesDao(ProductInfo productInfo) {
        return  sqlTemplate.delete(getNameSpace("delProductCategoriesDao"), productInfo);
    }
}
