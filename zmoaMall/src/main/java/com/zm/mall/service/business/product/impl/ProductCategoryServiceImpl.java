package com.zm.mall.service.business.product.impl;

import com.zm.mall.client.result.business.product.CategoryInfoResult;
import com.zm.mall.client.service.business.product.ProductCategoryService;
import com.zm.mall.client.service.system.CouponService;
import com.zm.mall.dao.business.product.ProductCategoryDao;
import com.zm.mall.domain.business.product.*;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * Created by Bochao on 2017/3/22.
 */

@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Resource
    private ProductCategoryDao productCategoryDao;
    @Resource
    private CouponService couponService;

    @Override
    public EasyUIListResult getPageProdCatList(PaginationCategoryInfo paginationCategoryInfo) {
        List<CategoryInfo> categoryInfoList = productCategoryDao.getPageProdCatList(paginationCategoryInfo);
        Long total = productCategoryDao.getPageProdCatCount(paginationCategoryInfo);
        return new EasyUIListResult(total, categoryInfoList);
    }

    @Transactional
    @Override
    public void addProdCat(CategoryInfo categoryInfo) {
        List<Long> pIds = couponService.getAncestorCategoryIds(categoryInfo.getShopId(), categoryInfo.getPluteformid(),
                Long.valueOf(categoryInfo.getParentCategoryId()));

        if (pIds != null && pIds.size() != 0) {
            pIds.remove(pIds.size()-1);
            Collections.reverse(pIds);
            StringBuilder builder = new StringBuilder();
            for (Long pId : pIds) {
                builder.append(pId);
                builder.append("|");
            }
            builder.append(categoryInfo.getParentCategoryId());
            categoryInfo.setPath(builder.toString());
        } else {
            categoryInfo.setPath("");
        }


        int i = productCategoryDao.addProdCat(categoryInfo);
        if (i == 0) throw new RuntimeException("添加失败");

        if (categoryInfo.getParentCategoryId() != 0) {
            int j = productCategoryDao.updateProdCatStatusByColumnName(
                    categoryInfo.getShopId(), categoryInfo.getPluteformid(), categoryInfo.getParentCategoryId(), "HasChildren", 1);
            if (j == 0) throw new RuntimeException("添加失败");
        }

    }

    @Transactional
    @Override
    public void updateProdCat(CategoryInfo categoryInfo) {
        int i = productCategoryDao.updateProdCat(categoryInfo);
        if (i == 0) throw new RuntimeException("修改失败");

        //深度优先遍历
        Stack<Long> nodeDeque = new Stack<Long>();
        Long node = categoryInfo.getCategoryId().longValue(); //分类Id
        nodeDeque.push(node);
        while (!nodeDeque.isEmpty()) {
            node = nodeDeque.pop();
            productCategoryDao.updateProdCatStatusById(node, categoryInfo.getStatus(), categoryInfo.getPluteformid(), categoryInfo.getShopId());
            List<Long> children = productCategoryDao.getChildCatIdsByParentId(node, categoryInfo.getPluteformid(), categoryInfo.getShopId());
            if (children != null && !children.isEmpty()) {
                for (Long child : children) {
                    nodeDeque.push(child);
                }
            }
        }

    }

    @Override
    public void updateProdCatStatus(CategoryInfo categoryInfo) {
        int i = productCategoryDao.updateProdCatStatus(categoryInfo);
        if (i == 0) throw new RuntimeException("修改失败");
    }

    @Override
    public List<CategoryInfo> getProdCatList(CategoryInfo categoryInfo) {
        return productCategoryDao.getProdCatList(categoryInfo);
    }

    @Override
    public List<EasyUITreeCatInfo> getPageProdCatListByParentId(PaginationCategoryInfo paginationCategoryInfo) {
        List<CategoryInfo> categoryInfoList = productCategoryDao.getPageProdCatListByParentId(paginationCategoryInfo);
        List<EasyUITreeCatInfo> easyUICategoryInfoList = new ArrayList<EasyUITreeCatInfo>();

        for (CategoryInfo categoryInfo : categoryInfoList) {
            EasyUITreeCatInfo easyUITreeCatInfo = new EasyUITreeCatInfo();
            BeanUtils.copyProperties(categoryInfo, easyUITreeCatInfo);
            if (easyUITreeCatInfo.getHasChildren()) {
                easyUITreeCatInfo.setState("closed");
            }
            easyUICategoryInfoList.add(easyUITreeCatInfo);
        }

        return easyUICategoryInfoList;
    }

    @Override
    public EasyUIListResult getProdTypes(PaginationProductType productType) {
        List<ProductType> productTypes = productCategoryDao.getProdTypes(productType);
        Long total = productCategoryDao.getProdTypeCount(productType);
        return new EasyUIListResult<ProductType>(total, productTypes);
    }

    @Override
    public List<BrandInfo> getProdBrandsByPlatformId(String platformId, Long shopId) {
        return productCategoryDao.getProdBrandsByPlatformId(platformId, shopId);
    }

    @Transactional
    @Override
    public void addProdType(ProductType productType, Long[] brandIds) {
        int i = productCategoryDao.addProdType(productType);
        if (i == 0) {
            throw new RuntimeException("商品类型添加失败");
        }
        if (brandIds != null)
        for (Long brandId : brandIds) {
            int j = productCategoryDao.addProdTypeBrand(productType, brandId);
            if (j == 0) {
                throw new RuntimeException("商品类型品牌添加失败");
            }
        }
    }

    @Override
    public EasyUIListResult getProdTypeAttrsByTypeId(PaginationAttributeInfo attributeInfo) {
        List<AttributeInfo> attributeInfoList = productCategoryDao.getProdTypeAttrsByTypeId(attributeInfo);
        List<EasyUIAttributeInfo> easyUIAttributeInfoList = new ArrayList<EasyUIAttributeInfo>();
        for (AttributeInfo info : attributeInfoList) {
            EasyUIAttributeInfo easyUIAttributeInfo = new EasyUIAttributeInfo();
            BeanUtils.copyProperties(info, easyUIAttributeInfo);
            easyUIAttributeInfoList.add(easyUIAttributeInfo);
        }
        Long total = productCategoryDao.getProdTypeAttrCount(attributeInfo);
        return new EasyUIListResult<EasyUIAttributeInfo>(total, easyUIAttributeInfoList);
    }

    @Transactional
    @Override
    public void addProdTypeAttr(AttributeInfo attributeInfo) {
        int i = productCategoryDao.addProdTypeAttr(attributeInfo);
        if (i == 0) {
            throw new RuntimeException("添加商品类型属性失败");
        }
        for (String value : attributeInfo.getValueStr()) {
            int j = productCategoryDao.addProdTypeAttrValue(attributeInfo.getAttributeId(), value, attributeInfo.getPluteformid(), attributeInfo.getShopId());
            if (j == 0) {
                throw new RuntimeException("添加商品类型属性值失败");
            }
        }
    }

    @Transactional
    @Override
    public void updateProdTypeAttr(AttributeInfo attributeInfo) {
        int i = productCategoryDao.updateProdTypeAttr(attributeInfo);
        int j = productCategoryDao.deleteProdTypeAttrValuesById(attributeInfo);
        for (String value : attributeInfo.getValueStr()) {
            int k = productCategoryDao.addProdTypeAttrValue(attributeInfo.getAttributeId(), value, attributeInfo.getPluteformid(), attributeInfo.getShopId());
            if (k == 0){
                throw new RuntimeException("添加商品类型属性值失败");
            }
        }
    }

    @Override
    public EasyUIListResult getProdTypeSpecsByTypeId(PaginationAttributeInfo attributeInfo) {
        List<AttributeInfo> attributeInfoList = productCategoryDao.getProdTypeSpecsByTypeId(attributeInfo);
        List<EasyUIAttributeInfo> easyUIAttributeInfoList = new ArrayList<EasyUIAttributeInfo>();
        for (AttributeInfo info : attributeInfoList) {
            EasyUIAttributeInfo easyUIAttributeInfo = new EasyUIAttributeInfo();
            BeanUtils.copyProperties(info, easyUIAttributeInfo);
            easyUIAttributeInfo.setUseAttributeImage(info.isUseAttributeImage());
            easyUIAttributeInfoList.add(easyUIAttributeInfo);
        }
        Long total = productCategoryDao.getProdTypeSpecCount(attributeInfo);
        return new EasyUIListResult<EasyUIAttributeInfo>(total, easyUIAttributeInfoList);
    }

    @Override
    public void addSpecValue(AttributeValue attributeValue) {
        int i = productCategoryDao.addSpecValue(attributeValue);
    }

    @Override
    public ProductType getProdTypeById(Long typeId, String platformId, Long shopId) {
        return productCategoryDao.getProdTypeById(typeId, platformId, shopId);
    }

    @Override
    public List<Long> getTypeBrandIdsByTypeId(Long typeId, String platformId, Long shopId) {
        return productCategoryDao.getTypeBrandIdsByTypeId(typeId, platformId, shopId);
    }

    @Override
    public EasyUIAttributeInfo getProdTypeAttrById(Long attributeId, String platformId, Long shopId) {
        AttributeInfo attributeInfo = productCategoryDao.getProdTypeAttrById(attributeId, platformId, shopId);
        EasyUIAttributeInfo easyUIAttributeInfo = new EasyUIAttributeInfo();
        BeanUtils.copyProperties(attributeInfo, easyUIAttributeInfo);
        easyUIAttributeInfo.setUseAttributeImage(attributeInfo.isUseAttributeImage());
        return easyUIAttributeInfo;
    }

    @Transactional
    @Override
    public void updateProdType(ProductType productType, Long[] brandIds) {
        int i = productCategoryDao.updateProdType(productType);
        if (i == 0) {
            throw new RuntimeException("商品类型修改失败");
        }
        productCategoryDao.deleteProdTypeBrand(productType);
        for (Long brandId : brandIds) {
            int j = productCategoryDao.addProdTypeBrand(productType, brandId);
            if (j == 0) {
                throw new RuntimeException("商品类型品牌修改失败");
            }
        }
    }

    @Override
    public CategoryInfo getProdCatById(Long categoryId, String platformId, Long shopId) {
        return productCategoryDao.getProdCatById(categoryId, platformId, shopId);
    }

    @Override
    public void updateVisible(Long shopId, String platformId, Long categoryId, Integer flag) {
        //深度优先遍历
        Stack<Long> nodeDeque = new Stack<Long>();
        Long node = categoryId; //分类Id
        nodeDeque.push(node);
        while (!nodeDeque.isEmpty()) {
            node = nodeDeque.pop();
            productCategoryDao.updateVisible(shopId, platformId, node, flag);
            List<Long> children = productCategoryDao.getChildCatIdsByParentId(node, platformId, shopId);
            if (children != null && !children.isEmpty()) {
                for (Long child : children) {
                    nodeDeque.push(child);
                }
            }
        }
    }

    @Transactional
    @Override
    public void addRegionCat(Long shopId, String platformId, Long regionId, Long[] indeterminateIds, Long[] checkedIds) {
        productCategoryDao.deleteRegionCatByRegionId(shopId, platformId, regionId);
        productCategoryDao.addRegionCat(shopId, platformId, regionId, checkedIds);
    }

    @Override
    public List<EasyUICatTreeNode> getProdCatListByRegionId(Long shopId, String platformId, Long regionId) {
        List<EasyUICatTreeNode> categoryList = productCategoryDao.getChildCategoriesByParentId(platformId, null);
        List<Long> ids = productCategoryDao.getProdCatIdsByRegionId(shopId, platformId, regionId);
        for (EasyUICatTreeNode easyUICatTreeNode : categoryList) {
            //深度优先遍历
            Stack<EasyUICatTreeNode> nodeDeque = new Stack<EasyUICatTreeNode>();
            EasyUICatTreeNode node = easyUICatTreeNode;
            nodeDeque.push(node);
            while (!nodeDeque.isEmpty()) {
                node = nodeDeque.pop();
                List<EasyUICatTreeNode> children = productCategoryDao.getChildCategoriesByParentId(platformId, node.getId());
                if (children != null && !children.isEmpty()) {
                    for (EasyUICatTreeNode child : children) {
                        nodeDeque.push(child);
                        if (!child.isLeaf()) {
                            child.setState("closed");
                        }
                        if (ids.contains(child.getId()) && child.isLeaf()) {
                            child.setChecked(true);
                        }
                    }
                    node.setChildren(children);
                }
            }
        }
        return categoryList;
    }

    @Override
    public List<ZTreeNode> getZTreeCatListByRegionId(Long shopId, String platformId, Long regionId) {
        List<ZTreeNode> categoryList = productCategoryDao.getZTreeCatList(shopId, platformId);
        List<Long> ids = productCategoryDao.getProdCatIdsByRegionId(shopId, platformId, regionId);
        for (ZTreeNode node : categoryList) {
            if (ids.contains(node.getId())) {
                node.setChecked(true);
            }
        }
        return categoryList;
    }

    @Override
    public List<CategoryInfoResult> getProdCategoryByRegionIdForWeChat(Long shopId, String platformId, Long regionId, Long parentCategoryId) {
        List<CategoryInfoResult> regionCatResultList =  productCategoryDao.getProdCategoryByRegionIdForWeChat(shopId, platformId, regionId, parentCategoryId);
        if (regionCatResultList == null || regionCatResultList.size() == 0) {
            regionCatResultList =  productCategoryDao.getProdCategoryForWeChat(shopId, platformId, parentCategoryId);
        }
        return regionCatResultList;
    }

    @Override
    public EasyUIListResult<BrandInfo> getTypeBrandList(PaginationBrandInfo brandInfo) {
        List<BrandInfo> brandInfoList = productCategoryDao.getTypeBrandList(brandInfo);
        Long total = productCategoryDao.getTypeBrandCount(brandInfo);
        return new EasyUIListResult<BrandInfo>(total, brandInfoList);
    }

    @Transactional
    @Override
    public void addTypeBrand(BrandInfo brandInfo) {
        int i = productCategoryDao.addTypeBrand(brandInfo);
        if (brandInfo.getProductTypes() != null && brandInfo.getProductTypes().size() != 0) {
            int j = productCategoryDao.addBrandType(brandInfo);
        }
    }

    @Transactional
    @Override
    public void updateTypeBrand(BrandInfo brandInfo) {
        int i = productCategoryDao.updateTypeBrand(brandInfo);
        int j = productCategoryDao.deleteBrandType(brandInfo);
        if (brandInfo.getProductTypes() != null && brandInfo.getProductTypes().size() != 0) {
            int k = productCategoryDao.addBrandType(brandInfo);
        }

    }

    @Transactional
    @Override
    public void deleteTypeBrand(BrandInfo brandInfo) {
        int i = productCategoryDao.deleteTypeBrand(brandInfo);
        int j = productCategoryDao.deleteBrandType(brandInfo);
    }

    @Override
    public List<ProductType> getProdTypeList(String platformId, Long shopId) {
        return productCategoryDao.getProdTypeList(platformId, shopId);
    }

    @Override
    public BrandInfo getTypeBrand(BrandInfo brandInfo) {
        BrandInfo brand = productCategoryDao.getTypeBrand(brandInfo);
        List<Integer> productTypes = productCategoryDao.getBrandProdTypeIds(brandInfo);
        brand.setProductTypes(productTypes);
        return brand;
    }


}
