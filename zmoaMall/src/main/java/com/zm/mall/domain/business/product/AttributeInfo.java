package com.zm.mall.domain.business.product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lp on 2016/11/11.
 * 产品的属性与规格
 */
public class AttributeInfo {
    private Long attributeId;//规格id
    private String attributeName;//规格名称
    private Integer displaySequence;
    private Integer typeId;//类型id
    private Integer usageMode;
    private Boolean useAttributeImage;
    private List<AttributeValue> listAttributeValues;
    private List<String> valueStr = new ArrayList<String>();
    private Boolean userDefinedPic;
    private Long shopId;//店铺id
    private String pluteformid;
    private Integer status;

    public AttributeInfo() {
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Integer getDisplaySequence() {
        return displaySequence;
    }

    public void setDisplaySequence(Integer displaySequence) {
        this.displaySequence = displaySequence;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getUsageMode() {
        return usageMode;
    }

    public void setUsageMode(Integer usageMode) {
        this.usageMode = usageMode;
    }

    public Boolean isUseAttributeImage() {
        return useAttributeImage;
    }

    public void setUseAttributeImage(Boolean useAttributeImage) {
        this.useAttributeImage = useAttributeImage;
    }

    public List<AttributeValue> getListAttributeValues() {
        return listAttributeValues;
    }

    public void setListAttributeValues(List<AttributeValue> listAttributeValues) {
        this.listAttributeValues = listAttributeValues;
    }

    public List<String> getValueStr() {
        return valueStr;
    }

    public void setValueStr(List<String> valueStr) {
        this.valueStr = valueStr;
    }

    public Boolean getUserDefinedPic() {
        return userDefinedPic;
    }

    public void setUserDefinedPic(Boolean userDefinedPic) {
        this.userDefinedPic = userDefinedPic;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
