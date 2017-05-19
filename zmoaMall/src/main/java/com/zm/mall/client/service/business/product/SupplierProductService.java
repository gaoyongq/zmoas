package com.zm.mall.client.service.business.product;

import com.zm.mall.client.result.business.product.SupplierProductResult;
import com.zm.mall.client.vo.business.product.SupplierProductVo;
import com.zm.mall.domain.business.product.SKUInfo;
import com.zm.mall.domain.business.product.SupplierProduct;
import com.zm.mall.domain.system.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/30.
 */
public interface SupplierProductService {
    public Map<String,Object> getProductBySupplier(int id,Page<SupplierProduct> page);
    public List<SupplierProductResult> selectBySku(String sku);
    public Map<String,Object> selectBlur(Integer id,String productName,String sku,Integer currentPage);
    public void withdrawProduct(Integer supplierId,String  skuIds);
    public Map<String,Object> selectOnly(Integer supplierId,Integer skuId,Page<SupplierProduct> page);
    public void update(SupplierProductVo supplierProductVo);
    public void addSupplierProduct(Long id,Long[]ids,Integer[]counts,Double[]prizes);
    //public List<SKUInfo> selectByProductId(Integer id);
    public SupplierProductResult selectBySkuId(Integer id);
    public List<SKUInfo> selectByProductId(Integer id,Integer supplierId);
}
