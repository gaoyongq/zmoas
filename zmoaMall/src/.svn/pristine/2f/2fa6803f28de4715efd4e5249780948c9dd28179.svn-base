package com.zm.mall.domain.system.coupon;

import java.util.List;

/**
 * 优惠券兑换接口返回结果
 * Created by Bochao on 2017/3/7.
 */
public class ExchangeCouponResult<T> {
    private List<T> data;
    private String errorMsg;
    private String success;
    private int status;

    public ExchangeCouponResult() {
        this.errorMsg = null;
        this.success = "success";
        this.status = 200;
    }

    public ExchangeCouponResult(String errorMsg) {
        this.errorMsg = errorMsg;
        this.success = null;
        this.status = 500;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
