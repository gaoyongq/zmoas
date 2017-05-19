package com.zm.mall.client.result.business.product;

/**
 * Created by lp on 2016/12/30.
 */
public class ProductPackageResult {
    private Long productId;
    private Integer packageId;
    private String pluteformid;

    public ProductPackageResult() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
