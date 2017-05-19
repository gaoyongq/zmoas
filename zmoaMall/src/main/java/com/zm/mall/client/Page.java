package com.zm.mall.client;


import com.zm.mall.client.result.business.product.ProductInfoResult;
import com.zm.mall.domain.business.accountsUsers.TradingRecord;
import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.business.product.FavoriteInfo;
import com.zm.mall.domain.business.product.ProductInfo;
import com.zm.mall.domain.business.product.ProductType;
import com.zm.mall.domain.business.purchase.PurchaseInfo;
import com.zm.mall.domain.business.purchase.PurchaseItems;
import com.zm.mall.domain.system.*;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * 
 * @author liyanshuai
 * @since 2016-02-01
 * @param <T> 需要分页的实体
 * 
 * @modify liyanshuai(zhaobaoxin)
 * 
 */
public class Page<T> implements Serializable{
	
	private static final long serialVersionUID = -8895742541737938908L;
	/**
	 * 总数
	 */
	private int totalCount;
	/**
	 * 页大小
	 */
	private int pageSize = 10;
	/**
	 * 当前页
	 */
	private int currentPage =1;
	/**
	 * 总页数
	 */
	private int totalPages;
	
	/*下一页*/
	private int nextCurrentPage;
	/*上一页*/
	private int frontCurrentPage;
	//部门id
	private Long departmentId;
	private Long shopId;//店铺id
	private String pluteformid;
	private String openId;
	/**
	 * 内容对象
	 */
	private List<ProductInfoResult> resultProductInfo;
	private List<ItemSystemCode> resultItemSystemCode;
	private List<TruckManage> resultTruckManage;
	private List<PurchaseItems> resultPurchaseItems;
	private List<Distribution> resultDistribution;
	private List<AlwaysProfit> resultAlwaysProfit;
    private List<OrderInfo> resultOrderInfo;
    private List<ApplyCar> resultApplyCar;
	private List<Users> usersList;

	private Integer beginNumber;

	private ProductInfo productInfo = new ProductInfo();
	private ItemSystemCode itemSystemCode = new ItemSystemCode();
	private TruckManage truckManage = new TruckManage();
	private PurchaseInfo purchaseInfo = new PurchaseInfo();
	private ProductType productType = new ProductType();
	private List<ProductType> productTypes;
	private PurchaseItems purchaseItems = new PurchaseItems();
	private Distribution distribution = new Distribution();
	private AlwaysProfit alwaysProfit= new AlwaysProfit();
    private OrderInfo orderInfo = new OrderInfo();
    private ApplyCar applyCar = new ApplyCar();
	private FavoriteInfo favoriteInfo = new FavoriteInfo();
	private TradingRecord tradingRecord = new TradingRecord();
	private Users users;
	private Integer userId;
	public Page() {

	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public List<AlwaysProfit> getResultAlwaysProfit() {
		return resultAlwaysProfit;
	}

	public void setResultAlwaysProfit(List<AlwaysProfit> resultAlwaysProfit) {
		this.resultAlwaysProfit = resultAlwaysProfit;
	}

	public AlwaysProfit getAlwaysProfit() {
		return alwaysProfit;
	}

	public void setAlwaysProfit(AlwaysProfit alwaysProfit) {
		this.alwaysProfit = alwaysProfit;
	}

	public Distribution getDistribution() {
		return distribution;
	}

	public void setDistribution(Distribution distribution) {
		this.distribution = distribution;
	}

	public PurchaseItems getPurchaseItems() {
		return purchaseItems;
	}

	public void setPurchaseItems(PurchaseItems purchaseItems) {
		this.purchaseItems = purchaseItems;
	}

	public List<Distribution> getResultDistribution() {
		return resultDistribution;
	}

	public void setResultDistribution(List<Distribution> resultDistribution) {
		this.resultDistribution = resultDistribution;
	}

	public List<PurchaseItems> getResultPurchaseItems() {
		return resultPurchaseItems;
	}

	public void setResultPurchaseItems(List<PurchaseItems> resultPurchaseItems) {
		this.resultPurchaseItems = resultPurchaseItems;
	}

	public PurchaseInfo getPurchaseInfo() {
		return purchaseInfo;
	}

	public void setPurchaseInfo(PurchaseInfo purchaseInfo) {
		this.purchaseInfo = purchaseInfo;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<ProductInfoResult> getResultProductInfo() {
		return resultProductInfo;
	}

	public void setResultProductInfo(List<ProductInfoResult> resultProductInfo) {
		this.resultProductInfo = resultProductInfo;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public Integer getBeginNumber() {
		return beginNumber;
	}

	public void setBeginNumber(Integer beginNumber) {
		this.beginNumber = beginNumber;
	}

	
	public List<ItemSystemCode> getResultItemSystemCode() {
		return resultItemSystemCode;
	}

	public void setResultItemSystemCode(List<ItemSystemCode> resultItemSystemCode) {
		this.resultItemSystemCode = resultItemSystemCode;
	}
	
	public ItemSystemCode getItemSystemCode() {
		return itemSystemCode;
	}

	public void setItemSystemCode(ItemSystemCode itemSystemCode) {
		this.itemSystemCode = itemSystemCode;
	}
	public int getNextCurrentPage() {
		return currentPage+1;
	}

	public void setNextCurrentPage(int currentPage) {
		this.nextCurrentPage = currentPage+1;
	}


	public int getFrontCurrentPage() {
		return currentPage-1;
	}

	public void setFrontCurrentPage(int currentPage) {
		this.frontCurrentPage = currentPage-1;
	}

	//车辆get/set


	public List<TruckManage> getResultTruckManage() {
		return resultTruckManage;
	}

	public void setResultTruckManage(List<TruckManage> resultTruckManage) {
		this.resultTruckManage = resultTruckManage;
	}

	public TruckManage getTruckManage() {
		return truckManage;
	}

	public void setTruckManage(TruckManage truckManage) {
		this.truckManage = truckManage;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public List<ProductType> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}
	public List<OrderInfo> getResultOrderInfo() {
		return resultOrderInfo;
	}

	public void setResultOrderInfo(List<OrderInfo> resultOrderInfo) {
		this.resultOrderInfo = resultOrderInfo;
	}

	public List<ApplyCar> getResultApplyCar() {
		return resultApplyCar;
	}

	public void setResultApplyCar(List<ApplyCar> resultApplyCar) {
		this.resultApplyCar = resultApplyCar;
	}

	public ApplyCar getApplyCar() {
		return applyCar;
	}

	public void setApplyCar(ApplyCar applyCar) {
		this.applyCar = applyCar;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getPluteformid() {
		return pluteformid;
	}

	public void setPluteformid(String pluteformid) {
		this.pluteformid = pluteformid;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public List<Users> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}

	public FavoriteInfo getFavoriteInfo() {
		return favoriteInfo;
	}

	public void setFavoriteInfo(FavoriteInfo favoriteInfo) {
		this.favoriteInfo = favoriteInfo;
	}

	public TradingRecord getTradingRecord() {
		return tradingRecord;
	}

	public void setTradingRecord(TradingRecord tradingRecord) {
		this.tradingRecord = tradingRecord;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}


	@Override
	public String toString() {
		return "Page{" +
				"totalCount=" + totalCount +
				", pageSize=" + pageSize +
				", currentPage=" + currentPage +
				", totalPages=" + totalPages +
				", nextCurrentPage=" + nextCurrentPage +
				", frontCurrentPage=" + frontCurrentPage +
				", departmentId=" + departmentId +
				", pluteformid='" + pluteformid + '\'' +
				", openId='" + openId + '\'' +
				", resultProductInfo=" + resultProductInfo +
				", resultItemSystemCode=" + resultItemSystemCode +
				", resultTruckManage=" + resultTruckManage +
				", resultPurchaseItems=" + resultPurchaseItems +
				", resultDistribution=" + resultDistribution +
				", resultAlwaysProfit=" + resultAlwaysProfit +
				", resultOrderInfo=" + resultOrderInfo +
				", resultApplyCar=" + resultApplyCar +
				", usersList=" + usersList +
				", beginNumber=" + beginNumber +
				", productInfo=" + productInfo +
				", itemSystemCode=" + itemSystemCode +
				", truckManage=" + truckManage +
				", purchaseInfo=" + purchaseInfo +
				", productType=" + productType +
				", productTypes=" + productTypes +
				", purchaseItems=" + purchaseItems +
				", distribution=" + distribution +
				", alwaysProfit=" + alwaysProfit +
				", orderInfo=" + orderInfo +
				", applyCar=" + applyCar +
				", favoriteInfo=" + favoriteInfo +
				", tradingRecord=" + tradingRecord +
				", users=" + users +
				", userId=" + userId +
				'}';
	}
}