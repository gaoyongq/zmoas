package com.zm.mall.dao.system;


import com.zm.mall.client.vo.system.MallConfigVo;
import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.shop.PageableMallInfo;
import com.zm.mall.domain.system.MallConfig;

import java.util.List;

/**
 * Created by lp on 2017/2/22.
 */
public interface MallConfigDao extends BaseDao<MallConfig> {
    //审核状态
    Integer updateStatus(MallConfigVo mallInfo);

    Integer InsertMallConfig(MallConfig mallConfig);

    MallConfig selSecretkeyByAppId(MallConfig mallConfig);
    //查询所有商城
    List<MallConfigVo> getAllMallConfig(PageableMallInfo mallInfo);

    //获取商城数据总行数
    Long getCount(PageableMallInfo mallInfo);
    //根据编号获取商城
    MallConfigVo getMallInfoById(Long id);

    List<MallConfig> listMall(Long start, Long size);

    Long countMall();


}
