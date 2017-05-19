package com.zm.mall.client.service.business.shopUser;

import com.zm.mall.client.Page;
import com.zm.mall.client.vo.business.shopUser.ShopUserVo;
import com.zm.mall.domain.business.shopUser.ShopUser;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */
public interface ShopUserService {
	//根据userId和shopInfoId查询中间关联表
	public List<ShopUser> selectShopUser(ShopUserVo shopUserVo);
	//增加店铺和用户关联表
	public  Integer insertShopUser(ShopUserVo shopUserVo);
	/**
	 * 查询店铺的下的产品
	 * 根据平台Id和openId查询用户是否关注店铺
	 * 关注返回最后时间关注店铺产品   没有关注返回该平台产品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page selectShopProductBySupplier(Page page)throws Exception;
	//查询单一个店铺的产品
	public Page selectShopProductByshopId(Page page)throws  Exception;
}
