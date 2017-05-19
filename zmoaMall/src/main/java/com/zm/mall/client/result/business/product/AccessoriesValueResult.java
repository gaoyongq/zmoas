package com.zm.mall.client.result.business.product;

/**
 * Created by lp on 2017/2/17.
 */
public class AccessoriesValueResult {
    private Integer accessoriesValueId;
    private Integer accessoriesId;
    private String sku;
    private String pluteformid;

    public Integer getAccessoriesValueId() {
        return accessoriesValueId;
    }

    public void setAccessoriesValueId(Integer accessoriesValueId) {
        this.accessoriesValueId = accessoriesValueId;
    }

    public Integer getAccessoriesId() {
        return accessoriesId;
    }

    public void setAccessoriesId(Integer accessoriesId) {
        this.accessoriesId = accessoriesId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
