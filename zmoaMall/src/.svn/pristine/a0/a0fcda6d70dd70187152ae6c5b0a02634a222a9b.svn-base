package com.zm.mall.domain.system.coupon;

import com.zm.mall.domain.system.User;

import java.util.Date;
import java.util.List;

/**
 * 优惠券
 * Created by Bochao on 2017/3/2.
 */
public class CouponSource {
    private Long id;//'优惠券源唯一自增id',
    private String name;//'优惠券源名称。例如：中秋节节日促销，全商品满100元可用',
    private CouponActivity activity;//'优惠券源所属活动id。关联b2c_shop_coupon_activity表',
    private double faceValue;//'优惠券源面值（元）',
    private double minimumValue; //最低消费金额（元），满足金额可用
    private int totalNumber;//'优惠券总共可发放数量',
    private int sendNumber;//'优惠券已发送数量',
    private int exchangeNumber;//'优惠券已兑换数量',
    private int type;//'优惠券类型。0代表普通优惠券(由商家主动发放给客户)，1代表兑换优惠券(由客户凭美豆积分兑换)',
    private int needPoint;//'兑换优惠券所需美豆积分值',
    private String imgUrl;//'优惠券源图片链接相对路径',
    private List<CouponRule> couponRuleList;//优惠券使用规则

    /**!!!
     * 如果productList和productCatList都为空，则优惠券应用于全部商品。
     * 如果有一项为空，另一项不为空。则优惠券只应用不为空列表规定的商品。
     * 如果全不为空，则优惠券分别应用于规定的商品。
     */
    private List<Product> productList;//优惠券应用商品列表
    private List<ProductCategory> productCatList;//优惠券应用商品分类列表

    private List<Long> prodIds;
    private List<Long> prodCatIds;

    private Date createTime;//'记录创建时间',
    private Date updateTime;//'记录最后一次更新时间',
    private User createUser;//'记录创建者id',
    private int status;//'优惠券源状态。0代表活动未启用，1代表活动已启用',
    private String platformId;//'优惠券源所属平台id'

    public String getRestNumber() {
        int restNumber = totalNumber - sendNumber;
        if (restNumber < 0) {
            return restNumber + "【已透支】";
        }
        return String.valueOf(restNumber);
    }

    public String getCouponRuleListString() {
        if (couponRuleList == null || couponRuleList.isEmpty()) return null;
        StringBuilder builder = new StringBuilder();
        for (CouponRule couponRule : couponRuleList) {
            builder.append(couponRule.getRuleName().getName());
            builder.append(":");
            builder.append(couponRule.getValue());
            builder.append(" | ");
        }
        return builder.toString();
    }

    public String getActivityName() {
        if (activity == null) return null;
        return activity.getName();
    }

    public int getExchangeNumber() {
        return exchangeNumber;
    }

    public void setExchangeNumber(int exchangeNumber) {
        this.exchangeNumber = exchangeNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getProdIds() {
        return prodIds;
    }

    public void setProdIds(List<Long> prodIds) {
        this.prodIds = prodIds;
    }

    public List<Long> getProdCatIds() {
        return prodCatIds;
    }

    public void setProdCatIds(List<Long> prodCatIds) {
        this.prodCatIds = prodCatIds;
    }

    public String getName() {
        return name;
    }

    public List<CouponRule> getCouponRuleList() {
        return couponRuleList;
    }

    public void setCouponRuleList(List<CouponRule> couponRuleList) {
        this.couponRuleList = couponRuleList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CouponActivity getActivity() {
        return activity;
    }

    public void setActivity(CouponActivity activity) {
        this.activity = activity;
    }

    public double getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(double faceValue) {
        this.faceValue = faceValue;
    }

    public double getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(double minimumValue) {
        this.minimumValue = minimumValue;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getSendNumber() {
        return sendNumber;
    }

    public void setSendNumber(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNeedPoint() {
        return needPoint;
    }

    public void setNeedPoint(int needPoint) {
        this.needPoint = needPoint;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<ProductCategory> getProductCatList() {
        return productCatList;
    }

    public void setProductCatList(List<ProductCategory> productCatList) {
        this.productCatList = productCatList;
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
