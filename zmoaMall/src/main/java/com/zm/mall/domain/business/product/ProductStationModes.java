package com.zm.mall.domain.business.product;

/**
 * Created by lp on 2016/12/30.
 */
public class ProductStationModes {
    private Integer stationId;
    private Long productId;
    private Integer displaySequence;
    private Integer type;//0:�Ƽ���Ʒ 1:������Ʒ 2:�ؼ���Ʒ 3:������Ʒ 4:������ҳ�Ƽ�
    private Long shopId;//����id  
  private String pluteformid;

    public ProductStationModes() {
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
