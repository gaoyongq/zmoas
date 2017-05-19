package com.zm.mall.dao.system;

import com.zm.mall.domain.system.Ad;
import com.zm.mall.domain.system.EasyUIPaginationAd;

import java.util.List;

/**
 * 商家广告
 * Created by Bochao on 2017/3/17.
 */
public interface AdDao {

    int addAd(Ad ad);

    List<Ad> getAdList(EasyUIPaginationAd ad);

    Long getAdCount(EasyUIPaginationAd ad);

    Ad getAdById(Long id, String platformId);

    int updateAd(Ad ad);

    int updateAdStatus(Ad ad);

    List<Ad> getAds(Ad ad);

}
