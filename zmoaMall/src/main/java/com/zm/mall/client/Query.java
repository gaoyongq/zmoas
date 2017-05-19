/**
 * File Name:Query.java
 * Package Name:com.jd.ecc.mall.center.domain
 * Date:2013-11-22下午5:51:36
 * Copyright (c) 2013, jd.com All Rights Reserved.
 *
 */
package com.zm.mall.client;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: Query
 * date: 2013-11-22 下午5:51:36
 * @author zhangjianyong
 * @version 
 * @since JDK 1.6
 * lastmodify 
 */
public class Query {
	//开始索引
    private int startIndex;
    
    //结束索引
    private int endIndex;
    
    //排序字段
    private String order;
    
    //排序类型
    private String orderType;
    
    //查询扩展
    private Map<String, Object> queryData;

	/**
	 * @return the startIndex
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * @return the endIndex
	 */
	public int getEndIndex() {
		return endIndex;
	}

	/**
	 * @param endIndex the endIndex to set
	 */
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the orderType
	 */
	public String getOrderType() {
        if("DESC".equalsIgnoreCase(orderType) || "ASC".equalsIgnoreCase(orderType)) {
            return orderType;
        }
        return null;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * @return the queryData
	 */
	public Map<String, Object> getQueryData() {
        if(queryData == null) {
            queryData = new HashMap<String, Object>();
        }
        return queryData;
	}

	/**
	 * @param queryData the queryData to set
	 */
    public void addQueryData(String key,Object value) {
        getQueryData().put(key, value);
    } 
}
