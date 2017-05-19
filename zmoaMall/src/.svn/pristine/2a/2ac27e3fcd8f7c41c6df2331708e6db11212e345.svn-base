package com.zm.mall.dao.business.product;

import com.zm.mall.client.Page;
import com.zm.mall.common.utils.OrderBySku;
import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.FavoriteInfo;
import com.zm.mall.domain.business.product.InsertProductImageDTO;
import com.zm.mall.domain.business.product.ProductInfo;

import java.util.List;

/**
 * Created by lp on 2016/11/12.
 */
public interface ProductInfoDao extends BaseDao<ProductInfo> {

    /**
     * @param page
     * @return 分页查询
     */
    List<ProductInfo> selectByContion(Page page);

    //获得总数
    Integer selectAllCount(Page page);

    //添加商品
    Integer InsertProduct(ProductInfo productInfo);

    //获得productCode
    List<ProductInfo> productInfoCode(ProductInfo productInfo);

    //查询id
    Long selProductId(ProductInfo productInfo);

    //更新图片路径
    Integer updateProduct(ProductInfo productInfo);

    //删除产品
    Integer delProduct(ProductInfo productInfo);

    //根据id查询产品
    ProductInfo selProductInfoById(ProductInfo productInfo);

    //根据sku规格查询product
    Integer selectTotalCount(OrderBySku orderBySku);

    List<ProductInfo> selProductBySku(OrderBySku orderBySku);

    //获得关联产品
    List<ProductInfo> selProductById(ProductInfo productInfo);

    //更新产品
    Integer updateProductById(ProductInfo productInfo);

    //根据用户购买的类型   查询类似商品
    List<ProductInfo> productListByCate(Page page);

    //wecartAddApp分页查询
    List<ProductInfo> weCartAddAppSelectByContion(Page page);

    //wecartAddApp分页查询获得总数
    Integer weCartAddAppSelectAllCount(Page page);

    //查询收藏产品
    List<ProductInfo> selFavoriteInfoById(Page page);

    //webservice数据同步
    Integer web_insertProduct(ProductInfo productInfo) throws Exception;

    Integer web_updateProduct(ProductInfo productInfo) throws Exception;

    Integer web_deleteProduct(List<Long> productInfo, Integer status) throws Exception;

    //根据id查询
    List<ProductInfo> productInfoId(ProductInfo productInfo);

    //移动APP收藏查询
    List<FavoriteInfo> selFavoriteInfoByUserId(FavoriteInfo favoriteInfo);


    List<ProductInfo> listSelfProductByShopId(Long start, Long size, Long shopId);

    Long countSelfProductByShopId(Long shopId);

    List<ProductInfo> listSelfProductByMallId(Long start, Long size, Long mallId);

    Long countSelfProductByMallId(Long mallId);

    /**
     * 删除商城所有关联自营产品
     * @param mallId 商城id
     * @return 删除成功的记录数
     */
    Integer removeSelfProductByMallId(Long mallId);

    /**
     * 删除商城单个关联自营产品
     * @param mallId 商城id
     * @param productId 产品id
     * @return 删除成功的记录数
     */
    Integer removeSelfProductByMallId(Long mallId, Long productId);

    /**
     * 删除店铺所有关联自营产品
     * @param shopId 店铺id
     * @return 删除成功的记录数
     */
    Integer removeSelfProductByShopId(Long shopId);

    /**
     * 删除店铺单个关联自营产品
     * @param shopId 店铺id
     * @param productId 产品id
     * @return 删除成功的记录数
     */
    Integer removeSelfProductByShopId(Long shopId, Long productId);

    /**
     * 添加店铺和自营产品关联
     * @param shopId 店铺id
     * @param productIds 关联产品id数组
     * @return 添加成功的记录数
     */
    Integer saveShopSelfProducts(Long shopId, Long[] productIds);

    /**
     * 添加商城和自营产品关联
     * @param mallId 商城id
     * @param productIds 关联产品id数组
     * @return 添加成功的记录数
     */
    Integer saveMallSelfProducts(Long mallId, Long[] productIds);


    Integer updateSelfProduct(Long productId, Integer selfSell);

    //通过店铺id获取店铺产品
    List<ProductInfo> selShopProduct(ProductInfo productInfo);

    Integer insertProductImageUrls(InsertProductImageDTO insertProductImageDTO);

    /**
     * 新版查询商品图片
     * @param insertProductImageDTO
     * @return
     */
    List<String> listProductImageUrl(InsertProductImageDTO insertProductImageDTO);
    //根据店铺id或者平台id查询商品客服手机号
    String getServicePhoneByShopIdOrPId(ProductInfo productInfo);
}



