package com.zm.mall.service.business.product.impl;

import com.zm.mall.client.Page;
import com.zm.mall.client.service.business.product.ProductInfoService;
import com.zm.mall.client.service.business.product.ProductTypeService;
import com.zm.mall.client.vo.business.product.ProductTypeVo;
import com.zm.mall.dao.business.product.AttributeValueDao;
import com.zm.mall.dao.business.product.ProductAttributeDao;
import com.zm.mall.dao.business.product.ProductTypeDao;
import com.zm.mall.domain.business.product.ProductType;
import com.zm.mall.service.SpaceBeanCopy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lp on 2016/11/30.
 */
@Service("ProductTypeService")
public class ProductTypeServiceImpl implements ProductTypeService {
    private static Log log = LogFactory.getLog(ProductInfoService.class);
    @Autowired
    private ProductTypeDao productTypeDao;
    @Autowired
    private AttributeValueDao attributeValueDao;
    @Autowired
    private ProductAttributeDao productAttributeDao;

    @Override
    public Page selProductTypePage(ProductTypeVo productTypeVo, Page page) {
        ProductType productType = SpaceBeanCopy.productTypeVoToProductType(productTypeVo);
        page.setProductType(productType);
        //分页开始行数
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        return null;
    }
}
