package com.zm.mall.client.vo.business.product;

import com.zm.mall.domain.business.product.ProductTypeBrand;

import java.util.List;

/**
 * Created by lp on 2016/11/11.
 * 产品的类型
 */
public class ProductTypeVo {
    private Integer typeId;//类型id
    private String typeName;//类型名称
    private String remark;//详细内容
    private List<ProductTypeBrand> brandsTypes;//产品品牌
    private String pluteformid;

    public ProductTypeVo() {
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

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
