package com.zm.mall.domain.business.accountsUsers;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/7.
 */
public class BalanceDetails {

    private Integer journalNumber;
    private Integer userId;
    private Date tradeDate;
    private Integer tradeType;

    private Double income;
    private Double expenses;
    private Double balance;
    private Integer payer;
    private Integer payee;
    private String remark;
    private String pluteformid;

    public Integer getJournalNumber() {
        return journalNumber;
    }

    public void setJournalNumber(Integer journalNumber) {
        this.journalNumber = journalNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getPayer() {
        return payer;
    }

    public void setPayer(Integer payer) {
        this.payer = payer;
    }

    public Integer getPayee() {
        return payee;
    }

    public void setPayee(Integer payee) {
        this.payee = payee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
