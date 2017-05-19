package com.zm.mall.domain.business.accountsUsers;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/18.
 */
public class ShippingAddress implements Serializable{

    private MsRegions msRegions;
    private Integer shippingId;

    private Integer regionId;

    private Integer userId;

    private String shipName;

    private String address;

    private String zipcode;

    private String emailAddress;

    private String telPhone;

    private String celPhone;

    private Boolean isDefault;

    private String aliases;

    private Integer latitude;

    private Integer longitude;

    private Integer lineId;

    private Integer circleId;

    private Integer depotId;

    private String remark;

    private String pluteformid;

    private String state;

    private Users users;




    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public MsRegions getMsRegions() {
        return msRegions;
    }

    public void setMsRegions(MsRegions msRegions) {
        this.msRegions = msRegions;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getCelPhone() {
        return celPhone;
    }

    public void setCelPhone(String celPhone) {
        this.celPhone = celPhone;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getCircleId() {
        return circleId;
    }

    public void setCircleId(Integer circleId) {
        this.circleId = circleId;
    }

    public Integer getDepotId() {
        return depotId;
    }

    public void setDepotId(Integer depotId) {
        this.depotId = depotId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ShippingAddress(Integer shippingId, Integer regionId, Integer userId, String shipName, String address, String zipcode, String emailAddress, String telPhone, String celPhone, Boolean isDefault, String aliases, Integer latitude, Integer longitude, Integer lineId, Integer circleId, Integer depotId, String remark) {
        this.shippingId = shippingId;
        this.regionId = regionId;
        this.userId = userId;
        this.shipName = shipName;
        this.address = address;
        this.zipcode = zipcode;
        this.emailAddress = emailAddress;
        this.telPhone = telPhone;
        this.celPhone = celPhone;
        this.isDefault = isDefault;
        this.aliases = aliases;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lineId = lineId;
        this.circleId = circleId;
        this.depotId = depotId;
        this.remark = remark;
    }

    public ShippingAddress() {
    }
}
