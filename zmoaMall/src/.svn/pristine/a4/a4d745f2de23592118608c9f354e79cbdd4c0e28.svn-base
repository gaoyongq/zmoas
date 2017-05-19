package com.zm.mall.client.service.business.product;

import com.zm.mall.client.Page;
import com.zm.mall.client.result.business.product.CategoryInfoResult;
import com.zm.mall.client.result.business.product.ProductInfoResult;
import com.zm.mall.client.vo.business.product.*;
import com.zm.mall.domain.business.product.*;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by lp on 2016/11/12.
 *查询商品
 */
@WebService
public interface ProductInfoService {

    /**
     * 分页
     */
    Page selectProductInfo(Page page,ProductInfoVo productInfoVo);
    /**
     * 查属性
     */
    List<AttributeInfo> selSpecById(ProductInfoVo productInfoVo);
    /**
     *查规格
     */
    List<AttributeInfo> selSkuById(ProductInfoVo productInfoVo);
    /**
     * 查库存
     * @param skuInfoVo
     * @return
     */
    List<SKUInfo> selSkuInfoById(SKUInfoVo skuInfoVo);
    /**
     * 查询分类
     * @param categoryInfoVo
     * @return
     */
    List<CategoryInfo> categoryInfo(CategoryInfoVo categoryInfoVo);

    /**
     * 录入数据的预查询
     * @return
     */
    ProductInfo preProductInfo(String platformId, Long shopId);

    /**
     * 查规格
     * @param attributeInfoVo
     * @return
     */
    List<AttributeInfo> selAttributeInfo(AttributeInfoVo attributeInfoVo);

    /**
     * 查询地址
     * @param regionInfoVo
     * @return
     */
    List<RegionInfo> selRegionInfoAll(RegionInfoVo regionInfoVo);

    /**
     * 查询品牌
     * @param attributeInfoVo
     * @return
     */
    List<BrandInfo> selBrandInfoByType(AttributeInfoVo attributeInfoVo);

    /**
     * 获得统一调整价
     * @param categoryInfoVo
     * @return
     */
    CategoryInfo price(CategoryInfoVo categoryInfoVo);

    ProductInfo productCode(ProductInfoVo productInfoVo);

    /**
     * 插入产品
     * @param productInfoVo
     * @return
     */
    @Transactional
    Long insertProduct(ProductInfoVo productInfoVo);

    /**
     * 添加产品图片路径。<br/>
     * 过时，使用
     * {@link #updateProductImageUrl(List, Long, String, Long) Integer updateProductImageUrl(List<String> str, Long productResultID, String pluteformid, Long shopId)}
     * 代替。
     * @param str
     * @param productResultID
     * @return
     */
    @Deprecated
    Integer upProuctUrlPrice(List<String> str,Long productResultID,String pluteformid);

    /**
     * 添加产品图片路径
     */
    Integer updateProductImageUrl(List<String> str, Long productResultID, String pluteformid, Long shopId);

    /**
     * 删除产品
     * @param delProductId
     * @return
     */
    @Transactional
    Integer delProduct(String delProductId,String pluteformid);

    /**
     * 根据Id查询
     * @param productInfoVo
     * @return
     */
    ProductInfoResult selProductInfoById(ProductInfoVo productInfoVo);
    /**
     * 白洋洋  根据产品Id查询相关的规格信息
     */
    List<AttributeInfo> selectAttribute(ProductInfoVo productInfoVo);



    /**
     * 根据产地子节点获得父节点
     * @param regionInfoVo
     * @return
     */
    SelProductUtil selRegionByProductId(RegionInfoVo regionInfoVo);

    /**
     * 根据路径查找所有父节点
     * @param categoryInfoVo
     * @return
     */
    SelProductUtil selCateByPath(CategoryInfoVo categoryInfoVo);

    /**
     * 获得多分类
     * @param productInfoVo
     * @return
     */
    List<SelProductUtil<CategoryInfo>> preUpDateCateGory(ProductInfoVo productInfoVo);

    /**
     * 根据规格查询生成订单
     * @param cate
     * @param typeId
     * @param appearence
     * @param thick
     * @param productName
     * @param currentPage
     * @return
     */
    Page selProductBySku(String cate,Integer typeId,String appearence,String thick,String productName,String productCode,Integer currentPage);

    /**
     * 获得关联产品
     * @param productInfoVo
     * @return
     */
    List<ProductInfo> selProductRelated(ProductInfoVo productInfoVo);

    /**
     * 获得产品路径
     * @param productInfoVo
     * @return
     */
    List<ProductImage> selProductImageByProductId(ProductInfoVo productInfoVo);
    /**
     * 更新产品
     * @param productInfoVo
     * @return
     */
    @Transactional
    Long upDateProduct(ProductInfoVo productInfoVo);

    /**
     * 获得图片大小
     * @return
     */
    List<ThumbnailSize> selThumbnailSizeSpec();

    //根据产品id查询
    ProductInfo productId(ProductInfoVo productInfoVo);


    /**添加商品收藏
     *
     * @param favoriteInfoVo
     * @return
     */
    Integer AddShopFavorite(FavoriteInfoVo favoriteInfoVo);

    /**
     * 删除商品收藏
     * @param favoriteStr
     * @param pluteformid
     * @return
     */
    Integer delFavoriteInfoById(String[] favoriteStr,String pluteformid);

    /**
     * 查询产品
     * @param page
     * @return
     */
    List<ProductInfo> selFavoriteInfoById(Page page);


    /**
     * 添加商品属性
     * @param stationModesString
     * @param pluteformid
     * @param productId
     * @return
     */
    Integer AddStationModes(String[] stationModesString,String pluteformid, Long shopId, Long productId);

    /**
     * 根据产品查属性
     * @param productInfoVo
     * @return
     */
    List<ProductStationModes> selProductStationModesByProductId(ProductInfoVo productInfoVo);

    /**
     * 根据属性差产品
     * @param productStationModesVo
     * @return
     */
    List<ProductStationModes> selProductStationModesByStationId(ProductStationModesVo productStationModesVo);

    /**
     * 删除属性  根据产品
     * @param productInfoVo
     * @return
     */
    Integer delProductStationModesByProductId(ProductInfoVo productInfoVo);
    /**
     *微信App查询商品分类
     */
    List<CategoryInfoResult> weCartAddAppSelectCategory(CategoryInfoVo categoryInfoVo);

    /**
     * 微信App查询分类商品
     * @param page
     * @return
     */
    Page selProductAppWeCart(Page page);
    /**
     * 微信App查询商品
     * @param page
     * @return
     */
    Page weCartAddAppSelectProduct(Page page);

    //webservice数据同步
    @Transactional
    Integer web_InsertProduct(ProductInfo productInfo) throws Exception;
    @Transactional
    Integer web_updateProduct(ProductInfo productInfo) throws Exception;
    @Transactional
    Integer web_deleteProduct(List<Long> productInfo,Integer status) throws Exception;
    //移动APP收藏查询
    List<FavoriteInfo> selFavoriteInfoByUserId(FavoriteInfo favoriteInfo);
    //移动APP收藏查询数量
    Integer getFavoriteInfoCount(String userId);

    /**
     * 查询商户列表，用在兆美自营产品的分配上。
     * @param filter 过滤条件。查询店铺还是商城
     * @return
     */
    EasyUIListResult getBusinessList(Long page, Long rows, String filter);


    EasyUIListResult getSelfProductListByShopId(Long page, Long rows, Long shopId);

    EasyUIListResult getSelfProductListByPlatformId(Long page, Long rows, Long platformId);

    void deleteSelfProductByShopId(Long shopId, Long productId);

    void deleteSelfProductByMallId(Long mallId, Long productId);

    void saveSelfProductByShopId(Long shopId, Long[] productIds);

    void saveSelfProductByMallId(Long mallId, Long[] productIds);

    void updateSelfProduct(Long productId, Integer selfSell);

    //通过店铺id获取店铺产品
    List<ProductInfo> selShopProduct(ProductInfo productInfo);

    void insertProductImageUrls(InsertProductImageDTO insertProductImageDTO);

    //根据店铺id或者平台id查询商品客服手机号
    String getServicePhoneByShopIdOrPId(ProductInfo productInfo);
}
