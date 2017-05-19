package com.zm.mall.client.result.business.product;

/**
 * Created by lp on 2016/12/30.
 */
public class ProductStationModesResult {
    private Integer stationId;
    private Long productId;
    private Integer displaySequence;
    private Integer type;
    private String pluteformid;

    public ProductStationModesResult() {
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getDisplaySequence() {
        return displaySequence;
    }

    public void setDisplaySequence(Integer displaySequence) {
        this.displaySequence = displaySequence;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
