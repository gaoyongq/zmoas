package com.zm.mall.dao.system.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.CouponDao;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.system.coupon.*;
import com.zm.mall.domain.system.coupon.Coupon;
import com.zm.mall.domain.system.coupon.CouponActivity;
import com.zm.mall.domain.system.coupon.CouponActivityCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bochao on 2017/2/27.
 */
public class CouponDaoImpl extends BaseDaoImpl implements CouponDao {

    private final static String NAMESPACE = "com.zm.mall.dao.system.CouponDao.";

    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

    @Override
    public List<ProductCategory> getProductCategoriesByParentId(String platformId, Long parentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("parentId", parentId);
        return sqlTemplate.selectList(getNameSpace("getProductCategoriesByParentId"), map);
    }

    @Override
    public int addCouponActivityCategory(CouponActivityCategory couponActivityCategory) {
        return sqlTemplate.insert(getNameSpace("addCouponActivityCategory"), couponActivityCategory);
    }

    @Override
    public int addCouponActivity(CouponActivity couponActivity) {
        return sqlTemplate.insert(getNameSpace("addCouponActivity"), couponActivity);
    }

    @Override
    public int addCouponRule(CouponRule couponRule) {
        return sqlTemplate.insert(getNameSpace("addCouponRule"), couponRule);
    }

    @Override
    public int addCouponSource(CouponSource couponSource) {
        return sqlTemplate.insert(getNameSpace("addCouponSource"), couponSource);
    }

    @Override
    public int addCouponSourceProd(String platformId, Long couponSourceId, Long prodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("couponSourceId", couponSourceId);
        map.put("prodId", prodId);
        return sqlTemplate.insert(getNameSpace("addCouponSourceProd"), map);
    }

    @Override
    public int addCouponSourceProdCat(String platformId, Long couponSourceId, Long prodCatId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("couponSourceId", couponSourceId);
        map.put("prodCatId", prodCatId);
        return sqlTemplate.insert(getNameSpace("addCouponSourceProdCat"), map);
    }

    @Override
    public int addCoupon(Coupon coupon) {
        return sqlTemplate.insert(getNameSpace("addCoupon"), coupon);
    }

    @Override
    public int addCouponWithUserIds(Coupon coupon) {
        return sqlTemplate.insert(getNameSpace("addCouponWithUserIds"), coupon);
    }

    @Override
    public List<CouponActivityCategory> getCouponActivityCategories(String platformId, Long start, Long size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("start", start);
        map.put("size", size);
        return sqlTemplate.selectList(getNameSpace("getCouponActivityCategories"), map);
    }

    @Override
    public Long getCouponActivityCategoryCount(String platformId) {
        return sqlTemplate.selectOne(getNameSpace("getCouponActivityCategoryCount"), platformId);
    }

    @Override
    public List<CouponActivity> getCouponActivities(String platformId, Long start, Long size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("start", start);
        map.put("size", size);
        return sqlTemplate.selectList(getNameSpace("getCouponActivities"), map);
    }

    @Override
    public Long getCouponActivityCount(String platformId) {
        return sqlTemplate.selectOne(getNameSpace("getCouponActivityCount"), platformId);
    }

    @Override
    public List<Product> getProductsByCategoryId(String platformId, Long categoryId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("categoryId", categoryId);
        return sqlTemplate.selectList(getNameSpace("getProductsByCategoryId"), map);
    }

    @Override
    public List<Long> getCategoryIdsByProductId(String platformId, Long productId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("productId", productId);
        return sqlTemplate.selectList(getNameSpace("getCategoryIdsByProductId"), map);
    }

    @Override
    public CouponSource getCouponSourceById(String platformId, Long couponId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("couponId", couponId);
        return sqlTemplate.selectOne(getNameSpace("getCouponSourceById"), map);
    }

    @Override
    public List<CouponRuleResult> getCouponRuleResult(String platformId, Long couponCodeId, Long couponSourceId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("couponCodeId", couponCodeId);
        map.put("couponSourceId", couponSourceId);
        return sqlTemplate.selectList(getNameSpace("getCouponRuleResult"), map);
    }

    @Override
    public CouponRule getCouponRule(CouponRule couponRule) {
        return sqlTemplate.selectOne(getNameSpace("getCouponRule"), couponRule);
    }

    @Override
    public int updateCouponRule(CouponRule couponRule) {
        return sqlTemplate.update(getNameSpace("updateCouponRule"), couponRule);
    }

    @Override
    public List<CouponSource> getCouponSources(String platformId, Long start, Long size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("start", start);
        map.put("size", size);
        return sqlTemplate.selectList(getNameSpace("getCouponSources"), map);
    }

    @Override
    public Long getCouponSourceCount(String platformId) {
        return sqlTemplate.selectOne(getNameSpace("getCouponSourceCount"), platformId);
    }

    @Override
    public List<Users> getUsers(String platformId, Long start, Long size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("start", start);
        map.put("size", size);
        return sqlTemplate.selectList(getNameSpace("getUsers"), map);
    }

    @Override
    public Long getUserCount(String platformId) {
        return sqlTemplate.selectOne(getNameSpace("getUserCount"), platformId);
    }

    @Override
    public List<Long> getUserIdsOfALL(String platformId) {
        return sqlTemplate.selectList(getNameSpace("getUserIdsOfALL"), platformId);
    }

    @Override
    public int updateSendNumber(CouponSource couponSource) {
        return sqlTemplate.update(getNameSpace("updateSendNumber"), couponSource);
    }

    @Override
    public int updateActivityStatus(String platformId, Long activityId, int status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("activityId", activityId);
        map.put("status", status);
        return sqlTemplate.update(getNameSpace("updateActivityStatus"), map);
    }

    @Override
    public int updateCouponSourceStatus(String platformId, Long couponId, int status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("couponId", couponId);
        map.put("status", status);
        return sqlTemplate.update(getNameSpace("updateCouponSourceStatus"), map);
    }

    @Override
    public List<Coupon> getUserCoupons(String platformId, Long start, Long size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("start", start);
        map.put("size", size);
        return sqlTemplate.selectList(getNameSpace("getUserCoupons"), map);
    }

    @Override
    public Long getUserCouponCount(String platformId) {
        return sqlTemplate.selectOne(getNameSpace("getUserCouponCount"), platformId);
    }

    /**
     * 同步方法
     */
    @Override
    public synchronized int increaseExchangeNumber(String platformId, Long couponSourceId, Integer number) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("couponSourceId", couponSourceId);
        map.put("number", number);
        return sqlTemplate.update(getNameSpace("increaseExchangeNumber"), map);
    }

    @Override
    public int reduceUserRestPoint(String platformId, Long userId, int needPoint) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("userId", userId);
        map.put("needPoint", needPoint);
        return sqlTemplate.update(getNameSpace("reduceUserRestPoint"), map);
    }

    @Override
    public Integer getUserRestPoints(String platformId, Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("userId", userId);
        return sqlTemplate.selectOne(getNameSpace("getUserRestPoints"), map);
    }

    @Override
    public List<CouponSource> getCouponSourcesByActivityId(String platformId, Long activityId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("activityId", activityId);
        return sqlTemplate.selectList(getNameSpace("getCouponSourcesByActivityId"), map);
    }

    @Override
    public List<CouponActivity> getCouponActivitiesInProgress(String platformId) {
        return sqlTemplate.selectList(getNameSpace("getCouponActivitiesInProgress"), platformId);
    }

    @Override
    public List<Coupon> getCouponsByUserId(String platformId, Long userId, Integer status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("userId", userId);
        map.put("status", status);
        return sqlTemplate.selectList(getNameSpace("getCouponsByUserId"), map);
    }

    @Override
    public Long getParentCategoryId(Long shopId, String platformId, Long categoryId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopId", shopId);
        map.put("platformId", platformId);
        map.put("categoryId", categoryId);
        return sqlTemplate.selectOne(getNameSpace("getParentCategoryId"), map);
    }

    @Override
    public List<Long> getCouponSourceProdCatIdsBySourceId(Long sourceId) {
        return sqlTemplate.selectList(getNameSpace("getCouponSourceProdCatIdsBySourceId"), sourceId);
    }

    @Override
    public List<Long> getCouponSourceProdIdsBySourceId(Long sourceId) {
        return sqlTemplate.selectList(getNameSpace("getCouponSourceProdIdsBySourceId"), sourceId);
    }

    @Override
    public CouponSource getCouponSourceDetailById(String platformId, Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("id", id);
        return sqlTemplate.selectOne(getNameSpace("getCouponSourceDetailById"), map);
    }

    @Override
    public List<CouponRule> getCouponRuleBySourceId(String platformId, Long sourceId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("sourceId", sourceId);
        return sqlTemplate.selectOne(getNameSpace("getCouponRuleBySourceId"), map);
    }

    @Override
    public int useCoupon(String platformId, Long couponId, Long orderId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("couponId", couponId);
        map.put("orderId", orderId);
        return sqlTemplate.update(getNameSpace("useCoupon"), map);
    }

    @Override
    public List<Product> getCouponProductsBySourceId(String platformId, Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("id", id);
        return sqlTemplate.selectList(getNameSpace("getCouponProductsBySourceId"), map);
    }

    @Override
    public List<ProductCategory> getCouponProductCatsBySourceId(String platformId, Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("id", id);
        return sqlTemplate.selectList(getNameSpace("getCouponProductCatsBySourceId"), map);
    }

    @Override
    public String getCouponImgUrlById(String platformId, Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("id", id);
        return sqlTemplate.selectOne(getNameSpace("getCouponImgUrlById"), map);
    }

    @Override
    public String getActivityImgUrlById(String platformId, Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("id", id);
        return sqlTemplate.selectOne(getNameSpace("getActivityImgUrlById"), map);
    }

    @Override
    public String getActivityIntroductionById(String platformId, Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("id", id);
        return sqlTemplate.selectOne(getNameSpace("getActivityIntroductionById"), map);
    }

    @Override
    public int updateCouponStatus(String platformId, Long couponId, int status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("couponId", couponId);
        map.put("status", status);
        return sqlTemplate.update(getNameSpace("updateCouponStatus"), map);
    }

    @Override
    public List<Coupon> getCouponsByUserId(String platformId, Long userId, Integer status, Long start, Long size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("userId", userId);
        map.put("status", status);
        map.put("start", start);
        map.put("size", size);
        return sqlTemplate.selectList(getNameSpace("getPaginationCouponsByUserId"), map);
    }

    @Override
    public int addCouponSourceProd(CouponSource couponSource) {
        return sqlTemplate.insert(getNameSpace("addCouponSourceProdIds"), couponSource);
    }

    @Override
    public int addCouponSourceProdCat(CouponSource couponSource) {
        return sqlTemplate.insert(getNameSpace("addCouponSourceProdCatIds"), couponSource);
    }

    @Override
    public List<ProductInfo> getProductsByCouponId(PageableCouponSource couponSource) {
        return sqlTemplate.selectList(getNameSpace("getProductsByCouponId"), couponSource);
    }

    @Override
    public Long getProductsByCouponIdCount(PageableCouponSource couponSource) {
        return sqlTemplate.selectOne(getNameSpace("getProductsByCouponIdCount"), couponSource);
    }

    @Override
    public List<Long> getUserIdsOfCreateDateGreaterThan(int days, String platformId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("days", days);
        return sqlTemplate.selectList(getNameSpace("getUserIdsOfCreateDateGreaterThan"), map);
    }

    @Override
    public List<Long> getUserIdsOfCreateDateLessThan(int days, String platformId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("days", days);
        return sqlTemplate.selectList(getNameSpace("getUserIdsOfCreateDateLessThan"), map);
    }

    @Override
    public List<Long> getUserIdsOfTotalCostGreaterThan(int rmb, String platformId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("rmb", rmb);
        return sqlTemplate.selectList(getNameSpace("getUserIdsOfTotalCostGreaterThan"), map);
    }

    @Override
    public List<Long> getUserIdsByTotalCostDESC(Long sendNumber, String platformId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("sendNumber", sendNumber);
        return sqlTemplate.selectList(getNameSpace("getUserIdsByTotalCostDESC"), map);
    }

    @Override
    public List<Long> getUserIdsOfShoppingTimeGreaterThan(int times, String platformId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("times", times);
        return sqlTemplate.selectList(getNameSpace("getUserIdsOfShoppingTimeGreaterThan"), map);
    }

    @Override
    public List<Long> getUserIdsByShoppingTimeDESC(Long sendNumber, String platformId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("sendNumber", sendNumber);
        return sqlTemplate.selectList(getNameSpace("getUserIdsByShoppingTimeDESC"), map);
    }

}
