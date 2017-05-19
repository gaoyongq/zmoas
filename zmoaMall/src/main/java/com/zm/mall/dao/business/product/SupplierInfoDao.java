package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.BrandInfo;
import com.zm.mall.domain.business.product.SupplierInfo;


import java.util.List;

/**
 * Created by lp on 2016/11/18.
 */
public interface SupplierInfoDao extends BaseDao<SupplierInfo> {
    /**
     * 查询生产商
     * @return
     */
    List<SupplierInfo> selAllSupplierInfo(String platformId, Long shopId);
    List<SupplierInfo> selAllById(Integer id);
    public SupplierInfo selectById(SupplierInfo supplierInfo);
    public void insertSupplierInfo(SupplierInfo supplierInfo);
    public void updateSupplierInfo(SupplierInfo supplierInfo);
    public List<BrandInfo> getBrandInfos();
}
