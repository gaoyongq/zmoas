package com.zm.mall.domain.system;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/11.
 */
//物流配送费用报销
public class AlwaysProfit implements Serializable {
    private Integer id;
    private String alwaysProfitCode;//编码
    private String purchaseCode;//采购单编号

    private String alwaysRealNumber;//采购总数
    private String alwaysRealWeight;//采购总重
    private String alwaysOrderNumber;//总订单数量
    private String alwaysOrderWeight;//总订单重量
    private String alwaysOrderMoney;//订单总价
    private String alwaysRealMoney;//采购总价
    private String money;//预支差旅费
    private String realMoney;//真实物流费用
    private Double profit;
    private Double realProfit;//真实物流费用
    private Double alwaysWornMoney;//总破损费用
    private Double aWipeOffMoney;//抹零金额
    private Double aReceiveMoney;//实收金额


    public Double getaWipeOffMoney() {
        return aWipeOffMoney;
    }

    public void setaWipeOffMoney(Double aWipeOffMoney) {
        this.aWipeOffMoney = aWipeOffMoney;
    }

    public Double getaReceiveMoney() {
        return aReceiveMoney;
    }

    public void setaReceiveMoney(Double aReceiveMoney) {
        this.aReceiveMoney = aReceiveMoney;
    }

    public Double getAlwaysWornMoney() {
        return alwaysWornMoney;
    }

    public void setAlwaysWornMoney(Double alwaysWornMoney) {
        this.alwaysWornMoney = alwaysWornMoney;
    }

    public Double getRealProfit() {
        return realProfit;
    }

    public void setRealProfit(Double realProfit) {
        this.realProfit = realProfit;
    }

    public String getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(String realMoney) {
        this.realMoney = realMoney;
    }

    public String getAlwaysOrderNumber() {
        return alwaysOrderNumber;
    }

    public void setAlwaysOrderNumber(String alwaysOrderNumber) {
        this.alwaysOrderNumber = alwaysOrderNumber;
    }

    public String getAlwaysOrderWeight() {
        return alwaysOrderWeight;
    }

    public void setAlwaysOrderWeight(String alwaysOrderWeight) {
        this.alwaysOrderWeight = alwaysOrderWeight;
    }

    public String getAlwaysRealWeight() {
        return alwaysRealWeight;
    }

    public void setAlwaysRealWeight(String alwaysRealWeight) {
        this.alwaysRealWeight = alwaysRealWeight;
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

    public String getAlwaysRealNumber() {
        return alwaysRealNumber;
    }

    public void setAlwaysRealNumber(String alwaysRealNumber) {
        this.alwaysRealNumber = alwaysRealNumber;
    }

    public String getAlwaysOrderMoney() {
        return alwaysOrderMoney;
    }

    public void setAlwaysOrderMoney(String alwaysOrderMoney) {
        this.alwaysOrderMoney = alwaysOrderMoney;
    }

    public String getAlwaysRealMoney() {
        return alwaysRealMoney;
    }

    public void setAlwaysRealMoney(String alwaysRealMoney) {
        this.alwaysRealMoney = alwaysRealMoney;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
