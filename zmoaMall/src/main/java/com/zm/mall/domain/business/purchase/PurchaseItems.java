package com.zm.mall.domain.business.purchase;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/11/28.
 */
//采购单产品字表引用采购单主表，(采购单主表的ID和编码)
public class PurchaseItems {
	private Set<PurchaseSupplierItems> purchaseSupplierItemses=new HashSet<PurchaseSupplierItems>();
	private Set<PurchaseItems> purchaseItems=new HashSet<PurchaseItems>();
	private Integer ItemId;                //采购单子表
	private Long purchaseId;			   //引用采购单主表ID
	private String  purchaseCode;			   //引用采购单主表编码
	private Integer weight;                 //订单的产品重量
	private Long productId;                //产品ID
	private String  productCode;           //产品编码
	//private OrderItems orderItems;         //订单子表ID
	private  String sku;                    //产品规格
	private String orderId;             //订单号
	private String    orderCode;
	private String shipperName;
	private String shipAddress;
	private String shipCellPhone;
	private  String productName;
	private Integer number;
	private  String productStyles;

	private Integer orderState;
	private  Double productSell;        //订单中产品销售价
	private Double purchasePrice;       //针对某一产品的采购总价
	private Date itemTimes;
	private Integer purchaseN;          //针对某一产品的采购总数量
	private Integer purchaseW;          //针对某一产品的采购总重量
	
	/*=======================================================*/
	/*物流配送展示字段*/
	private  String kind;
	private Integer orderNumber;
	private Integer realNumber;
	private Integer realWeigth;
	private Integer orderWeigth;
	//统计利润需要字段
	private double orderMoney;
	private double realMoney;


	public Integer getOrderWeigth() {
		return orderWeigth;
	}

	public void setOrderWeigth(Integer orderWeigth) {
		this.orderWeigth = orderWeigth;
	}

	public Double getProductSell() {
		return productSell;
	}

	public void setProductSell(Double productSell) {
		this.productSell = productSell;
	}



	public Integer getPurchaseW() {
		return purchaseW;
	}

	public void setPurchaseW(Integer purchaseW) {
		this.purchaseW = purchaseW;
	}
	public double getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(double orderMoney) {
		this.orderMoney = orderMoney;
	}

	public Date getItemTimes() {
		return itemTimes;
	}

	public void setItemTimes(Date itemTimes) {
		this.itemTimes = itemTimes;
	}
	public double getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(double realMoney) {
		this.realMoney = realMoney;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Integer getRealWeigth() {
		return realWeigth;
	}

	public void setRealWeigth(Integer realWeigth) {
		this.realWeigth = realWeigth;
	}

	public Integer getRealNumber() {
		return realNumber;
	}

	public void setRealNumber(Integer realNumber) {
		this.realNumber = realNumber;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Set<PurchaseItems> getPurchaseItems() {
		return purchaseItems;
	}

	public void setPurchaseItems(Set<PurchaseItems> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}






	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Set<PurchaseSupplierItems> getPurchaseSupplierItemses() {
		return purchaseSupplierItemses;
	}

	public void setPurchaseSupplierItemses(Set<PurchaseSupplierItems> purchaseSupplierItemses) {
		this.purchaseSupplierItemses = purchaseSupplierItemses;
	}

	public Integer getItemId() {
		return ItemId;
	}

	public void setItemId(Integer itemId) {
		ItemId = itemId;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String  purchaseCode) {
		this.purchaseCode = purchaseCode;
	}


	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
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

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getShipCellPhone() {
		return shipCellPhone;
	}

	public void setShipCellPhone(String shipCellPhone) {
		this.shipCellPhone = shipCellPhone;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getProductStyles() {
		return productStyles;
	}

	public void setProductStyles(String productStyles) {
		this.productStyles = productStyles;
	}

	public Integer getPurchaseN() {
		return purchaseN;
	}

	public void setPurchaseN(Integer purchaseN) {
		this.purchaseN = purchaseN;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
}
