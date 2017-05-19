package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.AttributeInfo;
import com.zm.mall.domain.business.product.ProductInfo;

import java.util.List;

/**
 * Created by lp on 2016/11/19.
 */
public interface AttributeInfoDao extends BaseDao<AttributeInfo> {
    //查询所有属性
    List<AttributeInfo> selProperty(AttributeInfo attributeInfo);
    //查询所有规格
    List<AttributeInfo> selSpec(AttributeInfo attributeInfo);
    //查询产品规格
    List<AttributeInfo> selSkuById(ProductInfo productInfo);
    //查询产品属性
    List<AttributeInfo> selSpecById(ProductInfo productInfo);

    /**
     * 白洋洋  根据类型Id和规格属性名确定属性Id,AttributeId,查询出对应的ValueStr
     */
    List<AttributeInfo> selectAttributeValue(AttributeInfo attributeInfo);}
