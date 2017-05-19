package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.business.product.SKUInfo;
import com.zm.mall.domain.business.product.SKUItem;

import java.util.List;

/**
 * Created by lp on 2016/11/25.
 */
public interface SKUItemDao extends BaseDao<SKUItem> {

    Integer insertSKUItem(SKUItem skuItem);

    SKUItem selSKUItem(SKUItem skuItem);

    Integer delSKUItem(ProductInfo productInfo);
    //根据skuID去查skuItem的值
    List<SKUItem> selSkuItemById(SKUInfo skuInfo);
}
