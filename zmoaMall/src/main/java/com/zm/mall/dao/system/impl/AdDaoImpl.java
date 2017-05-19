package com.zm.mall.dao.system.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.AdDao;
import com.zm.mall.domain.system.Ad;
import com.zm.mall.domain.system.EasyUIPaginationAd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商家广告
 * Created by Bochao on 2017/3/17.
 */
public class AdDaoImpl extends BaseDaoImpl implements AdDao {

    private final static String NAMESPACE = "com.zm.mall.dao.system.AdDao.";

    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

    @Override
    public int addAd(Ad ad) {
        return sqlTemplate.insert(getNameSpace("addAd"), ad);
    }

    @Override
    public List<Ad> getAdList(EasyUIPaginationAd ad) {
        return sqlTemplate.selectList(getNameSpace("getAdList"), ad);
    }

    @Override
    public Long getAdCount(EasyUIPaginationAd ad) {
        return sqlTemplate.selectOne(getNameSpace("getAdCount"), ad);
    }

    @Override
    public Ad getAdById(Long id, String platformId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("platformId", platformId);
        return sqlTemplate.selectOne(getNameSpace("getAdById"), map);
    }

    @Override
    public int updateAd(Ad ad) {
        return sqlTemplate.update(getNameSpace("updateAd"), ad);
    }

    @Override
    public int updateAdStatus(Ad ad) {
        return sqlTemplate.update(getNameSpace("updateAdStatus"), ad);
    }

    @Override
    public List<Ad> getAds(Ad ad) {
        return sqlTemplate.selectList(getNameSpace("getAds"), ad);
    }


}
