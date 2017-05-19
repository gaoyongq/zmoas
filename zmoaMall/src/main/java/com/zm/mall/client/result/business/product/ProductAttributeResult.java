package com.zm.mall.client.result.business.product;

/**
 * Created by lp on 2017/2/17.
 * 商品与类型关联表
 */
public class ProductAttributeResult {
    private Long productId;//商品id
    private Long attributeId;//属性id
    private Long valueId;//属性值id
    private String pluteformid;

    public ProductAttributeResult() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public Long getValueId() {
        return valueId;
    }

    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
