package com.zm.mall.domain.business.product;

import java.util.List;

/**
 * Created by lp on 2016/11/11.
 * 产品的品牌
 */
public class BrandInfo {
    private Integer brandId;//品牌id
    private String brandName;//品牌名称
    private String brandSpell;//简称
    private String metaDescription;
    private String metaKeywords;
    private String logo;//图标url
    private String companyUrl;
    private String description;
    private Integer displaySequence;//显示顺序
    private String theme;
    private List<Integer> productTypeIdOrBrandsId;
    private List<Integer> productTypes;
    private Long shopId;//店铺id
    private String pluteformid;

    public BrandInfo() {
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandSpell() {
        return brandSpell;
    }

    public void setBrandSpell(String brandSpell) {
        this.brandSpell = brandSpell;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDisplaySequence() {
        return displaySequence;
    }

    public void setDisplaySequence(Integer displaySequence) {
        this.displaySequence = displaySequence;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<Integer> getProductTypeIdOrBrandsId() {
        return productTypeIdOrBrandsId;
    }

    public void setProductTypeIdOrBrandsId(List<Integer> productTypeIdOrBrandsId) {
        this.productTypeIdOrBrandsId = productTypeIdOrBrandsId;
    }

    public List<Integer> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<Integer> productTypes) {
        this.productTypes = productTypes;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
