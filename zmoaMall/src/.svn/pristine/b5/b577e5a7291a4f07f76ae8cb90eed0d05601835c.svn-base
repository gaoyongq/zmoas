package com.zm.mall.web.business.product;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import com.zm.mall.client.result.business.product.CategoryInfoResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.business.product.ProductCategoryService;
import com.zm.mall.domain.business.product.RegionCatDto;
import com.zm.mall.domain.business.orders.FilterObj;
import com.zm.mall.domain.business.product.*;
import com.zm.mall.domain.business.shop.WeChatIdentity;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import com.zm.mall.domain.system.systemCode.EasyUIResult;
import com.zm.mall.util.QRCodeUtils;
import com.zm.mall.util.UploadUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.Region;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 产品分类、类型、品牌
 * Created by Bochao on 2017/3/22.
 */
@Controller
@RequestMapping("/business/prodCat")
public class ProductCategoryController {

    @Resource
    private ProductCategoryService productCategoryService;
    @Resource
    private UploadUtils uploadUtils;
    @Resource
    private QRCodeUtils qrCodeUtils;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /*--------------------------产品分类 Start---------------------------*/

    /**
     * 分页获取产品分类列表
     * @param paginationCategoryInfo
     * @return
     */
    @RequestMapping("/getPageProdCatList")
    @ResponseBody
    public EasyUIListResult getPageProdCatList(HttpServletRequest request, PaginationCategoryInfo paginationCategoryInfo) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return new EasyUIListResult();
        }
        paginationCategoryInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            paginationCategoryInfo.setShopId(userResult.getShopId());
            paginationCategoryInfo.setPluteformid(userResult.getParentPluteformId());
        }
        try {
            return productCategoryService.getPageProdCatList(paginationCategoryInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIListResult();
        }
    }

    /**
     * 分页查询产品分类，EasyUI树形展现，异步加载子类型
     * @param paginationCategoryInfo
     * @return
     */
    @RequestMapping("/getPageProdCatListByParentId")
    @ResponseBody
    public List<EasyUITreeCatInfo> getPageProdCatListByParentId(HttpServletRequest request, PaginationCategoryInfo paginationCategoryInfo) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        paginationCategoryInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            paginationCategoryInfo.setShopId(userResult.getShopId());
            paginationCategoryInfo.setPluteformid(userResult.getParentPluteformId());
        }
        try {
            return productCategoryService.getPageProdCatListByParentId(paginationCategoryInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取产品分类列表
     * @return
     */
    @RequestMapping("/getProdCatList")
    @ResponseBody
    public List<CategoryInfo> getProdCatList(CategoryInfo categoryInfo) {
        return productCategoryService.getProdCatList(categoryInfo);
    }

    /**
     * 添加产品分类
     * @param categoryInfo
     * @return
     */
    @RequestMapping("/addProdCat")
    @ResponseBody
    public EasyUIResult addProdCat(HttpServletRequest request, MultipartFile file, CategoryInfo categoryInfo) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        categoryInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            categoryInfo.setShopId(userResult.getShopId());
            categoryInfo.setPluteformid(userResult.getParentPluteformId());
        }


        if (categoryInfo.getParentCategoryId() == null) {
            categoryInfo.setParentCategoryId(0);
        }
        try {
            String relUrl = uploadUtils.upload(file, "Picture", "ProductCategory");
            categoryInfo.setImageUrl(relUrl);
        } catch (IOException e) {
            e.printStackTrace();
            categoryInfo.setImageUrl(null);
        }
        try {
            productCategoryService.addProdCat(categoryInfo);
            return new EasyUIResult();
        } catch (Exception e) {
            return new EasyUIResult(e.getMessage());
        }
    }

    /**
     * 更新产品分类
     * @param categoryInfo
     * @return
     */
    @RequestMapping("/updateProdCat")
    @ResponseBody
    public EasyUIResult updateProdCat(HttpServletRequest request, CategoryInfo categoryInfo, String status) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        categoryInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            categoryInfo.setShopId(userResult.getShopId());
            categoryInfo.setPluteformid(userResult.getParentPluteformId());
        }
        if (status == null) {
            categoryInfo.setStatus(false);
        }
        try {
            productCategoryService.updateProdCat(categoryInfo);
            return new EasyUIResult();
        } catch (Exception e) {
            return new EasyUIResult(e.getMessage());
        }
    }

    /**
     * 更新产品分类状态
     * @param categoryInfo
     * @return
     */
    @RequestMapping("/updateProdCatStatus")
    @ResponseBody
    public EasyUIResult updateProdCatStatus(CategoryInfo categoryInfo) {
        try {
            productCategoryService.updateProdCatStatus(categoryInfo);
            return new EasyUIResult();
        } catch (Exception e) {
            return new EasyUIResult(e.getMessage());
        }
    }

    /*--------------------------产品分类 End---------------------------*/


    /*--------------------------产品类型 Start---------------------------*/

    @RequestMapping("/getProdTypes")
    @ResponseBody
    public EasyUIListResult getProdTypes(HttpServletRequest request, String filterRules, PaginationProductType productType) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        productType.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            productType.setShopId(userResult.getShopId());
            productType.setPluteformid(userResult.getParentPluteformId());
        }
        //EasyUI筛选功能支持
        if (filterRules != null) {
            try {
                List<FilterObj> list = MAPPER.readValue(filterRules, MAPPER.getTypeFactory().constructCollectionType(List.class, FilterObj.class));
                for (FilterObj obj : list) {
                    System.out.println(Arrays.toString(productType.getClass().getDeclaredFields()));
                    Field field = null;
                    try {
                        field = productType.getClass().getDeclaredField(obj.getField());
                    } catch (NoSuchFieldException e) {
                        field = productType.getClass().getSuperclass().getDeclaredField(obj.getField());
                    }
                    field.setAccessible(true);
                    if (obj.getField().equals("typeId")) {
                        field.set(productType, Integer.valueOf(obj.getValue()));
                    } else {
                        field.set(productType, obj.getValue());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productCategoryService.getProdTypes(productType);
    }

    @RequestMapping("/getProdTypeList")
    @ResponseBody
    public List<ProductType> getProdTypeList(HttpServletRequest request) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        String platformId = userResult.getPluteformid();
        if (userResult.getShopId() != null) {
            platformId = userResult.getParentPluteformId();
        }
        return productCategoryService.getProdTypeList(platformId, userResult.getShopId());
    }

    /**
     * 添加商品类型
     * @param request
     * @param productType
     * @param brandIds
     * @return
     */
    @RequestMapping("/addProdType")
    @ResponseBody
    public EasyUIResult addProdType(HttpServletRequest request, ProductType productType, Long[] brandIds) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return new EasyUIResult("权限不足");
        }
        productType.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            productType.setShopId(userResult.getShopId());
            productType.setPluteformid(userResult.getParentPluteformId());
        }
        try {
            productCategoryService.addProdType(productType, brandIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult(e.getMessage());
        }
        EasyUIResult result = new EasyUIResult();
        result.setData(String.valueOf(productType.getTypeId()));
        return result;
    }

    @RequestMapping("/updateProdType")
    @ResponseBody
    public EasyUIResult updateProdType(HttpServletRequest request, ProductType productType, Long[] brandIds) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return new EasyUIResult("权限不足");
        }
        productType.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            productType.setShopId(userResult.getShopId());
            productType.setPluteformid(userResult.getParentPluteformId());
        }
        try {
            productCategoryService.updateProdType(productType, brandIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult(e.getMessage());
        }
        return new EasyUIResult();
    }

    /*--------------------------产品类型 End---------------------------*/


    /*--------------------------产品类型属性 Start---------------------------*/

    @RequestMapping("/getProdTypeAttrsByTypeId")
    @ResponseBody
    public EasyUIListResult getProdTypeAttrsByTypeId(HttpServletRequest request, PaginationAttributeInfo attributeInfo) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        attributeInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            attributeInfo.setShopId(userResult.getShopId());
            attributeInfo.setPluteformid(userResult.getParentPluteformId());
        }
        return productCategoryService.getProdTypeAttrsByTypeId(attributeInfo);
    }

    /**
     * 添加产品类型属性
     * @param request
     * @param attributeInfo
     * @return
     */
    @RequestMapping("/addProdTypeAttr")
    @ResponseBody
    public EasyUIResult addProdTypeAttr(HttpServletRequest request, AttributeInfo attributeInfo) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        attributeInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            attributeInfo.setShopId(userResult.getShopId());
            attributeInfo.setPluteformid(userResult.getParentPluteformId());
        }
        attributeInfo.setUseAttributeImage(false);
        try {
            if (attributeInfo.getAttributeId() == null) {
                productCategoryService.addProdTypeAttr(attributeInfo);
            } else {
                productCategoryService.updateProdTypeAttr(attributeInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult(e.getMessage());
        }
        return new EasyUIResult();
    }

    /**
     * 修改产品类型属性
     * @param request
     * @param attributeInfo
     * @return
     */
    @RequestMapping("/updateProdTypeAttr")
    @ResponseBody
    public EasyUIResult updateProdTypeAttr(HttpServletRequest request, AttributeInfo attributeInfo) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        attributeInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            attributeInfo.setShopId(userResult.getShopId());
            attributeInfo.setPluteformid(userResult.getParentPluteformId());
        }
        productCategoryService.updateProdTypeAttr(attributeInfo);

        return new EasyUIResult();
    }

    /*--------------------------产品类型属性 End---------------------------*/

    /*--------------------------产品类型规格 Start---------------------------*/

    @RequestMapping("/getProdTypeSpecsByTypeId")
    @ResponseBody
    public EasyUIListResult getProdTypeSpecsByTypeId(HttpServletRequest request, PaginationAttributeInfo attributeInfo) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        attributeInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            attributeInfo.setShopId(userResult.getShopId());
            attributeInfo.setPluteformid(userResult.getParentPluteformId());
        }
        return productCategoryService.getProdTypeSpecsByTypeId(attributeInfo);
    }

    /**
     * 添加产品类型规格
     * @param request
     * @param attributeInfo
     * @return
     */
    @RequestMapping("/addProdTypeSpec")
    @ResponseBody
    public EasyUIResult addProdTypeSpec(HttpServletRequest request, AttributeInfo attributeInfo) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        attributeInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            attributeInfo.setShopId(userResult.getShopId());
            attributeInfo.setPluteformid(userResult.getParentPluteformId());
        }
        try {
            productCategoryService.addProdTypeAttr(attributeInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult(e.getMessage());
        }
        return new EasyUIResult();
    }

    /**
     * 修改产品类型规格
     * @param request
     * @param attributeInfo
     * @return
     */
    @RequestMapping("/updateProdTypeSpec")
    @ResponseBody
    public EasyUIResult updateProdTypeSpec(HttpServletRequest request, AttributeInfo attributeInfo) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        attributeInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            attributeInfo.setShopId(userResult.getShopId());
            attributeInfo.setPluteformid(userResult.getParentPluteformId());
        }
        productCategoryService.updateProdTypeAttr(attributeInfo);

        return new EasyUIResult();
    }

    /**
     * 添加图片规格值
     */
    @RequestMapping("/addPicSpecValue")
    @ResponseBody
    public EasyUIResult addPicSpecValue(HttpServletRequest request, MultipartFile file, AttributeValue attributeValue) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        attributeValue.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            attributeValue.setShopId(userResult.getShopId());
            attributeValue.setPluteformid(userResult.getParentPluteformId());
        }
        try {
            String relUrl = uploadUtils.upload(file, "Picture", "Product/Type/Spec");
            attributeValue.setImageUrl(relUrl);
        } catch (IOException e) {
            e.printStackTrace();
            attributeValue.setImageUrl("");
        }
        try {
            productCategoryService.addSpecValue(attributeValue);
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult(e.getMessage());
        }
        return new EasyUIResult();
    }

    /**
     * 添加文字规格值
     */
    @RequestMapping("/addCharSpecValue")
    @ResponseBody
    public EasyUIResult addCharSpecValue(HttpServletRequest request, AttributeValue attributeValue) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        attributeValue.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            attributeValue.setShopId(userResult.getShopId());
            attributeValue.setPluteformid(userResult.getParentPluteformId());
        }
        try {
            String[] values = attributeValue.getValueStr().split(",|，");
            for (String value : values) {
                AttributeValue attrValue = new AttributeValue();
                BeanUtils.copyProperties(attributeValue, attrValue);
                attrValue.setValueStr(value);
                productCategoryService.addSpecValue(attrValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult(e.getMessage());
        }
        return new EasyUIResult();
    }

    /*--------------------------产品类型规格 End---------------------------*/

    /*--------------------------产品品牌 Start---------------------------*/
    @RequestMapping("/getProdBrands")
    @ResponseBody
    public List<BrandInfo> getProdBrands(HttpServletRequest request) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        String platformId = userResult.getPluteformid();
        if (userResult.getShopId() != null) {
            platformId = userResult.getParentPluteformId();
        }
        return productCategoryService.getProdBrandsByPlatformId(platformId, userResult.getShopId());
    }

    @RequestMapping("/getTypeBrandIdsByTypeId")
    @ResponseBody
    public List<Long> getTypeBrandIdsByTypeId(HttpServletRequest request, Long typeId) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        String platformId = userResult.getPluteformid();
        if (userResult.getShopId() != null) {
            platformId = userResult.getParentPluteformId();
        }
        return productCategoryService.getTypeBrandIdsByTypeId(typeId, platformId, userResult.getShopId());
    }

    @RequestMapping("/addTypeBrand")
    @ResponseBody
    public EasyUIResult addTypeBrand(HttpServletRequest request, BrandInfo brandInfo, Integer[] prodTypeIds, MultipartFile file) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        brandInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            brandInfo.setShopId(userResult.getShopId());
            brandInfo.setPluteformid(userResult.getParentPluteformId());
        }
        try {
            String relUrl = uploadUtils.upload(file, "Picture", "Product/Brand/Logo");
            brandInfo.setLogo(relUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (prodTypeIds != null) {
            brandInfo.setProductTypes(Arrays.asList(prodTypeIds));
        }
        productCategoryService.addTypeBrand(brandInfo);
        return new EasyUIResult();
    }

    @RequestMapping("/updateTypeBrand")
    @ResponseBody
    public EasyUIResult updateTypeBrand(HttpServletRequest request, BrandInfo brandInfo, Integer[] prodTypeIds, MultipartFile file) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        brandInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            brandInfo.setShopId(userResult.getShopId());
            brandInfo.setPluteformid(userResult.getParentPluteformId());
        }
        try {
            String relUrl = uploadUtils.upload(file, "Picture", "Product/Brand/Logo");
            brandInfo.setLogo(relUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (prodTypeIds != null) {
            brandInfo.setProductTypes(Arrays.asList(prodTypeIds));
        }
        productCategoryService.updateTypeBrand(brandInfo);
        return new EasyUIResult();
    }

    @RequestMapping("/deleteTypeBrand")
    @ResponseBody
    public EasyUIResult deleteTypeBrand(HttpServletRequest request, BrandInfo brandInfo) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        brandInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            brandInfo.setShopId(userResult.getShopId());
            brandInfo.setPluteformid(userResult.getParentPluteformId());
        }
        productCategoryService.deleteTypeBrand(brandInfo);
        return new EasyUIResult();
    }

    @RequestMapping("/getTypeBrandList")
    @ResponseBody
    public EasyUIListResult getTypeBrandList(HttpSession session, PaginationBrandInfo brandInfo) {
        UserResult userResult = (UserResult) session.getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        brandInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            brandInfo.setShopId(userResult.getShopId());
            brandInfo.setPluteformid(userResult.getParentPluteformId());
        }
        return productCategoryService.getTypeBrandList(brandInfo);
    }

    @RequestMapping("/typeBrandList")
    public String typeBrandList(HttpServletRequest request) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        return "business/productCat/typeBrand/typeBrandList";
    }

    @RequestMapping("/addTypeBrandDlg")
    public String addTypeBrandDlg(HttpServletRequest request) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        return "business/productCat/typeBrand/addTypeBrandDlg";
    }

    @RequestMapping("/updateTypeBrandDlg")
    public String updateTypeBrandDlg(HttpServletRequest request, Model model, BrandInfo brandInfo) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        brandInfo.setPluteformid(userResult.getPluteformid());
        if (userResult.getShopId() != null) {
            brandInfo.setShopId(userResult.getShopId());
            brandInfo.setPluteformid(userResult.getParentPluteformId());
        }
        BrandInfo brand = productCategoryService.getTypeBrand(brandInfo);
        model.addAttribute("brand", brand);
        return "business/productCat/typeBrand/updateTypeBrandDlg";
    }

    /*--------------------------产品品牌 End---------------------------*/


    /*-----------------------分类按区域显示控制 Start---------------------*/

    /**
     * 控制分类是否显示
     * @return
     */
    @RequestMapping("/catAuth/availableCat")
    public String availableCat() {
        return "business/productCat/catAuth/availableCat";
    }

    /**
     * 控制分类按区域显示
     * @return
     */
    @RequestMapping("/catAuth/regionCat")
    public String regionCat() {
        return "business/productCat/catAuth/regionCat-ztree";
    }

    /**
     * 修改分类显示状态
     * @param request
     * @param categoryId 分类Id
     * @param flag 状态码。0代表不在微信端显示，1代表显示
     * @return
     */
    @RequestMapping("/catAuth/updateVisible")
    @ResponseBody
    public EasyUIResult updateVisible(HttpServletRequest request, Long categoryId, Integer flag) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        String platformId = userResult.getPluteformid();
        if (userResult.getShopId() != null) {
            platformId = userResult.getParentPluteformId();
        }
        try {
            productCategoryService.updateVisible(userResult.getShopId(), platformId, categoryId, flag);
            return new EasyUIResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIResult(e.getMessage());
        }
    }

    /**
     * 添加地区分类
     * @return
     */
    @RequestMapping("/catAuth/addRegionCat")
    @ResponseBody
    public EasyUIResult addRegionCat(HttpSession session, Long regionId, Long[] indeterminateIds, Long[] checkedIds) {
        UserResult userResult = (UserResult) session.getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        String platformId = userResult.getPluteformid();
        if (userResult.getShopId() != null) {
            platformId = userResult.getParentPluteformId();
        }
        try {
            productCategoryService.addRegionCat(userResult.getShopId(), platformId, regionId, indeterminateIds, checkedIds);
            return new EasyUIResult();
        } catch (Exception e) {
            return new EasyUIResult(e.getMessage());
        }
    }

    /**
     * OA区域分类EasyUI回显
     * @param request
     * @param regionId
     * @return
     */
    /*@RequestMapping("/getProdCatListByRegionId")
    @ResponseBody
    public List<EasyUICatTreeNode> getProdCatListByRegionId(HttpServletRequest request, Long regionId) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        String platformId = userResult.getPluteformid();
        if (userResult.getShopId() != null) {
            platformId = userResult.getParentPluteformId();
        }
        try {
            return productCategoryService.getProdCatListByRegionId(userResult.getShopId(), platformId, regionId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/

    /**
     * OA区域分类ZTree回显
     * @param request
     * @param regionId
     * @return
     */
    @RequestMapping("/getZTreeCatListByRegionId")
    @ResponseBody
    public List<ZTreeNode> getZTreeCatListByRegionId(HttpServletRequest request, Long regionId) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        String platformId = userResult.getPluteformid();
        if (userResult.getShopId() != null) {
            platformId = userResult.getParentPluteformId();
        }
        try {
            return productCategoryService.getZTreeCatListByRegionId(userResult.getShopId(), platformId, regionId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 微信按地区查询产品分类接口
     * @return
     */
    @RequestMapping(value="/getProdCategoryByRegionIdForWeChat")
    @ResponseBody
    public List<CategoryInfoResult> getProdCategoryByRegionIdForWeChat(@RequestBody String param, RegionCatDto regionCat) {
        RegionCatDto regionCatDto = JSONObject.parseObject(param, RegionCatDto.class);
        return productCategoryService.getProdCategoryByRegionIdForWeChat(
                regionCatDto.getShopId(), regionCatDto.getPluteformid(),
                regionCatDto.getRegionId(), regionCatDto.getParentCategoryId());
    }

    /*-----------------------分类按区域显示控制 End-----------------------*/


    /*---------------------页面跳转-----------------------*/
    @RequestMapping("/prodCatListPage")
    public String prodCatListPage() {
        return "business/productCat/prodCatList";
    }

    @RequestMapping("/addProdCatDlg")
    public String addProdCatDlg() {
        return "business/productCat/addProdCatDlg";
    }

    @RequestMapping("/updateProdCatDlg")
    public String updateProdCatDlg(HttpServletRequest request, Model model, Long categoryId) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        String platformId = userResult.getPluteformid();
        if (userResult.getShopId() != null) {
            platformId = userResult.getParentPluteformId();
        }
        CategoryInfo category = productCategoryService.getProdCatById(categoryId, platformId, userResult.getShopId());

        model.addAttribute("category", category);

        return "business/productCat/updateProdCatDlg";
    }

    @RequestMapping("/prodTypeListPage")
    public String prodTypeListPage() {
        return "business/productCat/prodTypeList";
    }

    @RequestMapping("/addProdTypeDlg")
    public String addProdTypeDlg() {
        return "business/productCat/addProdTypeDlg";
    }

    @RequestMapping("/addProdTypeAttrDlg")
    public String addProdTypeAttrDlg(Model model, Long typeId) {
        model.addAttribute("typeId", typeId);
        return "business/productCat/addProdTypeAttrDlg";
    }

    @RequestMapping("/addProdTypeSpecDlg")
    public String addProdTypeSpecDlg(Model model, Long typeId) {
        model.addAttribute("typeId", typeId);
        return "business/productCat/addProdTypeSpecDlg";
    }

    @RequestMapping("/updateProdTypeDlg")
    public String updateProdTypeDlg(HttpServletRequest request, Model model, Long typeId) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        String platformId = userResult.getPluteformid();
        if (userResult.getShopId() != null) {
            platformId = userResult.getParentPluteformId();
        }
        ProductType prodType = productCategoryService.getProdTypeById(typeId, platformId, userResult.getShopId());
        model.addAttribute("prodType", prodType);
        return "business/productCat/updateProdTypeDlg";
    }

    @RequestMapping("/addProdTypeCharSpecDlg")
    public String addProdTypeCharSpecDlg(Model model, Long typeId) {
        model.addAttribute("typeId", typeId);
        return "business/productCat/addProdTypeCharSpecDlg";
    }

    @RequestMapping("/addProdTypePicSpecDlg")
    public String addProdTypePicSpecDlg(Model model, Long typeId) {
        model.addAttribute("typeId", typeId);
        return "business/productCat/addProdTypePicSpecDlg";
    }

    @RequestMapping("/addSpecPicDlg")
    public String addSpecPicDlg(Model model, Long attributeId) {
        model.addAttribute("attributeId", attributeId);
        return "business/productCat/addSpecPicDlg";
    }

    @RequestMapping("/addSpecCharDlg")
    public String addSpecCharDlg(Model model, Long attributeId) {
        model.addAttribute("attributeId", attributeId);
        return "business/productCat/addSpecCharDlg";
    }

    @RequestMapping("/updateProdTypeSpecDlg")
    public String updateProdTypeSpecDlg(HttpServletRequest request, Model model, Long attributeId) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        String platformId = userResult.getPluteformid();
        if (userResult.getShopId() != null) {
            platformId = userResult.getParentPluteformId();
        }
        AttributeInfo attributeInfo = productCategoryService.getProdTypeAttrById(attributeId, platformId, userResult.getShopId());
        model.addAttribute("attributeInfo", attributeInfo);

        if (attributeInfo.getValueStr() != null && attributeInfo.getValueStr().size() != 0) {
            StringBuilder builder = new StringBuilder();
            for (String value : attributeInfo.getValueStr()) {
                builder.append(value);
                builder.append(",");
            }
            builder.deleteCharAt(builder.length()-1);
            model.addAttribute("valueStr", builder.toString());
        } else {
            model.addAttribute("valueStr", "");
        }

        return "business/productCat/updateProdTypeSpecDlg";
    }

    @RequestMapping("/updateProdTypeAttrDlg")
    public String updateProdTypeAttrDlg(HttpServletRequest request, Model model, Long attributeId) {
        UserResult userResult = (UserResult) request.getSession().getAttribute("userResult");
        if (userResult == null) {
            return null;
        }
        String platformId = userResult.getPluteformid();
        if (userResult.getShopId() != null) {
            platformId = userResult.getParentPluteformId();
        }
        EasyUIAttributeInfo attributeInfo = productCategoryService.getProdTypeAttrById(attributeId, platformId, userResult.getShopId());
        model.addAttribute("attributeInfo", attributeInfo);

        if (attributeInfo.getValueStr() != null && attributeInfo.getValueStr().size() != 0) {
            StringBuilder builder = new StringBuilder();
            for (String value : attributeInfo.getValueStr()) {
                builder.append(value);
                builder.append(",");
            }
            builder.deleteCharAt(builder.length()-1);
            model.addAttribute("valueStr", builder.toString());
        } else {
            model.addAttribute("valueStr", "");
        }

        return "business/productCat/updateProdTypeAttrDlg";
    }

    @RequestMapping("/addProdTypeInputAttrDlg")
    public String addProdTypeInputAttrDlg(Model model, Long typeId, Integer usageMode) {
        model.addAttribute("typeId", typeId);
        model.addAttribute("usageMode", usageMode);
        return "business/productCat/addProdTypeInputAttrDlg";
    }


    /**
     * 二维码获取接口
     * @param response
     * @param content 二维码内容。例：http://www.zhongguozhaomei.com/
     * @param width 宽度（像素）。例：100
     * @param height 高度（像素）。例：100
     * @param format 图像格式。例：png
     */
    @RequestMapping("/getQRCodeImg")
    public void addProdTypeInputAttrDlg(HttpServletResponse response, String content, Integer width, Integer height, String format) {
        if (content == null) {
            try {
                response.getWriter().write("parameter 'content' is required");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (width == null) {
            width = 200;
        }
        if (height == null) {
            height = 200;
        }
        if (format == null) {
            format = "png";
        }
        try {
            qrCodeUtils.generateQRCode(content, width, height, format, response.getOutputStream());
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
