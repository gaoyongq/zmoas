/**
 * 产品实体类
 */
package com.zm.mall.domain.business.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by lp on 2016/11/11.
 * 产品实体类
 */
public class ProductInfo {

    private List<AttributeInfo> attributeInfoList = new ArrayList<AttributeInfo>();//产品属性与规格
    private List<AttributeValue> attributeValueList = new ArrayList<AttributeValue>();
    private List<ProductAttribute> productAttributeList = new ArrayList<ProductAttribute>();
    private List<SKUInfo> skuInfoList = new ArrayList<SKUInfo>();
    private List<RelatedProduct> relatedProductList = new ArrayList<RelatedProduct>();
    private List<SupplierInfo> supplierInfoList = new ArrayList<SupplierInfo>();
    private List<ProductType> productTypeList = new ArrayList<ProductType>();
    private List<RegionInfo> RegionInfoList = new ArrayList<RegionInfo>();
    private List<BrandInfo> brandInfos = new ArrayList<BrandInfo>();
    private List<ProductImage> productImagelist =new ArrayList<ProductImage>();//图片   同步
    private List<ProductAccessorie> productAccessorieList = new ArrayList<ProductAccessorie>();
    private List<AccessoriesValue> accessoriesValueList = new ArrayList<AccessoriesValue>();
    private List<SKUItem> skuItemList = new ArrayList<SKUItem>();
    private List<SKURelation> skuRelationList = new ArrayList<SKURelation>();
    private List<ProductCategories> productCategoriesList = new ArrayList<ProductCategories>();
    private List<ProductStationModes> productStationModes = new ArrayList<ProductStationModes>();
    private List<ProductPackage> productPackage = new ArrayList<ProductPackage>();
    private List<FavoriteInfo> favorites = new ArrayList<FavoriteInfo>();
    private String[] Station;//推荐 热销属性  数组  用于添加
    private Integer stationType;//属性类型  用于查询
    private GroupBuy groupBuy = new GroupBuy();
    private String supplier;
    private String productType;
    private String brand;
    private String[] productCategories;
    private String relatedProductId;
    private List<Integer> packageId;
    private String searchProductCategories;
    private Integer categoryId;//分类id
    private Integer typeId;//类型id
    private Long productId;//商品id
    private Integer brandId;//品牌id
    private String productName;//商品名
    private String productCode;//商品编码
    private Integer supplierId;//商家
    private Integer regionId;//地址表id
    private String shortDescription;
    private String unit;//计量单位
    private String description;//详细
    private String meta_Title;
    private String meta_Description;
    private String meta_Keywords;
    private Integer saleStatus;
    private Date addedDate = new Date();//创建时间
    private Integer vistiCounts;//浏览
    private Integer saleCounts;
    private Integer stock;//库存
    private Integer displaySequence;//显示序列
    private Integer lineId;
    private Double marketPrice;
    private Double lowestSalePrice;//销售价
    private Double adjustedPrice;//预约金额
    private Integer penetrationStatus;
    private String mainCategoryPath;
    private String extendCategoryPath;
    private Boolean hasSku;
    private Double points = new Double(new Double(0));
    /*------ 旧版图片属性 Start ------*/    private String imageUrl;
    private String thumbnailUrl1;
    private String thumbnailUrl2;
    private String thumbnailUrl3;
    private String thumbnailUrl4;
    private String thumbnailUrl5;
    private String thumbnailUrl6;
    private String thumbnailUrl7;
    private String thumbnailUrl8;
    /*------ 旧版图片属性 End ------*/

    /*------ 新版图片属性 Start ------*/
    private List<String> imageUrls;
    /*------ 新版图片属性 End ------*/
    private Integer maxQuantity = new Integer(0);//最大量
    private Integer minQuantity = new Integer(0);//最小量
    private String tags;
    private String seoUrl;//搜索
    private String seoImageAlt;
    private String seoImageTitle;
    private Integer salesType = 1;
    private Integer restrictionCount;
    private String deliveryTip;
    private String remark;
    private String staticUrl;
    private Integer limitCount;
    private Double profit;//利润
    private String SpecStr;//属性
    private String skusStr;//规格
    private Long shopId;//店铺id
    private String pluteformid;


    public ProductInfo() {
    }

    public List<ProductCategories> getProductCategoriesList() {
        return productCategoriesList;
    }

    public void setProductCategoriesList(List<ProductCategories> productCategoriesList) {
        this.productCategoriesList = productCategoriesList;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<FavoriteInfo> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<FavoriteInfo> favorites) {
        this.favorites = favorites;
    }

    public List<ProductStationModes> getProductStationModes() {
        return productStationModes;
    }

    public void setProductStationModes(List<ProductStationModes> productStationModes) {
        this.productStationModes = productStationModes;
    }

    public Integer getStationType() {
        return stationType;
    }

    public void setStationType(Integer stationType) {
        this.stationType = stationType;
    }

    public String[] getStation() {
        return Station;
    }

    public void setStation(String[] station) {
        Station = station;
    }

    public GroupBuy getGroupBuy() {
        return groupBuy;
    }

    public void setGroupBuy(GroupBuy groupBuy) {
        this.groupBuy = groupBuy;
    }

    public List<ProductPackage> getProductPackage() {
        return productPackage;
    }

    public void setProductPackage(List<ProductPackage> productPackage) {
        this.productPackage = productPackage;
    }

    public List<AttributeInfo> getAttributeInfoList() {
        return attributeInfoList;
    }

    public void setAttributeInfoList(List<AttributeInfo> attributeInfoList) {
        this.attributeInfoList = attributeInfoList;
    }

    public List<AttributeValue> getAttributeValueList() {
        return attributeValueList;
    }

    public void setAttributeValueList(List<AttributeValue> attributeValueList) {
        this.attributeValueList = attributeValueList;
    }

    public List<ProductAttribute> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<ProductAttribute> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }

    public List<SKUInfo> getSkuInfoList() {
        return skuInfoList;
    }

    public void setSkuInfoList(List<SKUInfo> skuInfoList) {
        this.skuInfoList = skuInfoList;
    }

    public List<RelatedProduct> getRelatedProductList() {
        return relatedProductList;
    }

    public void setRelatedProductList(List<RelatedProduct> relatedProductList) {
        this.relatedProductList = relatedProductList;
    }

    public List<SupplierInfo> getSupplierInfoList() {
        return supplierInfoList;
    }

    public void setSupplierInfoList(List<SupplierInfo> supplierInfoList) {
        this.supplierInfoList = supplierInfoList;
    }

    public List<ProductType> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<ProductType> productTypeList) {
        this.productTypeList = productTypeList;
    }

    public List<RegionInfo> getRegionInfoList() {
        return RegionInfoList;
    }

    public void setRegionInfoList(List<RegionInfo> regionInfoList) {
        RegionInfoList = regionInfoList;
    }

    public List<BrandInfo> getBrandInfos() {
        return brandInfos;
    }

    public void setBrandInfos(List<BrandInfo> brandInfos) {
        this.brandInfos = brandInfos;
    }

    public List<ProductImage> getProductImagelist() {
        return productImagelist;
    }

    public void setProductImagelist(List<ProductImage> productImagelist) {
        this.productImagelist = productImagelist;
    }

    public List<ProductAccessorie> getProductAccessorieList() {
        return productAccessorieList;
    }

    public void setProductAccessorieList(List<ProductAccessorie> productAccessorieList) {
        this.productAccessorieList = productAccessorieList;
    }

    public List<AccessoriesValue> getAccessoriesValueList() {
        return accessoriesValueList;
    }

    public void setAccessoriesValueList(List<AccessoriesValue> accessoriesValueList) {
        this.accessoriesValueList = accessoriesValueList;
    }

    public List<SKUItem> getSkuItemList() {
        return skuItemList;
    }

    public void setSkuItemList(List<SKUItem> skuItemList) {
        this.skuItemList = skuItemList;
    }

    public List<SKURelation> getSkuRelationList() {
        return skuRelationList;
    }

    public void setSkuRelationList(List<SKURelation> skuRelationList) {
        this.skuRelationList = skuRelationList;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String[] getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(String[] productCategories) {
        this.productCategories = productCategories;
    }

    public String getRelatedProductId() {
        return relatedProductId;
    }

    public void setRelatedProductId(String relatedProductId) {
        this.relatedProductId = relatedProductId;
    }

    public List<Integer> getPackageId() {
        return packageId;
    }

    public void setPackageId(List<Integer> packageId) {
        this.packageId = packageId;
    }

    public String getSearchProductCategories() {
        return searchProductCategories;
    }

    public void setSearchProductCategories(String searchProductCategories) {
        this.searchProductCategories = searchProductCategories;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Integer getVistiCounts() {
        return vistiCounts;
    }

    public void setVistiCounts(Integer vistiCounts) {
        this.vistiCounts = vistiCounts;
    }

    public Integer getSaleCounts() {
        return saleCounts;
    }

    public void setSaleCounts(Integer saleCounts) {
        this.saleCounts = saleCounts;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getDisplaySequence() {
        return displaySequence;
    }

    public void setDisplaySequence(Integer displaySequence) {
        this.displaySequence = displaySequence;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getLowestSalePrice() {
        return lowestSalePrice;
    }

    public void setLowestSalePrice(Double lowestSalePrice) {
        this.lowestSalePrice = lowestSalePrice;
    }

    public Integer getPenetrationStatus() {
        return penetrationStatus;
    }

    public void setPenetrationStatus(Integer penetrationStatus) {
        this.penetrationStatus = penetrationStatus;
    }

    public String getMainCategoryPath() {
        return mainCategoryPath;
    }

    public void setMainCategoryPath(String mainCategoryPath) {
        this.mainCategoryPath = mainCategoryPath;
    }

    public String getExtendCategoryPath() {
        return extendCategoryPath;
    }

    public void setExtendCategoryPath(String extendCategoryPath) {
        this.extendCategoryPath = extendCategoryPath;
    }

    public Boolean getHasSku() {
        return hasSku;
    }

    public void setHasSku(Boolean hasSku) {
        this.hasSku = hasSku;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbnailUrl1() {
        return thumbnailUrl1;
    }

    public void setThumbnailUrl1(String thumbnailUrl1) {
        this.thumbnailUrl1 = thumbnailUrl1;
    }

    public String getThumbnailUrl2() {
        return thumbnailUrl2;
    }

    public void setThumbnailUrl2(String thumbnailUrl2) {
        this.thumbnailUrl2 = thumbnailUrl2;
    }

    public String getThumbnailUrl3() {
        return thumbnailUrl3;
    }

    public void setThumbnailUrl3(String thumbnailUrl3) {
        this.thumbnailUrl3 = thumbnailUrl3;
    }

    public String getThumbnailUrl4() {
        return thumbnailUrl4;
    }

    public void setThumbnailUrl4(String thumbnailUrl4) {
        this.thumbnailUrl4 = thumbnailUrl4;
    }

    public String getThumbnailUrl5() {
        return thumbnailUrl5;
    }

    public void setThumbnailUrl5(String thumbnailUrl5) {
        this.thumbnailUrl5 = thumbnailUrl5;
    }

    public String getThumbnailUrl6() {
        return thumbnailUrl6;
    }

    public void setThumbnailUrl6(String thumbnailUrl6) {
        this.thumbnailUrl6 = thumbnailUrl6;
    }

    public String getThumbnailUrl7() {
        return thumbnailUrl7;
    }

    public void setThumbnailUrl7(String thumbnailUrl7) {
        this.thumbnailUrl7 = thumbnailUrl7;
    }

    public String getThumbnailUrl8() {
        return thumbnailUrl8;
    }

    public void setThumbnailUrl8(String thumbnailUrl8) {
        this.thumbnailUrl8 = thumbnailUrl8;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public Integer getSalesType() {
        return salesType;
    }

    public void setSalesType(Integer salesType) {
        this.salesType = salesType;
    }

    public Integer getRestrictionCount() {
        return restrictionCount;
    }

    public void setRestrictionCount(Integer restrictionCount) {
        this.restrictionCount = restrictionCount;
    }

    public String getDeliveryTip() {
        return deliveryTip;
    }

    public void setDeliveryTip(String deliveryTip) {
        this.deliveryTip = deliveryTip;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStaticUrl() {
        return staticUrl;
    }

    public void setStaticUrl(String staticUrl) {
        this.staticUrl = staticUrl;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getSpecStr() {
        return SpecStr;
    }

    public void setSpecStr(String specStr) {
        SpecStr = specStr;
    }

    public String getSkusStr() {
        return skusStr;
    }

    public void setSkusStr(String skusStr) {
        this.skusStr = skusStr;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
    public Double getAdjustedPrice() {
        return adjustedPrice;
    }

    public void setAdjustedPrice(Double adjustedPrice) {
        this.adjustedPrice = adjustedPrice;
    }
}
