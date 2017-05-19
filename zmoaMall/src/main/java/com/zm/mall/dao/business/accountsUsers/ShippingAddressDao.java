package com.zm.mall.dao.business.accountsUsers;

import com.zm.mall.dao.BackDao;
import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.accountsUsers.ShippingAddress;
import com.zm.mall.domain.business.accountsUsers.Users;

import java.util.List;

/**
 * Created by lp on 2017/3/1.
 */
public interface ShippingAddressDao extends BaseDao<ShippingAddress> {
    //微信 通过userID 查询 收货地址
    public List<ShippingAddress> selectShopAddressByUserId(Users users);
    //微信添加收货地址
    public int addShopAddress(ShippingAddress shippingAddress);
    //微信删除收货地址
    public int removeShopAddress(ShippingAddress shippingAddress);
    //修改地址
    public int updateShippingAddress(ShippingAddress shippingAddress);
    //微信设置默认收货地址
    public int updateShopAddressState(ShippingAddress shippingAddress);
    //微信取消所有默认收货地址
    public int updateAllShopAddressState(ShippingAddress shippingAddress);
    //微信查询默认收货地址
    public ShippingAddress selectAddressState(ShippingAddress shippingAddress);
    //微信根据id查询收货地址信息
    public ShippingAddress selectAddressOne(ShippingAddress shippingAddress );
}
