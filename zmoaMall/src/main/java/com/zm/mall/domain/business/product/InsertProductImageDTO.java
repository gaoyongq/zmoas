package com.zm.mall.domain.business.product;

/**
 * Created by Bochao on 2017/5/12.
 */
public class InsertProductImageDTO {
    private String[] imageUrls;
    private Long productId;
    private Long shopId;
    private String platformId;

    public static InsertProductImageDTO newInstance() {
        return new InsertProductImageDTO();
    }

    public InsertProductImageDTO imageUrls(String[] imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }

    public InsertProductImageDTO productId(Long productId) {
        this.productId = productId;
        return this;
    }

    public InsertProductImageDTO shopId(Long shopId) {
        this.shopId = shopId;
        return this;
    }

    public InsertProductImageDTO platformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    public String[] getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String[] imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }
}
