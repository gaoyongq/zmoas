package com.zm.mall.service.business.product.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zm.mall.client.result.business.product.SupplierProductResult;
import com.zm.mall.client.service.business.product.SupplierProductService;
import com.zm.mall.client.vo.business.product.SupplierInfoVo;
import com.zm.mall.client.vo.business.product.SupplierProductVo;
import com.zm.mall.dao.business.product.SupplierProductDao;
import com.zm.mall.domain.business.product.SKUInfo;
import com.zm.mall.domain.business.product.SupplierInfo;
import com.zm.mall.domain.business.product.SupplierProduct;
import com.zm.mall.domain.system.Page;
import com.zm.mall.service.SpaceBeanCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/30.
 */
@Service("supplierProductService")
public class SupplierProductServiceImpl implements SupplierProductService{
    @Autowired
    private SupplierProductDao supplierProductDao;
    @Override
    public Map<String,Object> getProductBySupplier(int id,Page<SupplierProduct> page){
        if(page==null || page.getCurrentPage()==0){
            page.setCurrentPage(1);
        }
        page.setPageSize(20);
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SupplierProduct> list=supplierProductDao.getProductBySupplier(id);
        PageInfo<SupplierProduct> p=new PageInfo<SupplierProduct>(list);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("id",id);
        if(p.getList()!=null && p.getList().size()!=0){
            List<SupplierProductResult> supplierProductResults=new ArrayList<SupplierProductResult>();
            for(SupplierProduct s:list){
                supplierProductResults.add(SpaceBeanCopy.supplierProductToSupplierProductResult(s));
            }
            map.put("supplierProductResults",supplierProductResults);
        }
        page.setTotalPages(p.getPages());
        page.setTotalCount((int)p.getTotal());
        map.put("page",page);
        return map;
    }
    @Override
    public Map<String,Object> selectBlur(Integer id,String productName,String sku,Integer currentPage){
        SupplierProduct supplierProduct=new SupplierProduct();
        supplierProduct.setProductName(productName);
        supplierProduct.setSkuInfo(new SKUInfo());
        supplierProduct.getSkuInfo().setSku(sku);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("supplierProduct",supplierProduct);
        map.put("id",id);
       if (currentPage==null){
            currentPage=1;
        }
        PageHelper.startPage(currentPage,20);
        List<SupplierProduct> list=supplierProductDao.selectBlur(map);
        PageInfo<SupplierProduct> p=new PageInfo<SupplierProduct>(list);
        if(p.getList()!=null && p.getList().size()!=0){
            List<SupplierProductResult> supplierProductResults=new ArrayList<SupplierProductResult>();
            for(SupplierProduct s:list){
                supplierProductResults.add(SpaceBeanCopy.supplierProductToSupplierProductResult(s));
            }
            map.put("supplierProductResults",supplierProductResults);
        }
        Page<SupplierProduct> page=new Page<SupplierProduct>();
        page.setCurrentPage(currentPage);
        page.setTotalPages(p.getPages());
        page.setTotalCount((int)p.getTotal());
        map.put("page",page);
        return map;
    }

    @Override
    public void withdrawProduct(Integer supplierId,String skuIds){
        String[] str=skuIds.split(",");
        if (str!=null && str.length>0){
            for (String s:str){
                Map<String,Object> map=new HashMap<String, Object>();
                map.put("supplierId",supplierId);
                Long skuId=Long.valueOf(s);
                map.put("skuId",skuId);
                supplierProductDao.withdrawProduct(map);
            }
        }

    }

    @Override
    public Map<String,Object> selectOnly(Integer supplierId,Integer skuId,Page<SupplierProduct> page){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("supplierId",supplierId);
        map.put("skuId",skuId);
        SupplierProduct supplierProduct=supplierProductDao.selectOne(map);
        map.put("supplierProductResult",SpaceBeanCopy.supplierProductToSupplierProductResult(supplierProduct));
        map.put("page",page);
        return map;
    }

    @Override
    public void update(SupplierProductVo supplierProductVo){
       SupplierProduct supplierProduct= SpaceBeanCopy.supplierProductVoToSupplierProduct(supplierProductVo);
        supplierProductDao.updateProduct(supplierProduct);
    }


    public void addSupplierProduct(Long id,Long[]ids,Integer[]counts,Double[]prizes){
        if(ids!=null && ids.length>0){
            for(int i=0;i<ids.length;i++){
                SupplierProduct supplierProduct=new SupplierProduct();
                supplierProduct.setPrize(prizes[i]);
                supplierProduct.setCount(counts[i]);
                supplierProduct.setSkuInfo(new SKUInfo());
                supplierProduct.getSkuInfo().setSkuId(ids[i]);
                supplierProduct.setSupplierInfo(new SupplierInfo());
                supplierProduct.getSupplierInfo().setSupplierId(id);
                supplierProductDao.addSupplierProduct(supplierProduct);
            }
        }
    }

    @Override
    public List<SupplierProductResult> selectBySku(String sku){
        List<SupplierProduct> list=supplierProductDao.selectBySku(sku);
        if(list!=null && list.size()!=0){
            List<SupplierProductResult> supplierProductResults=new ArrayList<SupplierProductResult>();
            for(SupplierProduct s:list){
                supplierProductResults.add(SpaceBeanCopy.supplierProductToSupplierProductResult(s));
            }
            return supplierProductResults;
        }
        return null;
    }

    @Override
    public SupplierProductResult selectBySkuId(Integer id) {
        return  SpaceBeanCopy.supplierProductToSupplierProductResult(supplierProductDao.selectBySkuId(id));
    }

    @Override
    public List<SKUInfo> selectByProductId(Integer id,Integer supplierId){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("id",id);
        map.put("supplierId",supplierId);
        return supplierProductDao.selectByProductId(map);
    }
}
