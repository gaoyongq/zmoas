package com.zm.mall.client.vo.business.product;

/**
 * Created by Administrator on 2016/12/2.
 */
public class SupplierBrandsVo {
    private Long supplierId;
    private Integer brandId;
    private String pluteformid;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
