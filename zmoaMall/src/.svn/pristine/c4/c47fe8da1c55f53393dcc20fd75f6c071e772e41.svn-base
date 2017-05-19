package com.zm.mall.web.business.product;

import com.zm.mall.client.Page;
import com.zm.mall.client.PageVo;
import com.zm.mall.client.service.business.product.ProductTypeService;
import com.zm.mall.client.vo.business.product.ProductTypeVo;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by lp on 2016/11/30.
 */
@Controller
@RequestMapping("/ProductType")
public class ProductTypeController extends BaseController {
    @Resource
    private ProductTypeService productTypeService;
    @RequestMapping(value = "/productType.action")
    public ModelAndView productType() throws Exception{
        try{
            return new ModelAndView("/business/typeShow");
        }catch (Exception e){
            throw e;
        }
    }
@RequestMapping(value = "/selProductType.action")
    public Page productType(String typeName,Integer currentPage)throws Exception{
        try{
            Page page = new Page();
            ProductTypeVo productTypeVo = new ProductTypeVo();
            productTypeVo.setTypeName(typeName);
            Page resultPage =null;
            return resultPage;
        }catch (Exception e){
            throw e;
        }
    }

}
