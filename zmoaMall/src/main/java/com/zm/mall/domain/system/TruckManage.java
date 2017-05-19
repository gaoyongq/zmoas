package com.zm.mall.domain.system;


import java.util.Date;

public class TruckManage {
	private Integer id ;
	private String number;
	private String capacity;
	private String life;
	private String driver;
	private String deliveryman;
	private Integer sortTime;
	private String remark;
	private String state;   // 0 : É¾³ý     1£º¿ÕÏÐ   2£ºµÈ´ý  3£ºÇë¼Ù  4£º·ÖÅä  5£ºÔËÐÐ

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getLife() {
		return life;
	}

	public void setLife(String life) {
		this.life = life;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDeliveryman() {
		return deliveryman;
	}

	public void setDeliveryman(String deliveryman) {
		this.deliveryman = deliveryman;
	}

	public Integer getSortTime() {
		return sortTime;
	}

	public void setSortTime(Integer sortTime) {
		this.sortTime = sortTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
