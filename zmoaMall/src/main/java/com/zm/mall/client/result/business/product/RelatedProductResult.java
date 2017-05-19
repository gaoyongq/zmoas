package com.zm.mall.client.result.business.product;

/**
 * Created by lp on 2016/11/11.
 */
public class RelatedProductResult {
    private Long relatedId;
    private Long productId;
    private String pluteformid;

    public RelatedProductResult() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
