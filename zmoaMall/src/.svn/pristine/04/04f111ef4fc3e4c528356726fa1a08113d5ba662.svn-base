package com.zm.mall.service.business.wechat;/**
 * Created by Administrator on 2017/1/11.
 */

import com.zm.mall.client.service.wechat.CartService;
import com.zm.mall.dao.wechat.CartDao;
import com.zm.mall.domain.business.accountsUsers.ShippingAddress;
import com.zm.mall.domain.wechat.WeChat_cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @create 2017-01-11 9:19
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
    //private static Log log = LogFactory.getLog(CartService.class);
    @Autowired
    private CartDao cartDao;

    @Override
    public List<WeChat_cart> selectAllCart(String userCode) {
        List<WeChat_cart> allCart = cartDao.selectAllCart(userCode);
        return allCart;
    }

    @Override
    public int selectAllCartNum(WeChat_cart cart) {
        int cartNum = cartDao.selectAllCartNum(cart);
        return cartNum;
    }

    @Override
    public boolean updateCartCount(WeChat_cart cart) {
        boolean result = cartDao.updateCartCount(cart);
        return result;
    }

    @Override
    public boolean addCart(WeChat_cart cart) {
        boolean result = cartDao.addCart(cart);
        return result;
    }

    @Override
    public boolean addCartCheck(WeChat_cart cart) {
        boolean checkResult = cartDao.addCartCheck(cart);
        return checkResult;
    }

    @Override
    public boolean addCartNum(WeChat_cart cart) {
        boolean addCartNumResult = cartDao.addCartNum(cart);
        return addCartNumResult;
    }

    @Override
    public List<WeChat_cart> selectBuyCart(String shopCartStr,String userCode) {
        List<WeChat_cart>carts = cartDao.selectBuyCart(shopCartStr,userCode);
        return carts;
    }

    @Override
    public ShippingAddress selectUserAddress(String userCode) {
        ShippingAddress address = cartDao.selectUserAddress(userCode);
        return address;
    }

    @Override
    public int updateCartProduct(String cartIds) {
        int result = cartDao.updateCartProduct(cartIds);
        return result;
    }

    @Override
    public List<WeChat_cart> getWeChatCart(WeChat_cart carts) {
        return cartDao.getWeChatCart(carts);
    }
}
