package com.zm.mall.dao.business.product.impl;

import com.zm.mall.client.result.business.product.CategoryInfoResult;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.ProductCategoryDao;
import com.zm.mall.domain.business.product.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bochao on 2017/3/22.
 */


public class ProductCategoryDaoImpl extends BaseDaoImpl implements ProductCategoryDao {

    private final static String NAMESPACE = "com.zm.mall.dao.business.product.ProductCategoryDao.";

    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

    @Override
    public List<CategoryInfo> getPageProdCatList(PaginationCategoryInfo paginationCategoryInfo) {
        return sqlTemplate.selectList(getNameSpace("getPageProdCatList"), paginationCategoryInfo);
    }

    @Override
    public Long getPageProdCatCount(PaginationCategoryInfo paginationCategoryInfo) {
        return sqlTemplate.selectOne(getNameSpace("getPageProdCatCount"), paginationCategoryInfo);
    }

    @Override
    public int addProdCat(CategoryInfo categoryInfo) {
        return sqlTemplate.insert(getNameSpace("addProdCat"), categoryInfo);
    }

    @Override
    public int updateProdCat(CategoryInfo categoryInfo) {
        return sqlTemplate.update(getNameSpace("updateProdCat"), categoryInfo);
    }

    @Override
    public int updateProdCatStatus(CategoryInfo categoryInfo) {
        return sqlTemplate.update(getNameSpace("updateProdCatStatus"), categoryInfo);
    }

    @Override
    public List<CategoryInfo> getProdCatList(CategoryInfo categoryInfo) {
        return sqlTemplate.selectList(getNameSpace("getProdCatList"), categoryInfo);
    }

    @Override
    public List<CategoryInfo> getPageProdCatListByParentId(PaginationCategoryInfo paginationCategoryInfo) {
        return sqlTemplate.selectList(getNameSpace("getPageProdCatListByParentId"), paginationCategoryInfo);
    }

    @Override
    public Long getTopProdCatCount(PaginationCategoryInfo paginationCategoryInfo) {
        return sqlTemplate.selectOne(getNameSpace("getTopProdCatCount"), paginationCategoryInfo);
    }

    @Override
    public int updateProdCatStatusByColumnName(Long shopId, String platformId, Integer id, String columnName, int value) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        map.put("id", id);
        map.put("columnName", columnName);
        map.put("value", value);
        return sqlTemplate.update(getNameSpace("updateProdCatStatusByColumnName"), map);
    }

    @Override
    public List<ProductType> getProdTypes(PaginationProductType productType) {
        return sqlTemplate.selectList(getNameSpace("getProdTypes"), productType);
    }

    @Override
    public Long getProdTypeCount(PaginationProductType productType) {
        return sqlTemplate.selectOne(getNameSpace("getProdTypeCount"), productType);
    }

    @Override
    public List<BrandInfo> getProdBrandsByPlatformId(String platformId, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("shopId", shopId);
        return sqlTemplate.selectList(getNameSpace("getProdBrandsByPlatformId"), map);
    }

    @Override
    public int addProdType(ProductType productType) {
        return sqlTemplate.insert(getNameSpace("addProdType"), productType);
    }

    @Override
    public int addProdTypeBrand(ProductType productType, Long brandId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productType", productType);
        map.put("brandId", brandId);
        return sqlTemplate.insert(getNameSpace("addProdTypeBrand"), map);
    }

    @Override
    public List<AttributeInfo> getProdTypeAttrsByTypeId(PaginationAttributeInfo attributeInfo) {
        List<AttributeInfo> attributeInfoList = sqlTemplate.selectList(getNameSpace("getProdTypeAttrsByTypeId"), attributeInfo);
        for (AttributeInfo info : attributeInfoList) {
            List<String> values = getAttributeValuesById(info.getAttributeId(), info.getPluteformid(), info.getShopId());
            info.setValueStr(values);
        }
        return attributeInfoList;
    }

    @Override
    public Long getProdTypeAttrCount(PaginationAttributeInfo attributeInfo) {
        return sqlTemplate.selectOne(getNameSpace("getProdTypeAttrCount"), attributeInfo);
    }

    @Override
    public List<String> getAttributeValuesById(Long attributeId, String platformId, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("attributeId", attributeId);
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        return sqlTemplate.selectList(getNameSpace("getAttributeValuesById"), map);
    }

    @Override
    public int addProdTypeAttr(AttributeInfo attributeInfo) {
        return sqlTemplate.insert(getNameSpace("addProdTypeAttr"), attributeInfo);
    }

    @Override
    public int addProdTypeAttrValue(Long attributeId, String value, String platformId, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("attributeId", attributeId);
        map.put("value", value);
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        return sqlTemplate.insert(getNameSpace("addProdTypeAttrValue"), map);
    }

    @Override
    public int updateProdTypeAttr(AttributeInfo attributeInfo) {
        return sqlTemplate.update(getNameSpace("updateProdTypeAttr"), attributeInfo);
    }

    @Override
    public int deleteProdTypeAttrValuesById(AttributeInfo attributeInfo) {
        return sqlTemplate.delete(getNameSpace("deleteProdTypeAttrValuesById"), attributeInfo);
    }

    @Override
    public List<AttributeInfo> getProdTypeSpecsByTypeId(PaginationAttributeInfo attributeInfo) {
        List<AttributeInfo> attributeInfoList = sqlTemplate.selectList(getNameSpace("getProdTypeSpecsByTypeId"), attributeInfo);
        for (AttributeInfo info : attributeInfoList) {
            List<String> values = getSpecValuesById(info.getAttributeId(), info.getPluteformid(), info.getShopId());
            info.setValueStr(values);
        }
        return attributeInfoList;
    }

    @Override
    public Long getProdTypeSpecCount(PaginationAttributeInfo attributeInfo) {
        return sqlTemplate.selectOne(getNameSpace("getProdTypeSpecCount"), attributeInfo);
    }

    @Override
    public List<String> getSpecValuesById(Long attributeId, String platformId, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("attributeId", attributeId);
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        return sqlTemplate.selectList(getNameSpace("getSpecValuesById"), map);
    }

    @Override
    public int addSpecValue(AttributeValue attributeValue) {
        return sqlTemplate.insert(getNameSpace("addSpecValue"), attributeValue);
    }

    @Override
    public ProductType getProdTypeById(Long typeId, String platformId, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("typeId", typeId);
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        return sqlTemplate.selectOne(getNameSpace("getProdTypeById"), map);
    }

    @Override
    public List<Long> getTypeBrandIdsByTypeId(Long typeId, String platformId, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("typeId", typeId);
        map.put("platformId", platformId);
        map.put("shopId", shopId);
        List<Long> list = sqlTemplate.selectList(getNameSpace("getTypeBrandIdsByTypeId"), map);
        return list;
    }

    @Override
    public AttributeInfo getProdTypeAttrById(Long attributeId, String platformId, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("attributeId", attributeId);
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        AttributeInfo attributeInfo = sqlTemplate.selectOne(getNameSpace("getProdTypeAttrById"), map);
        List<String> values = getAttributeValuesById(attributeId, platformId, shopId);
        if (attributeId != null)
        attributeInfo.setValueStr(values);
        return attributeInfo;
    }

    @Override
    public int updateProdType(ProductType productType) {
        return sqlTemplate.update(getNameSpace("updateProdType"), productType);
    }

    @Override
    public int deleteProdTypeBrand(ProductType productType) {
        return sqlTemplate.delete(getNameSpace("deleteProdTypeBrand"), productType);
    }

    @Override
    public CategoryInfo getProdCatById(Long categoryId, String platformId, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categoryId", categoryId);
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        return sqlTemplate.selectOne(getNameSpace("getProdCatById"), map);
    }

    @Override
    public int updateProdCatStatusById(Long categoryId, Boolean status, String platformId, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categoryId", categoryId);
        map.put("status", status);
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        return sqlTemplate.update(getNameSpace("updateProdCatStatusById"), map);
    }

    @Override
    public List<Long> getChildCatIdsByParentId(Long categoryId, String platformId, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("categoryId", categoryId);
        map.put("shopId", shopId);
        return sqlTemplate.selectList(getNameSpace("getChildCatIdsByParentId"), map);
    }

    @Override
    public int updateVisible(Long shopId, String platformId, Long categoryId, Integer flag) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        map.put("categoryId", categoryId);
        map.put("flag", flag);
        return sqlTemplate.update(getNameSpace("updateVisible"), map);
    }

    @Override
    public int addRegionCat(Long shopId, String platformId, Long regionId, Long[] catIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        map.put("regionId", regionId);
        map.put("catIds", catIds);
        return sqlTemplate.insert(getNameSpace("addRegionCat"), map);
    }

    @Override
    public List<Long> getProdCatIdsByRegionId(Long shopId, String platformId, Long regionId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        map.put("regionId", regionId);
        return sqlTemplate.selectList(getNameSpace("getProdCatIdsByRegionId"), map);
    }

    @Override
    public List<EasyUICatTreeNode> getChildCategoriesByParentId(String platformId, Long categoryId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("categoryId", categoryId);
        return sqlTemplate.selectList(getNameSpace("getChildCategoriesByParentId"), map);
    }

    @Override
    public int deleteRegionCatByRegionId(Long shopId, String platformId, Long regionId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        map.put("regionId", regionId);
        return sqlTemplate.delete(getNameSpace("deleteRegionCatByRegionId"), map);
    }

    @Override
    public List<ZTreeNode> getZTreeCatList(Long shopId, String platformId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        return sqlTemplate.selectList(getNameSpace("getZTreeCatList"), map);
    }

    @Override
    public List<CategoryInfoResult> getProdCategoryByRegionIdForWeChat(Long shopId, String platformId, Long regionId, Long parentCategoryId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        map.put("regionId", regionId);
        map.put("parentCategoryId", parentCategoryId);
        return sqlTemplate.selectList(getNameSpace("getProdCategoryByRegionIdForWeChat"), map);
    }

    @Override
    public List<BrandInfo> getTypeBrandList(PaginationBrandInfo brandInfo) {
        return sqlTemplate.selectList(getNameSpace("getTypeBrandList"), brandInfo);
    }

    @Override
    public Long getTypeBrandCount(PaginationBrandInfo brandInfo) {
        return sqlTemplate.selectOne(getNameSpace("getTypeBrandCount"), brandInfo);
    }

    @Override
    public int addTypeBrand(BrandInfo brandInfo) {
        return sqlTemplate.insert(getNameSpace("addTypeBrand"), brandInfo);
    }

    @Override
    public int updateTypeBrand(BrandInfo brandInfo) {
        return sqlTemplate.update(getNameSpace("updateTypeBrand"), brandInfo);
    }

    @Override
    public int deleteTypeBrand(BrandInfo brandInfo) {
        return sqlTemplate.delete(getNameSpace("deleteTypeBrand"), brandInfo);
    }

    @Override
    public List<ProductType> getProdTypeList(String platformId, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("shopId", shopId);
        return sqlTemplate.selectList(getNameSpace("getProdTypeList"), map);
    }

    @Override
    public int addBrandType(BrandInfo brandInfo) {
        return sqlTemplate.insert(getNameSpace("addBrandType"), brandInfo);
    }

    @Override
    public int deleteBrandType(BrandInfo brandInfo) {
        return sqlTemplate.delete(getNameSpace("deleteBrandType"), brandInfo);
    }

    @Override
    public BrandInfo getTypeBrand(BrandInfo brandInfo) {
        return sqlTemplate.selectOne(getNameSpace("getTypeBrand"), brandInfo);
    }

    @Override
    public List<Integer> getBrandProdTypeIds(BrandInfo brandInfo) {
        return sqlTemplate.selectList(getNameSpace("getBrandProdTypeIds"), brandInfo);
    }

    @Override
    public List<CategoryInfoResult> getProdCategoryForWeChat(Long shopId, String platformId, Long parentCategoryId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        map.put("parentCategoryId", parentCategoryId);
        return sqlTemplate.selectList(getNameSpace("getProdCategoryForWeChat"), map);
    }
}
