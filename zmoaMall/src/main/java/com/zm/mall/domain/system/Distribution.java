package com.zm.mall.domain.system;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/11.
 */
//物流配送费用报销
public class Distribution implements Serializable {
    private Integer id;
    private String distributionCode;//编码

    private String purchaseCode;//采购单编号
    private String smallgroup;//小组
    private String person;//配送人员
    private String number;//车牌号
    private String startTime;//起始时间
    private String endTime;//结束时间
    private String money;//预支差旅费
    private String truckFinishTime;//装车完毕时间
    private String state;// 1:没有审批   2：审批同意   3 审批不同意   4 :车已回库

    //抹零和实收金额
    private Double wipeOffMoney;
    private Double receiveMoney;

    //物流配送中多个合计属性
    private String allOrderNumber;
    private String allRealNumber;
    private String allRealWeigth;

    //表底人员
    private String approvePeople;
    private String groupLeader;
    private String ister;
    private String realDistributionMoney;

    //统计利润的字段

    private Double allOrderMoney;
    private Double allRealMoney;
    private Double profit;

    //部门id
    private int departmentId;

    public Double getWipeOffMoney() {
        return wipeOffMoney;
    }

    public void setWipeOffMoney(Double wipeOffMoney) {
        this.wipeOffMoney = wipeOffMoney;
    }

    public Double getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(Double receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getAllOrderMoney() {
        return allOrderMoney;
    }

    public void setAllOrderMoney(Double allOrderMoney) {
        this.allOrderMoney = allOrderMoney;
    }

    public Double getAllRealMoney() {
        return allRealMoney;
    }

    public void setAllRealMoney(Double allRealMoney) {
        this.allRealMoney = allRealMoney;
    }

    public String getApprovePeople() {
        return approvePeople;
    }

    public void setApprovePeople(String approvePeople) {
        this.approvePeople = approvePeople;
    }

    public String getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(String groupLeader) {
        this.groupLeader = groupLeader;
    }

    public String getIster() {
        return ister;
    }

    public void setIster(String ister) {
        this.ister = ister;
    }

    public String getAllOrderNumber() {
        return allOrderNumber;
    }

    public void setAllOrderNumber(String allOrderNumber) {
        this.allOrderNumber = allOrderNumber;
    }

    public String getAllRealNumber() {
        return allRealNumber;
    }

    public void setAllRealNumber(String allRealNumber) {
        this.allRealNumber = allRealNumber;
    }

    public String getAllRealWeigth() {
        return allRealWeigth;
    }

    public void setAllRealWeigth(String allRealWeigth) {
        this.allRealWeigth = allRealWeigth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistributionCode() {
        return distributionCode;
    }

    public void setDistributionCode(String distributionCode) {
        this.distributionCode = distributionCode;
    }

    public String getSmallgroup() {
        return smallgroup;
    }

    public void setSmallgroup(String smallgroup) {
        this.smallgroup = smallgroup;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTruckFinishTime() {
        return truckFinishTime;
    }

    public void setTruckFinishTime(String truckFinishTime) {
        this.truckFinishTime = truckFinishTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRealDistributionMoney() {
        return realDistributionMoney;
    }

    public void setRealDistributionMoney(String realDistributionMoney) {
        this.realDistributionMoney = realDistributionMoney;
    }
}
