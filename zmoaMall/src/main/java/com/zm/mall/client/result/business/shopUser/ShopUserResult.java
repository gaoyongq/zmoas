package com.zm.mall.client.result.business.shopUser;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/14.
 */
public class ShopUserResult {
	private Integer shopUserId;
	private String pluteformid;
	private Long shopInfoId;
	private Integer userId;
	private Date createTime;
	private String openId;
	public Integer getShopUserId() {
		return shopUserId;
	}

	public void setShopUserId(Integer shopUserId) {
		this.shopUserId = shopUserId;
	}

	public Long getShopInfoId() {
		return shopInfoId;
	}

	public void setShopInfoId(Long shopInfoId) {
		this.shopInfoId = shopInfoId;
	}

	public String getPluteformid() {
		return pluteformid;
	}

	public void setPluteformid(String pluteformid) {
		this.pluteformid = pluteformid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
