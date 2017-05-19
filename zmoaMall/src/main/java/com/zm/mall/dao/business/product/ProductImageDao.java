package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.ProductImage;
import com.zm.mall.domain.business.product.ProductInfo;

import java.util.List;

/**
 * Created by lp on 2016/12/6.
 */
public interface ProductImageDao extends BaseDao<ProductImage> {
    //插入图片
    public Integer insertProductImage(ProductImage productImage);
    //删除
    Integer delProductImage(ProductInfo productInfo);
    //根据id找图片
    List<ProductImage> selProductImageByproductId(ProductImage productImage);
}
