package com.zm.mall.dao.wechat;

import com.zm.mall.domain.business.accountsUsers.ShippingAddress;
import com.zm.mall.domain.wechat.WeChat_cart;

import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */
public interface CartDao {
    List<WeChat_cart> selectAllCart(String userCode);
    Integer selectAllCartNum(WeChat_cart cart);
    boolean updateCartCount(WeChat_cart cart);
    boolean addCartCheck(WeChat_cart cart);
    boolean addCart(WeChat_cart cart);
    boolean addCartNum(WeChat_cart cart);
    List<WeChat_cart> selectBuyCart(String shopCartStr, String userCode);
    ShippingAddress  selectUserAddress(String userCode);
    int updateCartProduct(String cartIds);
    //查询购物车版面
    List<WeChat_cart> getWeChatCart(WeChat_cart carts);
}
