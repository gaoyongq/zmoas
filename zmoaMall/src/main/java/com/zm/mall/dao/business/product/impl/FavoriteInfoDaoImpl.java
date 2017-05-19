package com.zm.mall.dao.business.product.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.FavoriteInfoDao;
import com.zm.mall.domain.business.product.FavoriteInfo;
import com.zm.mall.domain.business.product.ProductInfo;

import java.util.List;

/**
 * Created by lp on 2017/3/6.
 */
public class FavoriteInfoDaoImpl extends BaseDaoImpl<FavoriteInfo> implements FavoriteInfoDao {

    private static final String NAMESPACE="com.zm.mall.dao.business.product.FavoriteInfoDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    //添加收藏产品
    @Override
    public Integer AddShopFavorite(FavoriteInfo favoriteInfo) {
        return sqlTemplate.update(getNameSpace("AddShopFavorite"),favoriteInfo);
    }
    //查询产品收藏
    @Override
    public List<FavoriteInfo> selFavoriteInfoById(ProductInfo productInfo) {
        return sqlTemplate.selectList(getNameSpace("selFavoriteInfoById"),productInfo);
    }
    //删除收藏
    @Override
    public Integer delFavoriteInfoById(FavoriteInfo favoriteInfo) {
        return sqlTemplate.delete(getNameSpace("delFavoriteInfoById"),favoriteInfo);
    }
    //查询收藏数量
    public Integer getFavoriteInfoCount(String userId){
        return sqlTemplate.selectOne(getNameSpace("getFavoriteInfoCount"),userId);
    }
}
