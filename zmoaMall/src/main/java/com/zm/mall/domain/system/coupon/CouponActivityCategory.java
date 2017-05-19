package com.zm.mall.domain.system.coupon;

import com.zm.mall.domain.system.User;

import java.util.Date;

/**
 * 活动分类
 * Created by Bochao on 2017/3/2.
 */
public class CouponActivityCategory {
    private Long id;//'优惠券活动分类唯一自增id',
    private String name;//'活动分类名称。例如：节日促销',
    private String platformId;//'活动分类所属平台id',
    private Date createTime;//'记录创建时间',
    private Date updateTime;//'记录最后一次更新时间',
    private User createUser;//'记录创建者id',
    private int status;//'活动分类状态。0代表分类未启用，1代表分类已启用'

    //前端展现json参数createUserName
    public String getCreateUserName() {
        if (createUser == null) return null;
        return createUser.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CouponActivityCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", platformId='" + platformId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", status=" + status +
                '}';
    }
}
