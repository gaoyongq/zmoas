package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.business.product.SKURelation;

/**
 * Created by lp on 2016/11/25.
 */
public interface SKURelationDao extends BaseDao<SKURelation> {
    //插入关联关系
    Integer insetSKURelation(SKURelation skuRelation);
    //根据ID删除
    Integer delSKURelation(ProductInfo ProductInfo);
}
