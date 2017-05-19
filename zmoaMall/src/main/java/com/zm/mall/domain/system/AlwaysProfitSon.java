package com.zm.mall.domain.system;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/11.
 */
//物流配送费用报销
public class AlwaysProfitSon implements Serializable {
    private Integer id;
    private String alwaysProfitSonCode;//自己编码
    private String alwaysProfitCode;//主表编码
    private String purchaseCode; //采购单编码
    private String orderCode; //订单编码
    private String orderNumber;//订单数量
    private String orderWeight;//订单重量
    private String purchaseNumber;//采购数量
    private String purchaseWeight;//采购重量
    private Double orderPrice;
    private Double purchasePrice;//采购价
    private Double grossProfit;//采购价

    public Double getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(Double grossProfit) {
        this.grossProfit = grossProfit;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getAlwaysProfitSonCode() {
        return alwaysProfitSonCode;
    }

    public void setAlwaysProfitSonCode(String alwaysProfitSonCode) {
        this.alwaysProfitSonCode = alwaysProfitSonCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlwaysProfitCode() {
        return alwaysProfitCode;
    }

    public void setAlwaysProfitCode(String alwaysProfitCode) {
        this.alwaysProfitCode = alwaysProfitCode;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(String orderWeight) {
        this.orderWeight = orderWeight;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public String getPurchaseWeight() {
        return purchaseWeight;
    }

    public void setPurchaseWeight(String purchaseWeight) {
        this.purchaseWeight = purchaseWeight;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
