package com.zm.mall.dao.system;

import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.system.coupon.*;
import com.zm.mall.domain.system.coupon.Coupon;
import com.zm.mall.domain.system.coupon.CouponActivity;
import com.zm.mall.domain.system.coupon.CouponActivityCategory;

import java.util.List;

/**
 * 优惠券Dao接口
 * Created by Bochao on 2017/2/27.
 */
public interface CouponDao {

    List<ProductCategory> getProductCategoriesByParentId(String platformId, Long parentId);

    int addCouponActivityCategory(CouponActivityCategory couponActivityCategory);

    int addCouponActivity(CouponActivity couponActivity);

    int addCouponRule(CouponRule couponRule);

    int addCouponSource(CouponSource couponSource);

    int addCouponSourceProd(String platformId, Long couponSourceId, Long prodId);

    int addCouponSourceProdCat(String platformId, Long couponSourceId, Long prodCatId);

    int addCoupon(Coupon coupon);

    int addCouponWithUserIds(Coupon coupon);

    List<CouponActivityCategory> getCouponActivityCategories(String platformId, Long start, Long size);

    Long getCouponActivityCategoryCount(String platformId);

    List<CouponActivity> getCouponActivities(String platformId, Long start, Long size);

    Long getCouponActivityCount(String platformId);

    List<Product> getProductsByCategoryId(String platformId, Long categoryId);

    List<Long> getCategoryIdsByProductId(String platformId, Long productId);

    CouponSource getCouponSourceById(String platformId, Long couponId);

    List<CouponRuleResult> getCouponRuleResult(String platformId, Long couponCodeId, Long couponSourceId);

    CouponRule getCouponRule(CouponRule couponRule);

    int updateCouponRule(CouponRule couponRule);

    List<CouponSource> getCouponSources(String platformId, Long start, Long size);

    Long getCouponSourceCount(String platformId);

    List<Users> getUsers(String platformId, Long start, Long size);

    Long getUserCount(String platformId);

    List<Long> getUserIdsOfCreateDateGreaterThan(int days, String platformId);

    List<Long> getUserIdsOfCreateDateLessThan(int days, String platformId);

    List<Long> getUserIdsOfTotalCostGreaterThan(int rmb, String platformId);

    List<Long> getUserIdsByTotalCostDESC(Long sendNumber, String platformId);

    List<Long> getUserIdsOfShoppingTimeGreaterThan(int times, String platformId);

    List<Long> getUserIdsByShoppingTimeDESC(Long sendNumber, String platformId);

    List<Long> getUserIdsOfALL(String platformId);

    int updateSendNumber(CouponSource couponSource);

    int updateActivityStatus(String platformId, Long activityId, int status);

    int updateCouponSourceStatus(String platformId, Long couponId, int status);

    List<Coupon> getUserCoupons(String platformId, Long start, Long size);

    Long getUserCouponCount(String platformId);

    int increaseExchangeNumber(String platformId, Long couponSourceId, Integer number);

    int reduceUserRestPoint(String platformId, Long userId, int needPoint);

    Integer getUserRestPoints(String platformId, Long userId);

    List<CouponSource> getCouponSourcesByActivityId(String platformId, Long activityId);

    List<CouponActivity> getCouponActivitiesInProgress(String platformId);

    List<Coupon> getCouponsByUserId(String platformId, Long userId, Integer status);

    Long getParentCategoryId(Long shopId, String platformId, Long categoryId);

    List<Long> getCouponSourceProdCatIdsBySourceId(Long sourceId);

    List<Long> getCouponSourceProdIdsBySourceId(Long sourceId);

    CouponSource getCouponSourceDetailById(String platformId, Long id);

    List<CouponRule> getCouponRuleBySourceId(String platformId, Long sourceId);

    int useCoupon(String platformId, Long couponId, Long orderId);

    List<Product> getCouponProductsBySourceId(String platformId, Long id);

    List<ProductCategory> getCouponProductCatsBySourceId(String platformId, Long id);

    String getCouponImgUrlById(String platformId, Long id);

    String getActivityImgUrlById(String platformId, Long id);

    String getActivityIntroductionById(String platformId, Long id);

    int updateCouponStatus(String platformId, Long couponId, int status);

    List<Coupon> getCouponsByUserId(String platformId, Long userId, Integer status, Long start, Long size);

    int addCouponSourceProd(CouponSource couponSource);

    int addCouponSourceProdCat(CouponSource couponSource);

    /**
     * 分页查询指定优惠券可用产品
     * @param couponSource
     * @return
     */
    List<ProductInfo> getProductsByCouponId(PageableCouponSource couponSource);

    /**
     * 分页查询指定优惠券可用产品总数
     * @param couponSource
     * @return
     */
    Long getProductsByCouponIdCount(PageableCouponSource couponSource);

}
