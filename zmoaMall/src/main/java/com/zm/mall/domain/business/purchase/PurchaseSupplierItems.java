package com.zm.mall.domain.business.purchase;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/12.
 */
public class PurchaseSupplierItems {
	private Integer PursupplierId;      //采购单供应商子表ID
	private Integer purchaseItemId; //引用采购单产品子表的ID
	private String purchaseItemCode;  //引用采购单产品子表的编码
	private  Integer SupplierId;     //供应商ID
	private String supplierName;      //供应商名字
	private Double costPrice;         //采购价
	private Long numbers;               //采购数量
	private Integer  Singleweight;     //根据供应商得知产品单片的重量
	private Integer totalWeight;       //采购重量
	private Long count;//库存
	private Double prices;//采购金额
	private Date suppTimes;

	public Integer getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Integer totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Double getPrices() {
		return prices;
	}

	public void setPrices(Double prices) {
		this.prices = prices;
	}

	public Date getSuppTimes() {
		return suppTimes;
	}

	public void setSuppTimes(Date suppTimes) {
		this.suppTimes = suppTimes;
	}

	public Integer getPursupplierId() {
		return PursupplierId;
	}

	public void setPursupplierId(Integer pursupplierId) {
		PursupplierId = pursupplierId;
	}

	public Integer getPurchaseItemId() {
		return purchaseItemId;
	}

	public void setPurchaseItemId(Integer purchaseItemId) {
		this.purchaseItemId = purchaseItemId;
	}

	public String getPurchaseItemCode() {
		return purchaseItemCode;
	}

	public void setPurchaseItemCode(String purchaseItemCode) {
		this.purchaseItemCode = purchaseItemCode;
	}

	public Integer getSupplierId() {
		return SupplierId;
	}

	public void setSupplierId(Integer supplierId) {
		SupplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Long getNumbers() {
		return numbers;
	}

	public void setNumbers(Long numbers) {
		this.numbers = numbers;
	}

	public Integer getSingleweight() {
		return Singleweight;
	}

	public void setSingleweight(Integer singleweight) {
		Singleweight = singleweight;
	}
}
