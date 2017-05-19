package com.zm.mall.client.service.business.product;

import com.zm.mall.client.PageVo;
import com.zm.mall.client.result.business.product.SupplierInfoResult;
import com.zm.mall.client.result.system.PageResult;
import com.zm.mall.client.vo.business.product.SupplierInfoVo;
import com.zm.mall.domain.business.product.BrandInfo;
import com.zm.mall.domain.business.product.SupplierInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */
public interface SupplierInfoService {
    public PageResult<SupplierInfo> select(PageVo<SupplierInfo> page) throws Exception;
    public SupplierInfoResult selectOne(SupplierInfoVo supplierInfoVo);
    public void delete(SupplierInfoVo supplierInfoVo);
    public void insert(SupplierInfoVo supplierInfoVo);
    public void update(SupplierInfoVo supplierInfoVo);
    public List<BrandInfo> getAllBrands();
}
