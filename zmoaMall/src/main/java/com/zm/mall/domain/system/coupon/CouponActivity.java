package com.zm.mall.domain.system.coupon;

import com.zm.mall.domain.system.User;

import java.util.Date;

/**
 * 活动
 * Created by Bochao on 2017/3/2.
 */
public class CouponActivity {
    private Long id;//'活动唯一自增id',
    private String name;//'活动名称。例如：中秋节节日促销',
    private CouponActivityCategory category;//'活动所属活动分类id，关联b2c_shop_coupon_activity_category表',
    private String imgUrl;//'活动宣传图片链接相对路径',
    private String introduction;//'活动介绍',
    private Date startTime;//'活动开始时间',
    private Date endTime;//'活动结束时间',
    private Date createTime;//'记录创建时间',
    private Date updateTime;//'记录最后一次更新时间',
    private User createUser;//'记录创建者id',
    private int status;//'活动状态。-1代表活动已过期，0代表活动未启用，1代表活动已启用',
    private String platformId;//'活动所属平台id'

    //前端展现json参数createUserName
    public String getCreateUserName() {
        if (createUser == null) return null;
        return createUser.getName();
    }
    //前端展现json参数categoryName
    public String getCategoryName() {
        if (category == null) return null;
        return category.getName();
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

    public CouponActivityCategory getCategory() {
        return category;
    }

    public void setCategory(CouponActivityCategory category) {
        this.category = category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    @Override
    public String toString() {
        return "CouponActivity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", imgUrl='" + imgUrl + '\'' +
                ", introduction='" + introduction + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", status=" + status +
                ", platformId='" + platformId + '\'' +
                '}';
    }
}
