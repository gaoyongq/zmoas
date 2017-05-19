package com.zm.mall.client.service.business.shop;

import com.zm.mall.domain.business.accountsUsers.Users;
import com.zm.mall.domain.business.product.SupplierInfo;
import com.zm.mall.domain.business.shop.PageableSupplierInfo;
import com.zm.mall.domain.business.shop.WeChatIdentity;
import com.zm.mall.domain.system.coupon.EasyUIListResult;

import java.util.List;

/**
 * Created by Bochao on 2017/4/13.
 */

public interface ShopInfoService {
    /**
     * 通过b2c_user表的id查询该用户的父平台id和openId
     * 用于将用户从b2c_user对应到b2c_accounts_users
     * @param userId
     * @return
     */
    WeChatIdentity getWeChatIdentityByUserId(Long userId);

    /**
     * 通过平台id和openId获取accountsUsers信息
     * @param weChatIdentity
     * @return
     */
    Users getUserByWeChatIdentity(WeChatIdentity weChatIdentity);

    /**
     * 添加店铺审核信息
     * @param supplierInfo
     */
    void addShopInfo(SupplierInfo supplierInfo);

    /**
     * 查询店铺信息列表
     * @param supplierInfo
     * @return
     */
    EasyUIListResult<SupplierInfo> getShopInfoList(PageableSupplierInfo supplierInfo);

    /**
     * 查询用户关注店铺信息列表
     * @param supplierInfo
     * @return
     */
    EasyUIListResult<SupplierInfo> getShopInfoListByAccountUser(PageableSupplierInfo supplierInfo, Users user);

    /**
     * 查询指定店铺信息
     * @param supplierInfo
     * @return
     */
    SupplierInfo getShopInfo(SupplierInfo supplierInfo);

    /**
     * 审核店铺
     * @param supplierInfo
     */
    void checkShop(SupplierInfo supplierInfo);
    //根据店铺主人查询
    SupplierInfo  getShopInfoByUserId(SupplierInfo supplierInfo);
    /**
     * 推荐店铺
     * @param supplierInfo
     */
    void recommendShop(SupplierInfo supplierInfo);


    //查询平台下的全部店铺
    List<SupplierInfo> selAllShop(SupplierInfo supplierInfo);
    //根据shopId查询店铺
    SupplierInfo selOneShop(SupplierInfo supplierInfo);
}
