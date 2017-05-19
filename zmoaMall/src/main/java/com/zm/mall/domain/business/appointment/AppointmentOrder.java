package com.zm.mall.domain.business.appointment;

import com.zm.mall.domain.business.orders.OrderAction;
import com.zm.mall.domain.business.orders.OrderItems;
import com.zm.mall.domain.business.product.ProductInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class AppointmentOrder implements Serializable{
	private OrderAction orderAction;
	private List<ProductInfo> productInfo = new ArrayList<ProductInfo>();
	//订单的详细信息
	private List<OrderItems> orderItems = new ArrayList<OrderItems>();
	private Integer currentPage;
	private Integer pageSize;
	private Integer begionNumber;
	//订单id
	private String orderId;
	//订单号
	private String orderCode;
	//订单产品的数量
	private Integer quantity;
	//
	private Long parentOrderId = (long) -1;
	//送货地址id
	private Long originalId;

	private Integer createUserId = -1;
	//订单创建时间
	private Date createdDate;
	//订单修改时间
	private Date updatedDate;
	//用户id
	private Integer buyerID;
	//用户名
	private String buyerName;
	//用户邮箱
	private String buyerEmail;
	//用户电话
	private String buyerCellPhone;
	//
	private Integer regionId;
	//收货人地区
	private String shipRegion;

	//收货人详细地址
	private String shipAddress;
	//收货人邮政编码
	private String shipZipCode;
	//收货人姓名
	private String shipName;
	//收货人手机号码
	private String shipTelPhone;
	//收货人电话号码
	private String shipCellPhone;
	//收货人邮箱
	private String shipEmail;
	//配送方式id
	private Integer shippingModeId;
	//配送方式
	private String shippingModeName;

	private Integer realShippingModeId;

	private String realShippingModeName;

	private Integer shipperId;

	private String shipperName;

	private String shipperAddress;

	private String shipperCellPhone;

	private Double freight;

	private Double freightAdjusted;

	private Double freightActual;

	private Integer weight;

	private Integer shippingStatus;

	private String shipOrderNumber;

	private String expressCompanyName;

	private String expressCompanyAbb;

	private Integer paymentTypeId;    //paymentTypeId  订单类型 0全额-线下  1全额-线上  2全额-余额  3定金-线上  4定金-余额

	//支付方式
	private String paymentTypeName;  //0预约

	private String paymentGateway;

	private Integer paymentStatus;

	private Integer refundStatus;

	private String payCurrencyCode;

	private String payCurrencyName;

	private Double paymentFee;

	private Double paymentFeeAdjusted;

	private String gatewayOrderId;

	private Double orderTotal;

	private Integer orderPoint;

	private Double orderCostPrice;  //用户支付定金金额

	private Double orderProfit;

	private Double orderOtherCost;

	private Double orderOptionPrice;

	private String discountName;

	private Double discountAmount;

	private Double discountAdjusted;

	private Double discountValue;

	private Integer discountValueType;

	private String couponCode;

	private String couponName;

	private Double couponAmount;

	private Double couponValue;

	private Integer couponValueType;

	private String activityName;

	private Double activityFreeAmount;

	private Integer activityStatus;

	private Integer groupBuyId;

	private Double groupBuyPrice;

	private Integer groupBuyStatus;

	private Double Amount;

	private Integer orderType = 1;

	private Integer orderTypeSub;

	private Integer orderStatus;  //是否已经付款  0代表未付   1代表已付 2已付定金

	private Integer sellerID;

	private String sellerName;

	private String sellerEmail;

	private String sellerCellPhone;

	private Integer commentStatus;

	private Integer supplierId;

	private String supplierName;

	private String referID;

	private String referURL;

	private Integer referType;

	private String orderIP;

	private String remark;

	private Double productTotal;

	private Boolean hasChildren;

	private Boolean isReviews;

	private Boolean isFreeShipping;

	private Boolean isTrueOrder;
	//新添加的字段
	//订单业务流程状态
	private Integer purchasingStatus;
	private Integer orderCheckStatus;
	private String shipProvince;
	private String shipCity;
	private String shipCounty;
	private Integer applyVehicleStatus;
	private Long departmentId;
	private Long roleId;

	private String departmentName;
	private String realName;
	private String pluteformid;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public OrderAction getOrderAction() {
		return orderAction;
	}

	public void setOrderAction(OrderAction orderAction) {
		this.orderAction = orderAction;
	}

	public String getPluteformid() {
		return pluteformid;
	}

	public void setPluteformid(String pluteformid) {
		this.pluteformid = pluteformid;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getApplyVehicleStatus() {
		return applyVehicleStatus;
	}

	public void setApplyVehicleStatus(Integer applyVehicleStatus) {
		this.applyVehicleStatus = applyVehicleStatus;
	}

	public String getShipCounty() {
		return shipCounty;
	}

	public void setShipCounty(String shipCounty) {
		this.shipCounty = shipCounty;
	}

	public String getShipCity() {
		return shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	public String getShipProvince() {
		return shipProvince;
	}

	public void setShipProvince(String shipProvince) {
		this.shipProvince = shipProvince;
	}

	public Integer getOrderCheckStatus() {
		return orderCheckStatus;
	}

	public void setOrderCheckStatus(Integer orderCheckStatus) {
		this.orderCheckStatus = orderCheckStatus;
	}

	public Integer getPurchasingStatus() {
		return purchasingStatus;
	}

	public void setPurchasingStatus(Integer purchasingStatus) {
		this.purchasingStatus = purchasingStatus;
	}

	public Boolean getIsTrueOrder() {
		return isTrueOrder;
	}

	public void setIsTrueOrder(Boolean isTrueOrder) {
		this.isTrueOrder = isTrueOrder;
	}

	public Boolean getIsFreeShipping() {
		return isFreeShipping;
	}

	public void setIsFreeShipping(Boolean isFreeShipping) {
		this.isFreeShipping = isFreeShipping;
	}

	public Boolean getIsReviews() {
		return isReviews;
	}

	public void setIsReviews(Boolean isReviews) {
		this.isReviews = isReviews;
	}

	public Boolean getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(Boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public Double getProductTotal() {
		return productTotal;
	}

	public void setProductTotal(Double productTotal) {
		this.productTotal = productTotal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderIP() {
		return orderIP;
	}

	public void setOrderIP(String orderIP) {
		this.orderIP = orderIP;
	}

	public Integer getReferType() {
		return referType;
	}

	public void setReferType(Integer referType) {
		this.referType = referType;
	}

	public String getReferID() {
		return referID;
	}

	public void setReferID(String referID) {
		this.referID = referID;
	}

	public String getReferURL() {
		return referURL;
	}

	public void setReferURL(String referURL) {
		this.referURL = referURL;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(Integer commentStatus) {
		this.commentStatus = commentStatus;
	}

	public String getSellerCellPhone() {
		return sellerCellPhone;
	}

	public void setSellerCellPhone(String sellerCellPhone) {
		this.sellerCellPhone = sellerCellPhone;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Integer getSellerID() {
		return sellerID;
	}

	public void setSellerID(Integer sellerID) {
		this.sellerID = sellerID;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderTypeSub() {
		return orderTypeSub;
	}

	public void setOrderTypeSub(Integer orderTypeSub) {
		this.orderTypeSub = orderTypeSub;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Double getAmount() {
		return Amount;
	}

	public void setAmount(Double amount) {
		Amount = amount;
	}

	public Integer getGroupBuyStatus() {
		return groupBuyStatus;
	}

	public void setGroupBuyStatus(Integer groupBuyStatus) {
		this.groupBuyStatus = groupBuyStatus;
	}

	public Double getGroupBuyPrice() {
		return groupBuyPrice;
	}

	public void setGroupBuyPrice(Double groupBuyPrice) {
		this.groupBuyPrice = groupBuyPrice;
	}

	public Integer getGroupBuyId() {
		return groupBuyId;
	}

	public void setGroupBuyId(Integer groupBuyId) {
		this.groupBuyId = groupBuyId;
	}

	public Integer getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Double getActivityFreeAmount() {
		return activityFreeAmount;
	}

	public void setActivityFreeAmount(Double activityFreeAmount) {
		this.activityFreeAmount = activityFreeAmount;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getCouponValueType() {
		return couponValueType;
	}

	public void setCouponValueType(Integer couponValueType) {
		this.couponValueType = couponValueType;
	}

	public Double getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(Double couponValue) {
		this.couponValue = couponValue;
	}

	public Double getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(Double couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Integer getDiscountValueType() {
		return discountValueType;
	}

	public void setDiscountValueType(Integer discountValueType) {
		this.discountValueType = discountValueType;
	}

	public Double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Double discountValue) {
		this.discountValue = discountValue;
	}

	public Double getDiscountAdjusted() {
		return discountAdjusted;
	}

	public void setDiscountAdjusted(Double discountAdjusted) {
		this.discountAdjusted = discountAdjusted;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public Double getOrderOptionPrice() {
		return orderOptionPrice;
	}

	public void setOrderOptionPrice(Double orderOptionPrice) {
		this.orderOptionPrice = orderOptionPrice;
	}

	public Double getOrderOtherCost() {
		return orderOtherCost;
	}

	public void setOrderOtherCost(Double orderOtherCost) {
		this.orderOtherCost = orderOtherCost;
	}

	public Double getOrderProfit() {
		return orderProfit;
	}

	public void setOrderProfit(Double orderProfit) {
		this.orderProfit = orderProfit;
	}

	public Double getOrderCostPrice() {
		return orderCostPrice;
	}

	public void setOrderCostPrice(Double orderCostPrice) {
		this.orderCostPrice = orderCostPrice;
	}

	public Integer getOrderPoint() {
		return orderPoint;
	}

	public void setOrderPoint(Integer orderPoint) {
		this.orderPoint = orderPoint;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getGatewayOrderId() {
		return gatewayOrderId;
	}

	public void setGatewayOrderId(String gatewayOrderId) {
		this.gatewayOrderId = gatewayOrderId;
	}

	public Double getPaymentFeeAdjusted() {
		return paymentFeeAdjusted;
	}

	public void setPaymentFeeAdjusted(Double paymentFeeAdjusted) {
		this.paymentFeeAdjusted = paymentFeeAdjusted;
	}

	public Double getPaymentFee() {
		return paymentFee;
	}

	public void setPaymentFee(Double paymentFee) {
		this.paymentFee = paymentFee;
	}

	public String getPayCurrencyName() {
		return payCurrencyName;
	}

	public void setPayCurrencyName(String payCurrencyName) {
		this.payCurrencyName = payCurrencyName;
	}

	public String getPayCurrencyCode() {
		return payCurrencyCode;
	}

	public void setPayCurrencyCode(String payCurrencyCode) {
		this.payCurrencyCode = payCurrencyCode;
	}

	public Integer getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(Integer shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public String getShipOrderNumber() {
		return shipOrderNumber;
	}

	public void setShipOrderNumber(String shipOrderNumber) {
		this.shipOrderNumber = shipOrderNumber;
	}

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	public String getExpressCompanyAbb() {
		return expressCompanyAbb;
	}

	public void setExpressCompanyAbb(String expressCompanyAbb) {
		this.expressCompanyAbb = expressCompanyAbb;
	}

	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public String getPaymentGateway() {
		return paymentGateway;
	}

	public void setPaymentGateway(String paymentGateway) {
		this.paymentGateway = paymentGateway;
	}

	public Double getFreightActual() {
		return freightActual;
	}

	public void setFreightActual(Double freightActual) {
		this.freightActual = freightActual;
	}

	public Double getFreightAdjusted() {
		return freightAdjusted;
	}

	public void setFreightAdjusted(Double freightAdjusted) {
		this.freightAdjusted = freightAdjusted;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public String getShipperCellPhone() {
		return shipperCellPhone;
	}

	public void setShipperCellPhone(String shipperCellPhone) {
		this.shipperCellPhone = shipperCellPhone;
	}

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public List<ProductInfo> getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(List<ProductInfo> productInfo) {
		this.productInfo = productInfo;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getBegionNumber() {
		return begionNumber;
	}

	public void setBegionNumber(Integer begionNumber) {
		this.begionNumber = begionNumber;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getParentOrderId() {
		return parentOrderId;
	}

	public void setParentOrderId(Long parentOrderId) {
		this.parentOrderId = parentOrderId;
	}

	public Long getOriginalId() {
		return originalId;
	}

	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(Integer buyerID) {
		this.buyerID = buyerID;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getBuyerCellPhone() {
		return buyerCellPhone;
	}

	public void setBuyerCellPhone(String buyerCellPhone) {
		this.buyerCellPhone = buyerCellPhone;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getShipRegion() {
		return shipRegion;
	}

	public void setShipRegion(String shipRegion) {
		this.shipRegion = shipRegion;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getShipZipCode() {
		return shipZipCode;
	}

	public void setShipZipCode(String shipZipCode) {
		this.shipZipCode = shipZipCode;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getShipTelPhone() {
		return shipTelPhone;
	}

	public void setShipTelPhone(String shipTelPhone) {
		this.shipTelPhone = shipTelPhone;
	}

	public String getShipCellPhone() {
		return shipCellPhone;
	}

	public void setShipCellPhone(String shipCellPhone) {
		this.shipCellPhone = shipCellPhone;
	}

	public String getShipEmail() {
		return shipEmail;
	}

	public void setShipEmail(String shipEmail) {
		this.shipEmail = shipEmail;
	}

	public Integer getShippingModeId() {
		return shippingModeId;
	}

	public void setShippingModeId(Integer shippingModeId) {
		this.shippingModeId = shippingModeId;
	}

	public String getShippingModeName() {
		return shippingModeName;
	}

	public void setShippingModeName(String shippingModeName) {
		this.shippingModeName = shippingModeName;
	}

	public Integer getRealShippingModeId() {
		return realShippingModeId;
	}

	public void setRealShippingModeId(Integer realShippingModeId) {
		this.realShippingModeId = realShippingModeId;
	}

	public String getRealShippingModeName() {
		return realShippingModeName;
	}

	public void setRealShippingModeName(String realShippingModeName) {
		this.realShippingModeName = realShippingModeName;
	}

	public Integer getShipperId() {
		return shipperId;
	}

	public void setShipperId(Integer shipperId) {
		this.shipperId = shipperId;
	}

	public String getShipperAddress() {
		return shipperAddress;
	}

	public void setShipperAddress(String shipperAddress) {
		this.shipperAddress = shipperAddress;
	}
}
