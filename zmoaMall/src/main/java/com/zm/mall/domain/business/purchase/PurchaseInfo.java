package com.zm.mall.domain.business.purchase;

import com.zm.mall.domain.system.User;

import java.util.*;

/**
 * Created by Administrator on 2016/11/28.
 */
//采购单主表被采购单产品子表引用
public class PurchaseInfo {
	private Long purchaseId;                       //主键ID
	private  String purchaseCode;               //采购单编码
	private User user;                        //采购人
	private Date procurementTime;             //采购时间
	private Date updateTime;                  //修改时间
	private Double totalweights;               //采购总重量
	private Long totalnumber;               //采购总数量
	private Double  totalmoney;               //采购总金额
	private Integer state;                    //采购状态
	private Integer purchaser;                //采购人对应的ID
	private Long rackMoney;                   //玻璃架的钱
	private Long departmentId;                //小组
	private Set<PurchaseInfo> purchaseInfos = new HashSet<PurchaseInfo>();
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getRackMoney() {
		return rackMoney;
	}

	public void setRackMoney(Long rackMoney) {
		this.rackMoney = rackMoney;
	}

	private List<PurchaseItems> purchaseItemses=new ArrayList<PurchaseItems>();

	public List<PurchaseItems> getPurchaseItemses() {
		return purchaseItemses;
	}

	public void setPurchaseItemses(List<PurchaseItems> purchaseItemses) {
		this.purchaseItemses = purchaseItemses;
	}

	public Integer getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(Integer purchaser) {
		this.purchaser = purchaser;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Set<PurchaseInfo> getPurchaseInfos() {
		return purchaseInfos;
	}

	public void setPurchaseInfos(Set<PurchaseInfo> purchaseInfos) {
		this.purchaseInfos = purchaseInfos;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Double getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(Double totalmoney) {
		this.totalmoney = totalmoney;
	}

	public Long getTotalnumber() {
		return totalnumber;
	}

	public void setTotalnumber(Long totalnumber) {
		this.totalnumber = totalnumber;
	}

	public Double getTotalweights() {
		return totalweights;
	}

	public void setTotalweights(Double totalweights) {
		this.totalweights = totalweights;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getProcurementTime() {
		return procurementTime;
	}

	public void setProcurementTime(Date procurementTime) {
		this.procurementTime = procurementTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
}
