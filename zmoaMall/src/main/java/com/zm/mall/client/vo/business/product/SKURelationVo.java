package com.zm.mall.client.vo.business.product;

/**
 * Created by Administrator on 2016/11/25.
 */
public class SKURelationVo {
    private   Long  skuId;
    private   Long  specId;
    private   Long  productId;
    private String pluteformid;

    public SKURelationVo() {
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
