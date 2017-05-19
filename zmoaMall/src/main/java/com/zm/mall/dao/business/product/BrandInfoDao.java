package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.AttributeInfo;
import com.zm.mall.domain.business.product.BrandInfo;


import java.util.List;

/**
 * Created by lp on 2016/11/18.
 */
public interface BrandInfoDao extends BaseDao<BrandInfo> {
    /**
     * 查询全部
     * @return
     */
   List<BrandInfo> selBrandInfoByType(AttributeInfo attributeInfo);

    /**
     * 根据产品Id查询
     * @param brandInfo
     * @return
     */
   List<BrandInfo> selBrandInfoById(BrandInfo  brandInfo);

}
