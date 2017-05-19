package com.zm.mall.domain.business.orders;

import java.io.Serializable;

/**
 * 封装订单查询条件
 * Created by Administrator on 2016/11/14.
 */
public class OrderCheck implements Serializable{
    private String  orderCode;
    private String  shipName;
    private String  buyerName;
    private String pluteformid;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }

    public OrderCheck(String orderCode, String shipName, String buyerName) {
        this.orderCode = orderCode;
        this.shipName = shipName;
        this.buyerName = buyerName;
    }

    public OrderCheck() {
    }
}
