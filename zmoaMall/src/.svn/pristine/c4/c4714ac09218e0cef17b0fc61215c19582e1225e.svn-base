package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.ProductCategories;
import com.zm.mall.domain.business.product.ProductInfo;


import java.util.List;

/**
 * Created by lp on 2016/11/14.
 *产品与分类的关联操作
 */
public interface ProductCategoriesDao extends BaseDao<ProductCategories> {
    /*根据分类id查询*/
    ProductCategories selectByCategoryId(Integer categoryId);
    /*根据产品id查询*/
    List<ProductCategories> selectByProductId(ProductInfo productInfo);
    /*根据分类路径查询*/
    ProductCategories selectByCategoryPath(String categoriesPath);

    Integer InsertPath(ProductCategories productCategories);

    //删除
    Integer delProductCategoriesDao(ProductInfo productInfo);

}
