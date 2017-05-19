package com.zm.mall.domain.business.product;

import com.zm.mall.domain.business.accountsUsers.UserScore;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

/**
 * Created by lp on 2016/11/18.
 * 商家
 */
public class SupplierInfo {
    private Long supplierId;
    private String name; //商店名称
    private String userName;
    private Integer categoryId;//商家分类
    private Integer companyType;//公司性质
    private String artiPerson;//法人
    private Date establishedDate;//成立时间
    private Date createdDate;
    private Integer status;
    private Long registeredCapital;//注册资本
    private Double balance;//账户余额
    private String telPhone;//电话
    private String cellPhone;//手机
    private String contactMail;//联系邮箱
    private String address;//详细地址
    private String contact;//联系人
    private String fax;//传真
    private String postCode;//邮编
    private String homePage;//主页
    private Integer rank;//商家等级
    private String taxNumber;//税务登记
    private String accountBank;//开户银行
    private String accountInfo;//账号信息
    private String msn;//MSN
    private String introduction;//商家介绍
    private String reMark;//备注
    private Aaregionpriceimport aaregionpriceimport;
    private Aaregionpriceimport establishedPlace;
    private Integer establishedCity;
    private Integer regionId;//地区id
    private Set<BrandInfo> brandInfos;

    private Integer storeStatus;
    private Integer sequence;
    private Integer recomend;
    private String shopName;
    private Integer smallint;
    private Integer userId;
    private String logo;
    private String businessLicense;
    private String servicePhone;
    private String qq;
    private Integer createdUserId;
    private Date updatedDate;
    private Integer updatedUserId;
    private Date expirationDate;
    private Integer isUserApprove;
    private Integer isSuppApprove;
    private Double scoreDesc;
    private Double scoreService;
    private Double scoreSpeed;
    private Integer productCount;
    private Integer photoCount;
    private Integer themeId;
    private String remark;
    private Integer agentId;
    private Integer indexProdTop;
    private String indexContent;
    private Double latitude;
    private Double longitude;
    private String pluteformid;





    private UserScore userScore;//关于用户评分的表

    public Set<BrandInfo> getBrandInfos() {
        return brandInfos;
    }

    public void setBrandInfos(Set<BrandInfo> brandInfos) {
        this.brandInfos = brandInfos;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public String getArtiPerson() {
        return artiPerson;
    }

    public void setArtiPerson(String artiPerson) {
        this.artiPerson = artiPerson;
    }

    public Date getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(Date establishedDate) {
        this.establishedDate = establishedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(Long registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(String accountInfo) {
        this.accountInfo = accountInfo;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getReMark() {
        return reMark;
    }

    public void setReMark(String reMark) {
        this.reMark = reMark;
    }

    public Aaregionpriceimport getAaregionpriceimport() {
        return aaregionpriceimport;
    }

    public void setAaregionpriceimport(Aaregionpriceimport aaregionpriceimport) {
        this.aaregionpriceimport = aaregionpriceimport;
    }

    public Aaregionpriceimport getEstablishedPlace() {
        return establishedPlace;
    }

    public void setEstablishedPlace(Aaregionpriceimport establishedPlace) {
        this.establishedPlace = establishedPlace;
    }

    public Integer getEstablishedCity() {
        return establishedCity;
    }

    public void setEstablishedCity(Integer establishedCity) {
        this.establishedCity = establishedCity;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public UserScore getUserScore() {
        return userScore;
    }

    public void setUserScore(UserScore userScore) {
        this.userScore = userScore;
    }


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getSmallint() {
        return smallint;
    }

    public void setSmallint(Integer smallint) {
        this.smallint = smallint;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Integer createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(Integer updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getIsUserApprove() {
        return isUserApprove;
    }

    public void setIsUserApprove(Integer isUserApprove) {
        this.isUserApprove = isUserApprove;
    }

    public Integer getIsSuppApprove() {
        return isSuppApprove;
    }

    public void setIsSuppApprove(Integer isSuppApprove) {
        this.isSuppApprove = isSuppApprove;
    }

    public Double getScoreDesc() {
        return scoreDesc;
    }

    public void setScoreDesc(Double scoreDesc) {
        this.scoreDesc = scoreDesc;
    }

    public Double getScoreService() {
        return scoreService;
    }

    public void setScoreService(Double scoreService) {
        this.scoreService = scoreService;
    }

    public Double getScoreSpeed() {
        return scoreSpeed;
    }

    public void setScoreSpeed(Double scoreSpeed) {
        this.scoreSpeed = scoreSpeed;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getIndexProdTop() {
        return indexProdTop;
    }

    public void setIndexProdTop(Integer indexProdTop) {
        this.indexProdTop = indexProdTop;
    }

    public String getIndexContent() {
        return indexContent;
    }

    public void setIndexContent(String indexContent) {
        this.indexContent = indexContent;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(Integer storeStatus) {
        this.storeStatus = storeStatus;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getRecomend() {
        return recomend;
    }

    public void setRecomend(Integer recomend) {
        this.recomend = recomend;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
