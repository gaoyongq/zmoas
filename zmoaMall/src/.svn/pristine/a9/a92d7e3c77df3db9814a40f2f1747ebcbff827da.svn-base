package com.zm.mall.dao.business.shopUser.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.shopUser.ShopUserDao;
import com.zm.mall.domain.business.shopUser.ShopUser;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */
public class ShopUserDaoImpl extends BaseDaoImpl<ShopUser> implements ShopUserDao {
	private final static String NAMESPACE = "com.zm.mall.dao.business.shopUser.ShopUserDao.";
	@Override
	public String getNameSpace(String statement) {
		return NAMESPACE+statement;
	}

	@Override
	public Integer insertShopUser(ShopUser shopUser) {
		return sqlTemplate.insert(getNameSpace("insertShopUser"),shopUser);
	}

	@Override
	public List<ShopUser> selectShopUser(ShopUser shopUser) {
		return sqlTemplate.selectList(getNameSpace("selectByUserIdhShopId"),shopUser);
	}
}
