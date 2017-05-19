package com.zm.mall.dao.wechat.impl;/**
 * Created by Administrator on 2017/1/11.
 */

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.wechat.CartDao;
import com.zm.mall.domain.business.accountsUsers.ShippingAddress;
import com.zm.mall.domain.wechat.WeChat_cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2017-01-11 9:23
 */
public class CartDaoImpl extends BaseDaoImpl<WeChat_cart> implements CartDao{
    private final static String NAMESPACE = "com.zm.mall.dao.wechat.CartDao.";

    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }


    /**
     * 查询所有购物车内容
     * @return
     */
    @Override
    public List<WeChat_cart> selectAllCart(String userCode ) {
        List<WeChat_cart>  allCartNum = sqlTemplate.selectList(getNameSpace("selectAllCart"),userCode);
        return allCartNum;
    }

    @Override
    public Integer selectAllCartNum(WeChat_cart cart) {
        Integer cartNum = sqlTemplate.selectOne(getNameSpace("selectAllCartNum"),cart);
        return cartNum;
    }
    @Override
    public boolean updateCartCount(WeChat_cart cart) {
        Integer result = sqlTemplate.update(getNameSpace("updateCartCount"), cart);
        if (result==1){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean addCartCheck(WeChat_cart cart) {
       /* Map map = new HashMap();
        map.put("skuId",cart.getSkuid());
        map.put("userCode",cart.getUserCode());*/
        Integer checkResult = sqlTemplate.selectOne(getNameSpace("addCartCheck"), cart);
        if(checkResult==0){
            return true;
        }else{
            //规格存在 只加数量
            return false;
        }
    }
    @Override
    public boolean addCart(WeChat_cart cart) {
        int result = sqlTemplate.insert(getNameSpace("addCart"),cart);
        if (result==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean addCartNum(WeChat_cart cart) {
        Integer result = sqlTemplate.update(getNameSpace("addCartNum"), cart);
        if (result==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<WeChat_cart> selectBuyCart(String shopCartStr,String userCode) {
        Map map = new HashMap();
        map.put("shopCartStr",shopCartStr);
        map.put("userCode",userCode);
        List<WeChat_cart>carts = sqlTemplate.selectList(getNameSpace("selectBuyCart"),map);
        return carts;
    }

    @Override
    public ShippingAddress selectUserAddress(String userCode) {
        ShippingAddress userInfo = sqlTemplate.selectOne(getNameSpace("selectUserAddress"),userCode);
        return userInfo;
    }

    @Override
    public int updateCartProduct(String cartIds) {
        int result = sqlTemplate.update(getNameSpace("updateCartProduct"),cartIds);
        return result;
    }

    @Override
    public List<WeChat_cart> getWeChatCart(WeChat_cart carts) {
        return sqlTemplate.selectList(getNameSpace("getWeChatCart"), carts);
    }
}
