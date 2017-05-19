package com.zm.mall.dao.business.product.impl;

import com.zm.mall.client.Page;
import com.zm.mall.common.utils.OrderBySku;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.ProductInfoDao;
import com.zm.mall.domain.business.product.FavoriteInfo;
import com.zm.mall.domain.business.product.InsertProductImageDTO;
import com.zm.mall.domain.business.product.ProductInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lp on 2016/11/12.
 */
public class ProductDaoInfoImpl extends  BaseDaoImpl<ProductInfo> implements ProductInfoDao {
    private final static String NAMESPACE = "com.zm.mall.dao.business.product.ProductInfoDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public List<ProductInfo> selectByContion(Page page) {
        return sqlTemplate.selectList(getNameSpace("selectByContion"), page);
    }

    @Override
    public Integer selectAllCount(Page page) {
        return sqlTemplate.selectOne(getNameSpace("selectAllCount"),page);
    }

    @Override
    public Integer InsertProduct(ProductInfo productInfo) {
        return sqlTemplate.insert(getNameSpace("InsertProduct"), productInfo);
    }

    @Override
    public List<ProductInfo> productInfoCode(ProductInfo productInfo) {
        return sqlTemplate.selectList(getNameSpace("productInfoCode"), productInfo);
    }

    @Override
    public Long selProductId(ProductInfo productInfo) {
        ProductInfo p = sqlTemplate.selectOne(getNameSpace("selProductId"), productInfo);
        return p.getProductId();
    }

    @Override
    public Integer updateProduct(ProductInfo productInfo) {
        return sqlTemplate.update(getNameSpace("updateProduct"), productInfo);
    }

    @Override
    public Integer delProduct(ProductInfo productInfo) {
        return sqlTemplate.update(getNameSpace("delProduct"), productInfo);
    }

    @Override
    public ProductInfo selProductInfoById(ProductInfo productInfo) {
        return sqlTemplate.selectOne(getNameSpace("selProductInfoById"),productInfo);
    }

    @Override
    public Integer selectTotalCount(OrderBySku orderBySku) {
        return sqlTemplate.selectOne(getNameSpace("selProductCountBySku"),orderBySku);
    }

    @Override
    public List<ProductInfo> selProductBySku(OrderBySku orderBySku) {
        return sqlTemplate.selectList(getNameSpace("selProductBySku"),orderBySku);
    }

    @Override
    public List<ProductInfo> selProductById(ProductInfo productInfo) {
        return sqlTemplate.selectList(getNameSpace("selProductById"),productInfo);
    }

    @Override
    public Integer updateProductById(ProductInfo productInfo) {
        return sqlTemplate.update(getNameSpace("updateProductById"),productInfo);
    }
    //根据用户购买的类型   查询类似商品
    @Override
    public List<ProductInfo> productListByCate(Page page) {
        return sqlTemplate.selectList(getNameSpace("productListByCate"),page);
    }

    @Override
    public List<ProductInfo> weCartAddAppSelectByContion(Page page) {
        return sqlTemplate.selectList(getNameSpace("weCartAddAppSelectByContion"),page);
    }

    @Override
    public Integer weCartAddAppSelectAllCount(Page page) {
        return sqlTemplate.selectOne(getNameSpace("weCartAddAppSelectAllCount"),page);
    }

    @Override
    public List<ProductInfo> selFavoriteInfoById(Page page) {
        return sqlTemplate.selectList(getNameSpace("selFavoriteInfoById"), page);
    }

    /**webservice数据同步录入
     *
     * @param productInfo
     * @return
     */
    public Integer web_insertProduct(ProductInfo productInfo){
        try{
                    sqlTemplate.insert(getNameSpace("web_insertProductInfo"), productInfo);
            if(productInfo.getProductCategoriesList().size()>0 && productInfo.getProductCategoriesList() !=null){
                for(int Cate=0;Cate<productInfo.getProductCategoriesList().size();Cate++){
                    sqlTemplate.insert(getNameSpace("web_insertProductCategories"), productInfo.getProductCategoriesList().get(Cate));
                }
            }
            if(productInfo.getAttributeValueList().size()>0 && productInfo.getAttributeValueList() !=null){
                for(int AV=0;AV<productInfo.getAttributeValueList().size();AV++){
                    sqlTemplate.insert(getNameSpace("web_insertAttributeValue"), productInfo.getAttributeValueList().get(AV));
                }
            }
            if(productInfo.getProductAttributeList().size()>0 && productInfo.getProductAttributeList() !=null){
                for(int pa = 0;pa<productInfo.getProductAttributeList().size();pa++){
                    sqlTemplate.insert(getNameSpace("web_insertProductAttribute"), productInfo.getProductAttributeList().get(pa));
                }
            }
            if(productInfo.getSkuInfoList().size()>0 && productInfo.getSkuInfoList() !=null){
                for(int SkuInfo = 0;SkuInfo<productInfo.getSkuInfoList().size();SkuInfo++){
                    sqlTemplate.insert(getNameSpace("web_insertSKUInfo"), productInfo.getSkuInfoList().get(SkuInfo));
                }
            }
            if(productInfo.getSkuItemList().size()>0 && productInfo.getSkuItemList() !=null){
                for(int si=0;si< productInfo.getSkuItemList().size();si++){
                    sqlTemplate.insert(getNameSpace("web_insertSKUItem"), productInfo.getSkuItemList().get(si));
                }
            }
            if(productInfo.getSkuRelationList().size()>0 && productInfo.getSkuRelationList() !=null){
                for(int sr=0;sr<productInfo.getSkuRelationList().size();sr++){
                    sqlTemplate.insert(getNameSpace("web_insetSKURelation"), productInfo.getSkuRelationList().get(sr));
                }
            }
            if(productInfo.getRelatedProductList().size()>0 && productInfo.getRelatedProductList() !=null){
                for(int rp = 0;rp<productInfo.getRelatedProductList().size();rp++){
                    sqlTemplate.insert(getNameSpace("web_insertRelatedProduct"), productInfo.getRelatedProductList().get(rp));
                }
            }
            if(productInfo.getProductImagelist().size()>0 && productInfo.getProductImagelist() !=null){
                for(int pi = 0;pi<productInfo.getProductImagelist().size();pi++){
                    sqlTemplate.insert(getNameSpace("web_insertProductImage"), productInfo.getProductImagelist().get(pi));
                }
            }
            if( productInfo.getProductStationModes().size()>0 &&  productInfo.getProductStationModes() !=null){
                sqlTemplate.delete(getNameSpace("web_deleteProductStationModes"), productInfo.getProductStationModes());
            }
            if( productInfo.getProductStationModes().size()>0 &&  productInfo.getProductStationModes() !=null){
                for(int psm=0;psm< productInfo.getProductStationModes().size();psm++){
                    sqlTemplate.insert(getNameSpace("web_insertProductStationModes"), productInfo.getProductStationModes().get(psm));
                }
            }
            if(productInfo.getProductPackage().size()>0 && productInfo.getProductPackage() !=null){
                for(int pp=0;pp< productInfo.getProductPackage().size();pp++){
                    sqlTemplate.insert(getNameSpace("web_insertProductPackage"), productInfo.getProductPackage().get(pp));
                }
            }
        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    /**数据同步更新
     *
     * @param productInfo
     * @return
     */
    public Integer web_updateProduct(ProductInfo productInfo){
        try{
            sqlTemplate.delete(getNameSpace("web_delProductCategories"), productInfo.getProductId());
            sqlTemplate.delete(getNameSpace("web_delProductAttribute"), productInfo.getProductId());
            sqlTemplate.delete(getNameSpace("web_delSKUInfo"), productInfo.getProductId());
            sqlTemplate.delete(getNameSpace("web_delSKUItem"), productInfo.getProductId());
            sqlTemplate.delete(getNameSpace("web_delSKURelation"), productInfo.getProductId());
            sqlTemplate.delete(getNameSpace("web_delRelatedProduct"), productInfo.getProductId());
            sqlTemplate.delete(getNameSpace("web_delProductImage"), productInfo.getProductId());
            sqlTemplate.delete(getNameSpace("web_delSkuMemberPrice"), productInfo.getProductId());
            sqlTemplate.delete(getNameSpace("web_delAccessoriesValue"), productInfo.getProductId());
            sqlTemplate.delete(getNameSpace("web_delProductAccessories"), productInfo.getProductId());
            sqlTemplate.update(getNameSpace("web_updateProductById"), productInfo);

            if(productInfo.getProductCategoriesList().size()>0 && productInfo.getProductCategoriesList() !=null){
                for(int Cate=0;Cate<productInfo.getProductCategoriesList().size();Cate++){
                    sqlTemplate.insert(getNameSpace("web_insertProductCategories"), productInfo.getProductCategoriesList().get(Cate));
                }
            }
            if(productInfo.getAttributeValueList().size()>0 && productInfo.getAttributeValueList() !=null){
                for(int AV=0;AV<productInfo.getAttributeValueList().size();AV++){
                    sqlTemplate.insert(getNameSpace("web_insertAttributeValue"), productInfo.getAttributeValueList().get(AV));
                }
            }
            if(productInfo.getProductAttributeList().size()>0 && productInfo.getProductAttributeList() !=null){
                for(int pa = 0;pa<productInfo.getProductAttributeList().size();pa++){
                    sqlTemplate.insert(getNameSpace("web_insertProductAttribute"), productInfo.getProductAttributeList().get(pa));
                }
            }
            if(productInfo.getSkuInfoList().size()>0 && productInfo.getSkuInfoList() !=null){
                for(int SkuInfo = 0;SkuInfo<productInfo.getSkuInfoList().size();SkuInfo++){
                    sqlTemplate.insert(getNameSpace("web_insertSKUInfo"), productInfo.getSkuInfoList().get(SkuInfo));
                }
            }
            if(productInfo.getSkuItemList().size()>0 && productInfo.getSkuItemList() !=null){
                for(int si=0;si< productInfo.getSkuItemList().size();si++){
                    sqlTemplate.insert(getNameSpace("web_insertSKUItem"), productInfo.getSkuItemList().get(si));
                }
            }
            if(productInfo.getSkuRelationList().size()>0 && productInfo.getSkuRelationList() !=null){
                for(int sr=0;sr<productInfo.getSkuRelationList().size();sr++){
                    sqlTemplate.insert(getNameSpace("web_insetSKURelation"), productInfo.getSkuRelationList().get(sr));
                }
            }
            if(productInfo.getRelatedProductList().size()>0 && productInfo.getRelatedProductList() !=null){
                for(int rp = 0;rp<productInfo.getRelatedProductList().size();rp++){
                    sqlTemplate.insert(getNameSpace("web_insertRelatedProduct"), productInfo.getRelatedProductList().get(rp));
                }
            }
            if(productInfo.getProductImagelist().size()>0 && productInfo.getProductImagelist() !=null){
                for(int pi = 0;pi<productInfo.getProductImagelist().size();pi++){
                    sqlTemplate.insert(getNameSpace("web_insertProductImage"), productInfo.getProductImagelist().get(pi));
                }
            }
        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    /**数据同步删除
     *
     * @param productInfoId
     * @return
     */
    public Integer web_deleteProduct(List<Long> productInfoId,Integer status){
        try{
            for (int i = 0;i<productInfoId.size();i++){
                ProductInfo productInfo = new ProductInfo();
                productInfo.setProductId(productInfoId.get(i));
                productInfo.setSaleStatus(status);
                sqlTemplate.update(getNameSpace("web_deleteProductById"),productInfo);
            }
        }catch (Exception e){
            return 0;
        }
        return 1;
    }
    /**
     * 根据产品ID查询产品
     * @param productInfo
     * @return
     */
    @Override
    public List<ProductInfo> productInfoId(ProductInfo productInfo) {
        return sqlTemplate.selectList(getNameSpace("productInfoId"), productInfo);
    }

    /**
     * 移动APP根据userId查询收藏
     * @param favoriteInfo
     * @return
     */
    @Override
    public List<FavoriteInfo> selFavoriteInfoByUserId(FavoriteInfo favoriteInfo) {
        return sqlTemplate.selectList(getNameSpace("selFavoriteInfoByUserId"),favoriteInfo);
    }


    @Override
    public List<ProductInfo> listSelfProductByShopId(Long start, Long size, Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start.intValue());
        map.put("size", size.intValue());
        map.put("shopId", shopId);
        return sqlTemplate.selectList(getNameSpace("listSelfProductByShopId"), map);
    }

    @Override
    public Long countSelfProductByShopId(Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopId", shopId);
        return sqlTemplate.selectOne(getNameSpace("countSelfProductByShopId"), map);
    }

    @Override
    public List<ProductInfo> listSelfProductByMallId(Long start, Long size, Long mallId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start.intValue());
        map.put("size", size.intValue());
        map.put("mallId", mallId);
        return sqlTemplate.selectList(getNameSpace("listSelfProductByMallId"), map);
    }

    @Override
    public Long countSelfProductByMallId(Long mallId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mallId", mallId);
        return sqlTemplate.selectOne(getNameSpace("countSelfProductByMallId"), map);
    }

    @Override
    public Integer removeSelfProductByMallId(Long mallId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mallId", mallId);
        return sqlTemplate.selectOne(getNameSpace("removeSelfProductByMallId"), map);
    }

    @Override
    public Integer removeSelfProductByMallId(Long mallId, Long productId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mallId", mallId);
        map.put("productId", productId);
        return sqlTemplate.selectOne(getNameSpace("removeSelfProductByMallId"), map);
    }

    @Override
    public Integer removeSelfProductByShopId(Long shopId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopId", shopId);
        return sqlTemplate.selectOne(getNameSpace("removeSelfProductByShopId"), map);
    }

    @Override
    public Integer removeSelfProductByShopId(Long shopId, Long productId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopId", shopId);
        map.put("productId", productId);
        return sqlTemplate.selectOne(getNameSpace("removeSelfProductByShopId"), map);
    }

    @Override
    public Integer saveShopSelfProducts(Long shopId, Long[] productIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopId", shopId);
        map.put("productIds", productIds);
        return sqlTemplate.selectOne(getNameSpace("saveShopSelfProducts"), map);
    }

    @Override
    public Integer saveMallSelfProducts(Long mallId, Long[] productIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mallId", mallId);
        map.put("productIds", productIds);
        return sqlTemplate.selectOne(getNameSpace("saveMallSelfProducts"), map);
    }

    @Override
    public Integer updateSelfProduct(Long productId, Integer selfSell) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productId", productId);
        map.put("selfSell", selfSell);
        return sqlTemplate.selectOne(getNameSpace("updateSelfProduct"), map);
    }

    @Override
    public List<ProductInfo> selShopProduct(ProductInfo productInfo) {
        return sqlTemplate.selectList(getNameSpace("getProductId"),productInfo);
    }

    @Override
    public Integer insertProductImageUrls(InsertProductImageDTO insertProductImageDTO) {
        return sqlTemplate.insert(getNameSpace("insertProductImageUrls"), insertProductImageDTO);
    }

    @Override
    public List<String> listProductImageUrl(InsertProductImageDTO insertProductImageDTO) {
        return sqlTemplate.selectList(getNameSpace("listProductImageUrl"), insertProductImageDTO);
    }
    @Override
    public String getServicePhoneByShopIdOrPId(ProductInfo productInfo){
        String phone=sqlTemplate.selectOne(getNameSpace("getServicePhoneByShopIdOrPId"),productInfo);
        return phone;
    }
}
