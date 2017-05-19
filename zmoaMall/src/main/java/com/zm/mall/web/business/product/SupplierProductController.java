package com.zm.mall.web.business.product;



import com.zm.mall.client.result.business.product.SupplierProductResult;
import com.zm.mall.client.service.business.orders.OrderInfoService;
import com.zm.mall.client.service.business.product.ProductInfoService;
import com.zm.mall.client.service.business.product.SupplierProductService;
import com.zm.mall.client.vo.business.product.SupplierInfoVo;
import com.zm.mall.client.vo.business.product.SupplierProductVo;
import com.zm.mall.domain.business.product.SKUInfo;
import com.zm.mall.domain.business.product.SupplierProduct;
import com.zm.mall.domain.system.Page;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/30.
 */

@Controller
@RequestMapping("supplierProduct")
public class SupplierProductController {
    @Resource
    private SupplierProductService supplierProductService;
    @Resource
    private ProductInfoService productInfoService;

    /**
     * 分页查询供应商产品
     * @param id
     * @param page
     * @return
     */
    @RequestMapping("/getProductBySupplier.action")
    public ModelAndView getProductBySupplier(Integer id,Page<SupplierProduct> page){
        Map<String,Object> map=new HashMap<String, Object>();
        map= supplierProductService.getProductBySupplier(id, page);
        return new ModelAndView("business/supplierProducts",map);
    }

    /**
     * 模糊查询
     * @param id
     * @param productName
     * @param sku
     * @param currentPage
     * @return
     */
    @RequestMapping("/blur.action")
    @ResponseBody
    public Object selectBlur(Integer id,String productName,String sku,Integer currentPage){
        Map<String,Object> map=supplierProductService.selectBlur(id, productName, sku, currentPage);
        return map;
    }

    /**
     * 商品下架
     * @param skuId
     * @param id
     * @param page
     * @return
     */
    @RequestMapping("/withdrawProduct.action")
    public ModelAndView withdrawProduct(String skuIds,Integer id,Page<SupplierProduct> page){
        supplierProductService.withdrawProduct(id,skuIds);
        return getProductBySupplier(id,page);
    }

    /**
     * 跳转修改页面
     * @param skuId
     * @param id
     * @param page
     * @return
     */
    @RequestMapping("/toUpdatePage.action")
    public ModelAndView toUpdatePage(Integer skuId,Integer id,Page<SupplierProduct> page){
        Map<String,Object> map=supplierProductService.selectOnly(id, skuId, page);
        return new ModelAndView("business/supplierProduct",map);
    }

    /**
     * 提交修改表单
     * @param page
     * @param supplierProductVo
     * @return
     */
    @RequestMapping("/update.action")
    public ModelAndView updateSupplierProduct(Page<SupplierProduct> page,SupplierProductVo supplierProductVo){
        supplierProductService.update(supplierProductVo);
        Integer id=new Long(supplierProductVo.getSupplierInfo().getSupplierId()).intValue();
        return getProductBySupplier(id,page);
    }

    @RequestMapping("/toAddPage.action")
    public ModelAndView toAddPage(Integer id){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("id",id);
        return new ModelAndView("business/addSupplierProduct",map);
    }

    @RequestMapping("/add.action")
    public ModelAndView addSupplierProduct(Long id,Long[]ids,Integer[]counts,Double[]prizes,Page<SupplierProduct> page){
        try {
            supplierProductService.addSupplierProduct(id,ids,counts,prizes);
            Integer i=new Long(id).intValue();
            return getProductBySupplier(i,page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/selectByProductId.action")
    @ResponseBody
    public Object selectByProductId(Integer id,Integer supplierId){
        return supplierProductService.selectByProductId(id,supplierId);
    }

    @RequestMapping("/selectBySkuId.action")
    @ResponseBody
    public Object selectBySkuId(Integer id){
        return supplierProductService.selectBySkuId(id);
    }





}
