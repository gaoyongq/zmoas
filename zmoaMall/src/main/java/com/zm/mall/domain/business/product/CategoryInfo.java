/**
 * 商品类型的属性与规格
 */
package com.zm.mall.domain.business.product;

import javax.xml.bind.annotation.XmlType;

/**
 * Created by lp on 2016/11/11.
 * 产品的分类
 */
@XmlType(name = "CategoryInfo")
public class CategoryInfo {
    private Integer categoryId;//分类的id
    private String name;//分类字段
    private Integer displaySequence;//显示顺序
    private String meta_Title;
    private String meta_Description;
    private String meta_Keywords;
    private String description;//父id
    private Integer parentCategoryId;
    private Integer depth;
    private String path;
    private String rewriteName;
    private String skuPrefix;
    private Integer associatedProductType;
    private String imageUrl;
    private String notes1;
    private String notes2;
    private String notes3;
    private String notes4;
    private String notes5;
    private String theme;
    private Boolean hasChildren;
    private String seoUrl;
    private String seoImageAlt;
    private String seoImageTitle;
    private Boolean  status = true;//状态
    private String namePath;
    private Double priceChange;
    private Long shopId;//店铺id
    private String pluteformid;
    private Integer visible;

    public CategoryInfo() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDisplaySequence() {
        return displaySequence;
    }

    public void setDisplaySequence(Integer displaySequence) {
        this.displaySequence = displaySequence;
    }

    public String getMeta_Title() {
        return meta_Title;
    }

    public void setMeta_Title(String meta_Title) {
        this.meta_Title = meta_Title;
    }

    public String getMeta_Description() {
        return meta_Description;
    }

    public void setMeta_Description(String meta_Description) {
        this.meta_Description = meta_Description;
    }

    public String getMeta_Keywords() {
        return meta_Keywords;
    }

    public void setMeta_Keywords(String meta_Keywords) {
        this.meta_Keywords = meta_Keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRewriteName() {
        return rewriteName;
    }

    public void setRewriteName(String rewriteName) {
        this.rewriteName = rewriteName;
    }

    public String getSkuPrefix() {
        return skuPrefix;
    }

    public void setSkuPrefix(String skuPrefix) {
        this.skuPrefix = skuPrefix;
    }

    public Integer getAssociatedProductType() {
        return associatedProductType;
    }

    public void setAssociatedProductType(Integer associatedProductType) {
        this.associatedProductType = associatedProductType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNotes1() {
        return notes1;
    }

    public void setNotes1(String notes1) {
        this.notes1 = notes1;
    }

    public String getNotes2() {
        return notes2;
    }

    public void setNotes2(String notes2) {
        this.notes2 = notes2;
    }

    public String getNotes3() {
        return notes3;
    }

    public void setNotes3(String notes3) {
        this.notes3 = notes3;
    }

    public String getNotes4() {
        return notes4;
    }

    public void setNotes4(String notes4) {
        this.notes4 = notes4;
    }

    public String getNotes5() {
        return notes5;
    }

    public void setNotes5(String notes5) {
        this.notes5 = notes5;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

    public String getSeoImageAlt() {
        return seoImageAlt;
    }

    public void setSeoImageAlt(String seoImageAlt) {
        this.seoImageAlt = seoImageAlt;
    }

    public String getSeoImageTitle() {
        return seoImageTitle;
    }

    public void setSeoImageTitle(String seoImageTitle) {
        this.seoImageTitle = seoImageTitle;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNamePath() {
        return namePath;
    }

    public void setNamePath(String namePath) {
        this.namePath = namePath;
    }

    public Double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(Double priceChange) {
        this.priceChange = priceChange;
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

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
}
