package com.zm.mall.service.system.impl;

import com.zm.mall.client.service.system.AdService;
import com.zm.mall.dao.system.AdDao;
import com.zm.mall.domain.system.Ad;
import com.zm.mall.domain.system.EasyUIPaginationAd;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商家广告
 * Created by Bochao on 2017/3/17.
 */
@Service("adService")
public class AdServiceImpl implements AdService {
    @Resource
    private AdDao adDao;

    @Override
    public void addAd(Ad ad) {
        int i = adDao.addAd(ad);
        if (i == 0) throw new RuntimeException();
    }

    @Override
    public EasyUIListResult<Ad> getAdList(EasyUIPaginationAd ad) {
        List<Ad> rows = adDao.getAdList(ad);
        Long total = adDao.getAdCount(ad);
        return new EasyUIListResult<Ad>(total, rows);
    }

    @Override
    public Ad getAdById(Long id, String platformId) {
        return adDao.getAdById(id, platformId);
    }

    @Override
    public void updateAd(Ad ad) {
        int i = adDao.updateAd(ad);
        if (i == 0) throw new RuntimeException();
    }

    @Override
    public void updateAdStatus(Ad ad) {
        int i = adDao.updateAdStatus(ad);
        if (i == 0) throw new RuntimeException();
    }

    @Override
    public List<Ad> getAds(Ad ad) {
        return adDao.getAds(ad);
    }
}
