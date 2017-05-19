package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.SupplierInfoDao;
import com.zm.mall.domain.business.product.BrandInfo;
import com.zm.mall.domain.business.product.SupplierBrands;
import com.zm.mall.domain.business.product.SupplierInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lp on 2016/11/18.
 */
public class SupplierInfoDaoImpl extends BaseDaoImpl<SupplierInfo> implements SupplierInfoDao {
    private final static String NAMESPACE = "com.zm.mall.dao.business.product.SupplierInfoDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public List<SupplierInfo> selAllById(Integer id) {
        return sqlTemplate.selectList(getNameSpace("selAllById"),id);
    }

    @Override
    public List<SupplierInfo> selAllSupplierInfo(String pluteformid, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pluteformid", pluteformid);
        map.put("shopId", shopId);
        return sqlTemplate.selectList(getNameSpace("selAllSupplierInfo"), map);
    }
    @Override
    public List<SupplierInfo> selectPage(SupplierInfo supplierInfo) {
        List<SupplierInfo> list=sqlTemplate.selectList(getNameSpace("select"),supplierInfo);
        return list;
    }

    @Override
    public SupplierInfo selectById(SupplierInfo supplierInfo) {
        SupplierInfo s= null;
        try {
            s = sqlTemplate.selectOne("selectSupplierInfo",supplierInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public int delete(SupplierInfo supplierInfo) {
        return sqlTemplate.update("deleteSupplierInfo",supplierInfo);
    }



    @Override
    public void insertSupplierInfo(SupplierInfo supplierInfo) {
        try {
            sqlTemplate.insert("saveSupplierInfo",supplierInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSupplierInfo(SupplierInfo supplierInfo) {
        try {
            sqlTemplate.update("updateSupplierInfo",supplierInfo);
            sqlTemplate.delete("deleteBrands",supplierInfo.getSupplierId());
            if(supplierInfo.getBrandInfos()!=null){
                List<SupplierBrands> list=new ArrayList<SupplierBrands>();
                for(BrandInfo b:supplierInfo.getBrandInfos()){
                    SupplierBrands sb=new SupplierBrands();
                    sb.setSupplierId(supplierInfo.getSupplierId());
                    sb.setBrandId(b.getBrandId());
                    list.add(sb);
                }
                sqlTemplate.insert("insertBrands",list);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<BrandInfo> getBrandInfos() {
        return sqlTemplate.selectList("selectAllBrand");
    }
}
