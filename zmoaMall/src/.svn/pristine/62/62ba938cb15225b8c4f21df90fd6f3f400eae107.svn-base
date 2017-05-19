package com.zm.mall.domain.system.coupon;

import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.system.User;

import java.util.Date;
import java.util.List;

/**
 * 用户优惠券
 * Created by Bochao on 2017/3/2.
 */
public class Coupon {
    private Long id;//'优惠券源唯一自增id',
    private String code;//'优惠券编码',
    private CouponSource source;//'优惠券来源优惠券源id。关联b2c_shop_coupon_source表',
    private Users user;//'优惠券持有用户id',
    private OrderInfo order;//'优惠券被使用的订单id',
    private Date usedTime;//'优惠券被使用日期，失效日期',
    private Date createTime;//'记录创建时间',
    private Date updateTime;//'记录最后一次更新时间',
    private User createUser;//'记录创建者id',
    private int status;//'优惠券源状态。0代表活动未启用，1代表活动已启用',
    private String platformId;//'优惠券源所属平台id'

    private List<Long> userIds;

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public String getSourceName() {
        if (source == null) return null;
        return source.getName();
    }

    public String getFaceValue() {
        if (source == null) return null;
        return String.valueOf(source.getFaceValue());
    }

    public String getSourceType() {
        if (source == null) return null;
        return String.valueOf(source.getType());
    }

    public String getUserName() {
        if (user == null) return null;
        return user.getUserName();
    }

    public String getOrderCode() {
        if (order == null) return null;
        return order.getOrderCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CouponSource getSource() {
        return source;
    }

    public void setSource(CouponSource source) {
        this.source = source;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public OrderInfo getOrder() {
        return order;
    }

    public void setOrder(OrderInfo order) {
        this.order = order;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }
}
