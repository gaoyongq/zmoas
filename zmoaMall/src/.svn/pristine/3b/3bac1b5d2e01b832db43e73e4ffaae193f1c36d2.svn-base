package com.zm.mall.dao.business.accountsUsers.impl;

import com.zm.mall.dao.BackDao;
import com.zm.mall.dao.BackDaoImpl;
import com.zm.mall.dao.BaseDao;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.accountsUsers.ShippingAddressDao;
import com.zm.mall.domain.business.accountsUsers.ShippingAddress;
import com.zm.mall.domain.business.accountsUsers.Users;

import java.util.List;

/**
 * Created by lp on 2017/3/1.
 */
public class ShippingAddressDaoImpl extends BaseDaoImpl<ShippingAddress> implements ShippingAddressDao{

    private final static String NAMESPACE = "com.zm.mall.dao.business.accountsUsers.ShippingAddressDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }
    //微信 通过userID 查询 收货地址
    @Override
    public List<ShippingAddress> selectShopAddressByUserId(Users users) {
        return sqlTemplate.selectList(getNameSpace("selectShopAddressByUserId"), users);
    }
    //微信添加收货地址
    @Override
    public int addShopAddress(ShippingAddress shippingAddress) {
        return sqlTemplate.insert(getNameSpace("addShopAddress"), shippingAddress);
    }
    //微信删除收货地址
    @Override
    public int removeShopAddress(ShippingAddress shippingAddress) {
        return sqlTemplate.delete(getNameSpace("removeShopAddress"), shippingAddress);
    }

    @Override
    public int updateShippingAddress(ShippingAddress shippingAddress) {
        return sqlTemplate.update(getNameSpace("updateShippingAddress"), shippingAddress);
    }

    //微信设置默认收货地址
    @Override
    public int updateShopAddressState(ShippingAddress shippingAddress) {
        return sqlTemplate.update(getNameSpace("updateShopAddressState"), shippingAddress);
    }
    //微信取消所有默认收货地址
    @Override
    public int updateAllShopAddressState(ShippingAddress shippingAddress) {
        return sqlTemplate.update(getNameSpace("updateAllShopAddressState"), shippingAddress);
    }
    //微信查询默认收货地址
    @Override
    public ShippingAddress selectAddressState(ShippingAddress shippingAddress) {
        return sqlTemplate.selectOne(getNameSpace("selectAddressState"), shippingAddress);
    }
    //微信根据id查询收货地址信息
    @Override
    public ShippingAddress selectAddressOne(ShippingAddress shippingAddress) {
        return sqlTemplate.selectOne(getNameSpace("selectAddressOne"),shippingAddress);
    }

}
