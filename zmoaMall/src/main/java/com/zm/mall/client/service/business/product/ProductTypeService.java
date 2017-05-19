package com.zm.mall.client.service.business.product;

import com.zm.mall.client.Page;
import com.zm.mall.client.vo.business.product.ProductTypeVo;
import com.zm.mall.domain.business.product.ProductType;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/**
 * Created by lp on 2016/11/30.
 */
public interface ProductTypeService {
    /**
     * 分页
     * @param productTypeVo
     * @param page
     * @return
     */
     Page selProductTypePage(ProductTypeVo productTypeVo,Page page);
}
