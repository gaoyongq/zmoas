package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.ProductImageDao;
import com.zm.mall.domain.business.product.ProductImage;
import com.zm.mall.domain.business.product.ProductInfo;

import java.util.List;

/**
 * Created by lp on 2016/12/6.
 */
public class ProductImageDaoImpl extends BaseDaoImpl<ProductImage> implements ProductImageDao {
    private final static String NAMESPACE = "com.zm.mall.dao.business.product.ProductImageDao.";
    @Override
    public String getNameSpace(String statement) {
        return  NAMESPACE+statement;
    }

    @Override
    public Integer insertProductImage(ProductImage productImage) {
        return sqlTemplate.insert(getNameSpace("insertProductImage"), productImage);
    }

    @Override
    public Integer delProductImage(ProductInfo productInfo) {
        return sqlTemplate.delete(getNameSpace("delProductImage"), productInfo);
    }

    @Override
    public List<ProductImage> selProductImageByproductId(ProductImage productImage) {
        return sqlTemplate.selectList(getNameSpace("selProductImageByproductId"),productImage);
    }
}
