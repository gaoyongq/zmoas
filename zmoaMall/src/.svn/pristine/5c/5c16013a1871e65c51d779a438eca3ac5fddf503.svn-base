package com.zm.mall.dao.business.product.impl;


import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.RegionInfoDao;
import com.zm.mall.domain.business.product.RegionInfo;

import java.util.List;

/**
 * Created by lp on 2016/11/21.
 */
public class RegionInfoDaoImpl extends BaseDaoImpl<RegionInfo> implements RegionInfoDao {

    private final static String NAMESPACE = "com.zm.mall.dao.business.product.RegionInfoDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public List<RegionInfo> selRegionInfoFirst() {
        return sqlTemplate.selectList(getNameSpace("selRegionInfoFirst"));
    }

    @Override
    public List<RegionInfo> selRegionInfoScound(RegionInfo regionInfo) {
        return sqlTemplate.selectList(getNameSpace("selRegionInfoScound"),regionInfo);
    }

    @Override
    public RegionInfo selRegionInfoPath(RegionInfo regionInfo) {
        return sqlTemplate.selectOne(getNameSpace("selRegionInfoPath"), regionInfo);
    }

    @Override
    public List<RegionInfo> regionInfos(RegionInfo regionInfo) {
        return sqlTemplate.selectList(getNameSpace("regionInfos"),regionInfo);
    }

    @Override
    public RegionInfo selectRegionId(RegionInfo regionInfo) {
        return sqlTemplate.selectOne(getNameSpace("selectRegionId"),regionInfo);
    }
}
