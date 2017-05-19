package com.zm.mall.domain.system.coupon;

import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.system.User;

import java.util.Date;

/**
 * Created by Bochao on 2017/3/2.
 */
public class CouponResult {
    private Long id;//'优惠券源唯一自增id',
    private String code;//'优惠券编码',

    public CouponResult(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
