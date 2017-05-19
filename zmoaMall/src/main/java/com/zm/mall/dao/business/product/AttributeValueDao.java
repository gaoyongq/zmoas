package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.AttributeValue;
import com.zm.mall.domain.business.product.ProductInfo;

/**
 * Created by lp on 2016/11/25.
 */
public interface AttributeValueDao extends BaseDao<AttributeValue> {
    //添加值
    Integer insertAttributeValue(AttributeValue attributeValue);
    //查id
    AttributeValue selAttributeValueId(AttributeValue attributeValue);
    //删除input值
    Integer delAttributeValue(ProductInfo productInfo);
}
