package com.zm.mall.domain.business.orders;

/**
 * 微信用户修改订单状态接参
 * Created by Bochao on 2017/5/2.
 */
public class UserOrderDto {
    private Long userId;//微信用户数据库自增id
    private String openId;//微信用户的openId
    private String platformId;//微信用户所属平台id
    private String orderCode;//订单编号
    private Integer status;//订单状态。0未发货、1已发货、2已完成
    private Integer shippingStatus;
    private Integer purchasingStatus;

    public Integer getShippingStatus() {
        return status;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public Integer getPurchasingStatus() {
        if (status == null) {
            return null;
        } else if (status == 0) {
            return -1;
        } else if (status == 1) {
            return 2;
        } else if (status == 2) {
            return 3;
        } else {
            return null;
        }
    }

    public void setPurchasingStatus(Integer purchasingStatus) {
        this.purchasingStatus = purchasingStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
