package com.zm.mall.client.service.system;


import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.system.coupon.*;
import com.zm.mall.domain.system.systemCode.EasyUIResult;

import java.util.List;

/**
 * 优惠券Service接口
 * Created by Bochao on 2017/2/27.
 */
public interface CouponService {

    /**
     * 获取产品父分类下的所有子分类以及叶子分类下的所有商品，用于添加优惠券时限制应用商品
     * @param platformId
     * @param parentId
     * @return
     */
    List<ProductCategory> getProductCategoriesByParentId(String platformId, Long parentId);

    /**
     * 添加活动分类
     * @param couponActivityCategory
     * @return
     */
    int addCouponActivityCategory(CouponActivityCategory couponActivityCategory);

    /**
     * 获取活动分类EasyUIListResult
     * @param platformId
     * @param page
     * @param rows
     * @return
     */
    EasyUIListResult getCouponActivityCategories(String platformId, Long page, Long rows);

    /**
     * 获取活动分类List
     * @param platformId
     * @return
     */
    List<CouponActivityCategory> getCouponActivityCategoryList(String platformId);

    /**
     * 添加活动
     * @param couponActivity
     * @return
     */
    int addCouponActivity(CouponActivity couponActivity);

    /**
     * 获取活动List
     * @param platformId
     * @return
     */
    List<CouponActivity> getCouponActivityList(String platformId);

    /**
     * 获取活动EasyUIListResult
     * @param platformId
     * @param page
     * @param rows
     * @return
     */
    EasyUIListResult getCouponActivities(String platformId, Long page, Long rows);

    /**
     * 添加优惠券源信息
     * @param couponSource
     * @return
     */
    void addCouponSource(CouponSource couponSource);

    /**
     * 得到指定商品id的所有父辈分类id
     * @param platformId
     * @param productId
     * @return
     */
    List<Long> getCategoryIdsByProductId(String platformId, Long productId);

    Long getParentCategoryId(Long shopId, String platformId, Long categoryId);

    List<Long> getAncestorCategoryIds(Long shopId, String platformId, Long categoryId);

    List<Long> getAncestorCategoryIdsByProductId(Long shopId, String platformId, Long productId);

    /**
     * 通过id获取优惠券源
     * @param platformId
     * @param couponId
     * @return
     */
    CouponSource getCouponSourceById(String platformId, Long couponId);

    /**
     * 添加优惠券规则
     * @param couponRule
     * @return
     */
    int addCouponRule(CouponRule couponRule);

    /**
     * 获取优惠券规则页面展现结果EasyUIListResult
     * @param platformId
     * @param couponCodeId
     * @param couponSourceId
     * @return
     */
    EasyUIListResult getCouponRuleResult(String platformId, Long couponCodeId, Long couponSourceId);

    /**
     * 获取优惠券源信息列表
     * @param platformId
     * @param page
     * @param rows
     * @return
     */
    EasyUIListResult getCouponSources(String platformId, Long page, Long rows);

    /**
     * 获取所有用户信息
     * @param platformId
     * @param page
     * @param rows
     * @return
     */
    EasyUIListResult getUsers(String platformId, Long page, Long rows);

    /**
     * 向特定用户分发优惠券
     * @param userResult
     * @param couponSourceId
     * @param userIds
     * @return
     */
    EasyUIResult addCouponForUsers(UserResult userResult, Long couponSourceId, Long[] userIds);

    /**
     * 向预定义用户组分发优惠券
     * @param userResult
     * @param couponSourceId
     * @param userGroup
     * @param sendNumber
     * @return
     */
    EasyUIResult addCouponForUserGroup(UserResult userResult, Long couponSourceId, String userGroup, Long sendNumber);

    /**
     * 更新活动状态
     * @param platformId
     * @param activityId
     * @param status
     * @return
     */
    int updateActivityStatus(String platformId, Long activityId, int status);

    /**
     * 更新优惠券源状态
     * @param platformId
     * @param couponId
     * @param status
     * @return
     */
    int updateCouponSourceStatus(String platformId, Long couponId, int status);

    /**
     * 获取已发放的优惠券
     * @param platformId
     * @param page
     * @param rows
     * @return
     */
    EasyUIListResult getUserCoupons(String platformId, Long page, Long rows);

    /**
     * 兑换优惠券
     * @param userId
     * @param platformId
     * @param couponSourceId
     * @param number
     * @return
     */
    ExchangeCouponResult exchangeCoupon(Long userId, String platformId, Long couponSourceId, Integer number);

    /**
     * 获取指定活动id下的所有优惠券源
     * @param platformId
     * @param activityId
     * @return
     */
    List<CouponSource> getCouponSourcesByActivityId(String platformId, Long activityId);

    /**
     * 获取用户持有的优惠券
     * @param platformId
     * @param userId
     * @param status 0代表已使用，1代表未使用，-1代表已过期
     * @return
     */
    ExchangeCouponResult getCouponsByUserId(String platformId, Long userId, Integer status);

    /**
     * 获取正在进行中的获取列表
     * @param platformId
     * @return
     */
    List<CouponActivity>  getCouponActivitiesInProgress(String platformId);


    ExchangeCouponResult getUsableCouponsByProducts(String platformId, Long userId, String products, Long page, Long rows);

    ExchangeCouponResult useCoupon(String platformId, Long couponId, Long orderId);


    List<Product> getCouponProductsBySourceId(String platformId, Long id);

    List<ProductCategory> getCouponProductCatsBySourceId(String platformId, Long id);

    String getCouponImgUrlById(String platformId, Long id);

    String getActivityImgUrlById(String platformId, Long id);

    String getActivityIntroductionById(String platformId, Long id);

    /**
     * 修改用户优惠券状态
     * @param platformId 平台id
     * @param couponId 用户优惠券id
     * @param status 用户优惠券状态。0代表已使用，1代表未使用
     * @return
     */
    int updateCouponStatus(String platformId, Long couponId, int status);

    ExchangeCouponResult getCouponsByUserId(String platformId, Long userId, Integer status, Long page, Long rows);

    /**
     * 查询指定优惠券可用产品
     * @param couponSource
     * @return
     */
    EasyUIListResult<ProductInfo> getProductsByCouponId(PageableCouponSource couponSource);

}
