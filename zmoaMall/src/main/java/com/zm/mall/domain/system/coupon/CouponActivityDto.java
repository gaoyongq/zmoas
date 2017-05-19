package com.zm.mall.domain.system.coupon;

/**
 * 通过活动id查询优惠券接口接参对象
 * Created by Bochao on 2017/4/27.
 */
public class CouponActivityDto {
    private Long activityId;
    private String platformId;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }
}
