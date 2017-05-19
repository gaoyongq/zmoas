package com.zm.mall.dao.business.shopUser;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.shopUser.ShopUser;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */
public interface ShopUserDao extends BaseDao<ShopUser> {
	//根据userId和shopInfoId查询中间关联表
	public List<ShopUser> selectShopUser(ShopUser shopUser);
	//增加店铺和用户关联表
	public  Integer insertShopUser(ShopUser shopUser);
}
