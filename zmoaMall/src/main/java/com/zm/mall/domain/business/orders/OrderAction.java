package com.zm.mall.domain.business.orders;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/11.
 *  对订单进行操作的实体类
 */
public class OrderAction implements Serializable {
    private Long actionId;
    //订单id
    private String orderId;
    //订单号
    private String orderCode;
    //用户id
    private Integer userId;
    //用户名
    private String userName;
    //对订单进行操作的编号
    private String actionCode;
    //对订单进行操作的时间
    private Date actionDate;
    //对订单进行的操作-创建订单，支付订单，配货，发货，完成订单
    private String remark;
    private String pluteformid;

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
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