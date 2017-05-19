package com.zm.mall.client.service.business.product;

import com.zm.mall.client.result.business.product.CategoryInfoResult;
import com.zm.mall.domain.business.product.*;
import com.zm.mall.domain.system.coupon.EasyUIListResult;

import java.util.List;

/**
 * Created by Bochao on 2017/3/22.
 */
public interface ProductCategoryService {

    /**
     * 分页查询产品分类
     * @param paginationCategoryInfo
     * @return
     */
    EasyUIListResult getPageProdCatList(PaginationCategoryInfo paginationCategoryInfo);

    /**
     * 添加产品分类
     * @param categoryInfo
     */
    void addProdCat(CategoryInfo categoryInfo);

    /**
     * 更新产品分类
     * @param categoryInfo
     */
    void updateProdCat(CategoryInfo categoryInfo);

    /**
     * 更新产品分类状态
     * @param categoryInfo
     */
    void updateProdCatStatus(CategoryInfo categoryInfo);

    /**
     * 获取产品分类列表
     * @param categoryInfo
     * @return
     */
    List<CategoryInfo> getProdCatList(CategoryInfo categoryInfo);

    /**
     * 分页查询产品分类，EasyUI树形展现，异步加载子类型
     * @param paginationCategoryInfo
     * @return
     */
    List<EasyUITreeCatInfo> getPageProdCatListByParentId(PaginationCategoryInfo paginationCategoryInfo);

    /**
     * 分页获取所有产品类型
     * @param productType
     * @return
     */
    EasyUIListResult getProdTypes(PaginationProductType productType);

    List<BrandInfo> getProdBrandsByPlatformId(String platformId, Long shopId);

    /**
     * 添加商品分类
     * @param productType
     * @param brands
     */
    void addProdType(ProductType productType, Long[] brands);

    /**
     * 分页获取商品类型属性
     * @param attributeInfo
     * @return
     */
    EasyUIListResult getProdTypeAttrsByTypeId(PaginationAttributeInfo attributeInfo);

    /**
     * 添加商品类型属性
     * @param attributeInfo
     */
    void addProdTypeAttr(AttributeInfo attributeInfo);

    /**
     * 更新商品类型属性
     * @param attributeInfo
     */
    void updateProdTypeAttr(AttributeInfo attributeInfo);

    EasyUIListResult getProdTypeSpecsByTypeId(PaginationAttributeInfo attributeInfo);

    void addSpecValue(AttributeValue attributeValue);

    ProductType getProdTypeById(Long typeId, String platformId, Long shopId);

    List<Long> getTypeBrandIdsByTypeId(Long typeId, String platformId, Long shopId);

    EasyUIAttributeInfo getProdTypeAttrById(Long attributeId, String platformId, Long shopId);

    void updateProdType(ProductType productType, Long[] brandIds);

    CategoryInfo getProdCatById(Long categoryId, String platformId, Long shopId);

    void updateVisible(Long shopId, String platformId, Long categoryId, Integer flag);

    void addRegionCat(Long shopId, String platformId, Long regionId, Long[] indeterminateIds, Long[] checkedIds);

    List<EasyUICatTreeNode> getProdCatListByRegionId(Long shopId, String platformId, Long regionId);

    List<ZTreeNode> getZTreeCatListByRegionId(Long shopId, String platformId, Long regionId);

    List<CategoryInfoResult> getProdCategoryByRegionIdForWeChat(Long shopId, String platformId, Long regionId, Long parentCategoryId);

    EasyUIListResult getTypeBrandList(PaginationBrandInfo brandInfo);

    void addTypeBrand(BrandInfo brandInfo);

    void updateTypeBrand(BrandInfo brandInfo);

    void deleteTypeBrand(BrandInfo brandInfo);

    List<ProductType> getProdTypeList(String platformId, Long shopId);

    BrandInfo getTypeBrand(BrandInfo brandInfo);
}
