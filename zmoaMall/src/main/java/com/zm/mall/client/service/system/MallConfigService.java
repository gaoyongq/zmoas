package com.zm.mall.client.service.system;

import com.zm.mall.client.Page;
import com.zm.mall.client.vo.system.MallConfigVo;
import com.zm.mall.domain.business.shop.PageableMallInfo;
import com.zm.mall.domain.system.MallConfig;
import com.zm.mall.domain.system.coupon.EasyUIListResult;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
public interface MallConfigService {
	//获取商城集合
	public EasyUIListResult<MallConfigVo> getAllMallInfo(PageableMallInfo mallInfo);
	//根据商城id获取商城信息
	public MallConfigVo getMallInfo(Long id);

	//	修改状态
	public Integer updateStatus(MallConfigVo mall);
}
