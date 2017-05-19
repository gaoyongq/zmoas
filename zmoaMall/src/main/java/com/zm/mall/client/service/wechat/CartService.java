package com.zm.mall.client.service.wechat;


import com.zm.mall.domain.business.accountsUsers.ShippingAddress;
import com.zm.mall.domain.wechat.WeChat_cart;

import java.util.List;

public interface CartService {
    //查询购物车
    List<WeChat_cart> selectAllCart(String userCode);
    //查询购物车数量
    int selectAllCartNum(WeChat_cart cart);
    //增减购物车产品数量
    boolean updateCartCount(WeChat_cart cart);
    //新增购物车
    boolean addCart(WeChat_cart cart);
    //新增购物车检查
    boolean addCartCheck(WeChat_cart cart);
    //新增购物车检查后 加数量
    boolean addCartNum(WeChat_cart cart);
    //查询购物车中购买的产品
    List<WeChat_cart> selectBuyCart(String shopCartStr, String userCode);
    //查询收货地址信息
    ShippingAddress selectUserAddress(String userCode);
    //删除购物车中已经购买的产品
    int updateCartProduct(String cartIds);

    //查询购物车版面
    List<WeChat_cart> getWeChatCart(WeChat_cart carts);

}