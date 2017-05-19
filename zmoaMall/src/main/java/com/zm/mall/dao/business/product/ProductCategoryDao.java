package com.zm.mall.dao.business.product;

import com.zm.mall.client.result.business.product.CategoryInfoResult;
import com.zm.mall.domain.business.product.*;

import java.util.List;

/**
 * Created by Bochao on 2017/3/22.
 */
public interface ProductCategoryDao {
    /**
     * 分页-查询产品分类
     * @param paginationCategoryInfo
     * @return
     */
    List<CategoryInfo> getPageProdCatList(PaginationCategoryInfo paginationCategoryInfo);

    /**
     * 分页-查询产品分类总数
     * @param paginationCategoryInfo
     * @return
     */
    Long getPageProdCatCount(PaginationCategoryInfo paginationCategoryInfo);

    /**
     * 添加产品分类
     * @param categoryInfo
     * @return
     */
    int addProdCat(CategoryInfo categoryInfo);

    /**
     * 修改产品分类
     * @param categoryInfo
     * @return
     */
    int updateProdCat(CategoryInfo categoryInfo);

    /**
     * 修改产品分类状态
     * @param categoryInfo
     * @return
     */
    int updateProdCatStatus(CategoryInfo categoryInfo);

    /**
     * 获取产品分类列表
     * @param categoryInfo
     * @return
     */
    List<CategoryInfo> getProdCatList(CategoryInfo categoryInfo);


    List<CategoryInfo> getPageProdCatListByParentId(PaginationCategoryInfo paginationCategoryInfo);

    Long getTopProdCatCount(PaginationCategoryInfo paginationCategoryInfo);

    int updateProdCatStatusByColumnName(Long shopId, String platformId, Integer id, String columnName, int value);

    List<ProductType> getProdTypes(PaginationProductType productType);

    Long getProdTypeCount(PaginationProductType productType);

    /**
     * 获取平台下所有商品品牌
     * @param platformId
     * @return
     */
    List<BrandInfo> getProdBrandsByPlatformId(String platformId, Long shopId);

    /**
     * 添加商品类型
     * @param productType
     * @return
     */
    int addProdType(ProductType productType);

    /**
     * 添加商品类型管理品牌
     * @param productType
     * @param brandId
     * @return
     */
    int addProdTypeBrand(ProductType productType, Long brandId);

    /**
     * 分页获取商品类型属性
     * @param attributeInfo
     * @return
     */
    List<AttributeInfo> getProdTypeAttrsByTypeId(PaginationAttributeInfo attributeInfo);

    /**
     * 分页获取商品类型属性
     * @param attributeInfo
     * @return
     */
    Long getProdTypeAttrCount(PaginationAttributeInfo attributeInfo);

    List<String> getAttributeValuesById(Long attributeId, String platformId, Long shopId);

    /**
     * 添加商品类型属性
     * @param attributeInfo
     * @return
     */
    int addProdTypeAttr(AttributeInfo attributeInfo);

    /**
     * 添加商品类型属性值
     * @param attributeId
     * @param value
     * @param pluteformid
     * @return
     */
    int addProdTypeAttrValue(Long attributeId, String value, String platformId, Long shopId);

    /**
     * 更新商品类型属性
     * @param attributeInfo
     * @return
     */
    int updateProdTypeAttr(AttributeInfo attributeInfo);

    /**
     * 根据商品类型属性删除属性值
     * @param attributeInfo
     * @return
     */
    int deleteProdTypeAttrValuesById(AttributeInfo attributeInfo);


    List<AttributeInfo> getProdTypeSpecsByTypeId(PaginationAttributeInfo attributeInfo);

    Long getProdTypeSpecCount(PaginationAttributeInfo attributeInfo);

    List<String> getSpecValuesById(Long attributeId, String platformId, Long shopId);

    int addSpecValue(AttributeValue attributeValue);

    ProductType getProdTypeById(Long typeId, String platformId, Long shopId);

    List<Long> getTypeBrandIdsByTypeId(Long typeId, String platformId, Long shopId);

    AttributeInfo getProdTypeAttrById(Long attributeId, String platformId, Long shopId);

    int updateProdType(ProductType productType);

    int deleteProdTypeBrand(ProductType productType);


    CategoryInfo getProdCatById(Long categoryId, String platformId, Long shopId);

    int updateProdCatStatusById(Long categoryId, Boolean status, String platformId, Long shopId);

    List<Long> getChildCatIdsByParentId(Long categoryId, String platformId, Long shopId);

    int updateVisible(Long shopId, String platformId, Long categoryId, Integer flag);


    int addRegionCat(Long shopId, String platformId, Long regionId, Long[] catIds);

    List<Long> getProdCatIdsByRegionId(Long shopId, String platformId, Long regionId);

    List<EasyUICatTreeNode> getChildCategoriesByParentId(String platformId, Long categoryId);

    int deleteRegionCatByRegionId(Long shopId, String platformId, Long regionId);

    List<ZTreeNode> getZTreeCatList(Long shopId, String platformId);

    List<CategoryInfoResult> getProdCategoryByRegionIdForWeChat(Long shopId, String platformId, Long regionId, Long parentCategoryId);

    List<BrandInfo> getTypeBrandList(PaginationBrandInfo brandInfo);

    Long getTypeBrandCount(PaginationBrandInfo brandInfo);

    int addTypeBrand(BrandInfo brandInfo);


    int updateTypeBrand(BrandInfo brandInfo);

    int deleteTypeBrand(BrandInfo brandInfo);

    List<ProductType> getProdTypeList(String platformId, Long shopId);


    int addBrandType(BrandInfo brandInfo);

    int deleteBrandType(BrandInfo brandInfo);

    BrandInfo getTypeBrand(BrandInfo brandInfo);


    List<Integer> getBrandProdTypeIds(BrandInfo brandInfo);

    List<CategoryInfoResult> getProdCategoryForWeChat(Long shopId, String platformId, Long parentCategoryId);

}
