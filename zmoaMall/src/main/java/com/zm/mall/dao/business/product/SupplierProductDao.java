package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.BrandInfo;
import com.zm.mall.domain.business.product.SKUInfo;
import com.zm.mall.domain.business.product.SupplierProduct;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/30.
 */
public interface SupplierProductDao extends BaseDao<SupplierProduct> {
    public List<SupplierProduct> getProductBySupplier(int supplierId);
    public List<SupplierProduct> selectBySku(String sku);
    public List<SupplierProduct> selectBlur(Map<String,Object> map);
    public void withdrawProduct(Map<String,Object> map);
    public SupplierProduct selectOne(Map<String,Object> map);
    public void updateProduct(SupplierProduct supplierProduct);
    public void addSupplierProduct(SupplierProduct supplierProduct);
   // public List<SKUInfo> selectByProductId(Integer id);
    public SupplierProduct selectBySkuId(Integer id);
    public List<SKUInfo> selectByProductId(Map<String,Object> map);
}
