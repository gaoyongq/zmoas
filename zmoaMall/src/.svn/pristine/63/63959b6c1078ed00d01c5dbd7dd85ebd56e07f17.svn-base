package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.business.product.SKUInfo;


import java.util.List;

/**
 * Created by lp on 2016/11/14.
 * 库存操作
 */
public interface SKUInfoDao extends BaseDao<SKUInfo> {

    //查询库存数量
    Integer selectSkuCount(SKUInfo skuInfo);
    //根据id查询库存
    List<SKUInfo> selectById(Long productInfo);
    //添加
    Integer insertSKUInfo(SKUInfo skuInfo);
    //查询
    SKUInfo selSKUInfo(SKUInfo skuInfo);
    //查询最低销售价
    Double selPrice( Long productId);
    //根据id返回sku
    List<SKUInfo> selSkuById(SKUInfo skuInfo);
    //根据id删除
    Integer delSKUInfo(ProductInfo productInfo);
    //根据产品天规格
    List<SKUInfo> selSkuInfoById(SKUInfo skuInfo);
}
