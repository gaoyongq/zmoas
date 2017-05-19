package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.FavoriteInfo;
import com.zm.mall.domain.business.product.ProductInfo;

import java.util.List;

/**
 * Created by lp on 2017/3/6.
 */
public interface FavoriteInfoDao extends BaseDao<FavoriteInfo> {
    /**
     * 添加收藏产品
     * @param favoriteInfo
     * @return
     */
    Integer AddShopFavorite(FavoriteInfo favoriteInfo);
    /**
     * 查询收藏
     * @param productInfo
     * @return
     */
    List<FavoriteInfo> selFavoriteInfoById(ProductInfo productInfo);

    /**
     * 删除收藏
     * @param favoriteInfo
     * @return
     */
    Integer delFavoriteInfoById(FavoriteInfo favoriteInfo);
    /**
     * 查询收藏的数量
     */
    Integer getFavoriteInfoCount(String userId);}
