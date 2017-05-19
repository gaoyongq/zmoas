package com.zm.mall.client.result.business.accounts;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/26.
 */
public class RegionExpressPriceResult implements Serializable {
    private Integer id;
    private Integer typId;
    private Integer provinceId;
    private Integer cityId;
    private Integer countyId;
    private Integer regionId;
    private Integer productId;
    private Integer productCategoryId;
    private Integer roleId;
    private Double  price;
    private String  memo;
    private Date    addTime;
    private Date    updateTime;
    private Integer addUserId;
    private Integer updateUserId;
    private Date    verTime;
    private String pluteformid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypId() {
        return typId;
    }

    public void setTypId(Integer typId) {
        this.typId = typId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getVerTime() {
        return verTime;
    }

    public void setVerTime(Date verTime) {
        this.verTime = verTime;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }

    public RegionExpressPriceResult(Integer id, Integer typId, Integer provinceId, Integer cityId, Integer countyId, Integer regionId, Integer productId, Integer productCategoryId, Integer roleId, Double price, String memo, Date addTime, Date updateTime, Integer addUserId, Integer updateUserId, Date verTime, String pluteformid) {
        this.id = id;
        this.typId = typId;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.countyId = countyId;
        this.regionId = regionId;
        this.productId = productId;
        this.productCategoryId = productCategoryId;
        this.roleId = roleId;
        this.price = price;
        this.memo = memo;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.addUserId = addUserId;
        this.updateUserId = updateUserId;
        this.verTime = verTime;
        this.pluteformid = pluteformid;
    }

    public RegionExpressPriceResult() {
    }
}
