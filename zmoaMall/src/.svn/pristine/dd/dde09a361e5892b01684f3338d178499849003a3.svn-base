package com.zm.mall.service.system.impl;

import com.zm.mall.client.Page;
import com.zm.mall.client.service.system.MallConfigService;
import com.zm.mall.client.vo.system.MallConfigVo;
import com.zm.mall.dao.system.MallConfigDao;
import com.zm.mall.domain.business.shop.PageableMallInfo;
import com.zm.mall.domain.system.MallConfig;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
@Service("mallConfigService")
public class MallConfigServiceImpl implements MallConfigService {
	@Resource
	private MallConfigDao mallConfigDao;

	public EasyUIListResult<MallConfigVo> getAllMallInfo(PageableMallInfo mallInfo){
		Long total=mallConfigDao.getCount(mallInfo);
		List<MallConfigVo> mallInfos=mallConfigDao.getAllMallConfig(mallInfo);
		return new EasyUIListResult<MallConfigVo>(total,mallInfos);
	}

	public  MallConfigVo getMallInfo(Long id){
		return mallConfigDao.getMallInfoById(id);
	}

	public Integer updateStatus(MallConfigVo mall){
		Integer a=mallConfigDao.updateStatus(mall);
		return a;
	}
}
