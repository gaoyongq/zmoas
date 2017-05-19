package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.SupplierProductDao;
import com.zm.mall.domain.business.product.SKUInfo;
import com.zm.mall.domain.business.product.SupplierProduct;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/30.
 */
public class SupplierProductDaoImpl extends BaseDaoImpl<SupplierProduct>  implements SupplierProductDao{
    private final static String NAMESPACE = "com.zm.mall.dao.business.product.SupplierProductDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }
    @Override
    public List<SupplierProduct> getProductBySupplier(int supplierId){
        try {
            return  sqlTemplate.selectList("selectSupplierProductBySupplierId",supplierId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SupplierProduct> selectBlur(Map<String, Object> map) {
        try {
             List<SupplierProduct> list=sqlTemplate.selectList("selectBlur",map);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void withdrawProduct(Map<String,Object> map) {
        try {
            sqlTemplate.update("withdrawProduct",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SupplierProduct selectOne(Map<String, Object> map) {
        try {
            return sqlTemplate.selectOne("selectOnly",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void updateProduct(SupplierProduct supplierProduct){
        try {
            sqlTemplate.update("updateSupplierProduct",supplierProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSupplierProduct(SupplierProduct supplierProduct) {
        sqlTemplate.insert("addSupplierProduct",supplierProduct);
    }

    @Override
    public List<SKUInfo> selectByProductId(Map<String,Object> map) {
        try {
            return sqlTemplate.selectList("findByProductId",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SupplierProduct selectBySkuId(Integer id) {
        try {
            return sqlTemplate.selectOne("selectBySkuId",id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SupplierProduct> selectBySku(String sku) {
        return sqlTemplate.selectList("selectBySku",sku);
    }
}
