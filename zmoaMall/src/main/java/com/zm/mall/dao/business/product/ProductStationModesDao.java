package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.business.product.ProductStationModes;

import java.util.List;

/**
 * 属性 持久层
 * Created by lp on 2017/3/6.
 */
public interface ProductStationModesDao extends BaseDao<ProductStationModes> {
    //添加商品属性
    Integer AddStationModes(ProductStationModes productStationModes);
    //根据产品查属性
    List<ProductStationModes> selProductStationModesByProductId(ProductInfo productInfo);
    //根据属性差产品
    List<ProductStationModes> selProductStationModesByStationId(ProductStationModes productStationModes);
    //删除属性  根据产品
    Integer delProductStationModesByProductId(ProductInfo productInfo);
}
