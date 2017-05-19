package com.zm.mall.client.service.system;

import com.zm.mall.domain.system.Ad;
import com.zm.mall.domain.system.EasyUIPaginationAd;
import com.zm.mall.domain.system.coupon.EasyUIListResult;

import java.util.List;

/**
 * 商家广告
 * Created by Bochao on 2017/3/17.
 */
public interface AdService {

    void addAd(Ad ad);

    EasyUIListResult getAdList(EasyUIPaginationAd ad);

    Ad getAdById(Long id, String platformId);


    void updateAd(Ad ad);

    void updateAdStatus(Ad ad);

    List<Ad> getAds(Ad ad);

}
