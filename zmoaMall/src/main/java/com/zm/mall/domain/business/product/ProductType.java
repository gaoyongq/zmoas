package com.zm.mall.domain.business.product;

import java.util.List;

/**
 * Created by lp on 2016/11/11.
 * 产品的类型
 */
public class ProductType {
    private Integer typeId;//类型id
    private String typeName;//类型名称
    private String remark;//详细内容
    private List<ProductTypeBrand> brandsTypes;//产品品牌
    private Long shopId;//店铺id
    private String pluteformid;

    public ProductType() {
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ProductTypeBrand> getBrandsTypes() {
        return brandsTypes;
    }

    public void setBrandsTypes(List<ProductTypeBrand> brandsTypes) {
        this.brandsTypes = brandsTypes;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
