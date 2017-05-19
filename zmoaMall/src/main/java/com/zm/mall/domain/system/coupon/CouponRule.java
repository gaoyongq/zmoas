package com.zm.mall.domain.system.coupon;


import com.zm.mall.domain.system.User;
import com.zm.mall.domain.system.systemCode.SystemCode;

import java.util.Date;

/**
 * 优惠券规则
 * Created by Bochao on 2017/3/2.
 */
public class CouponRule {
    private Long id;//'优惠券使用限制规则唯一自增id',
    private SystemCode ruleName;//'规则限制名称码表id。例如：1。金额的码表id。关联b2c_item_system_code表',
    private double value;//'限制规则值。例如：100。购物金额满100可用优惠券',
    private CouponSource couponSource;//'规则应用优惠券源的id',
    private String platformId;//'优惠券规则所属平台id',
    private Date createTime;//'记录创建时间',
    private Date updateTime;//'记录最后一次更新时间',
    private User createUser;//'记录创建者id'

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemCode getRuleName() {
        return ruleName;
    }

    public void setRuleName(SystemCode ruleName) {
        this.ruleName = ruleName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public CouponSource getCouponSource() {
        return couponSource;
    }

    public void setCouponSource(CouponSource couponSource) {
        this.couponSource = couponSource;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
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

    @Override
    public String toString() {
        return "CouponRule{" +
                "id=" + id +
                ", ruleName=" + ruleName +
                ", value=" + value +
                ", couponSource=" + couponSource +
                ", platformId='" + platformId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                '}';
    }
}
