package com.zm.mall.dao.system.impl;


import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.DistributionDao;
import com.zm.mall.domain.system.Distribution;
import com.zm.mall.client.Page;

import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public class DistributionDaoImpl extends BaseDaoImpl<Distribution> implements DistributionDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.DistributionDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }


    @Override
    public void distributionSave(Distribution distribution) {
        sqlTemplate.insert(getNameSpace("insertDistribution"),distribution);
    }

    @Override
    public void distributionUpdate(Distribution distribution) {
        sqlTemplate.update(getNameSpace("distributionUpdate"),distribution);
    }

    @Override
    public void distributionAddStartTime(Distribution distribution) {
        sqlTemplate.update(getNameSpace("distributionAddStartTime"),distribution);
    }

    @Override
    public void distributionAddEndTime(Distribution distribution) {
        sqlTemplate.update(getNameSpace("distributionAddEndTime"),distribution);
    }

    @Override
    public List<Distribution> selectByLimit(Page page) {

        return sqlTemplate.selectList(getNameSpace("selectByLimit"), page);
    }

    @Override
    public List<Distribution> selectDistributionNoConsent(Page page) {
        return sqlTemplate.selectList(getNameSpace("selectDistributionNoConsent"), page);
    }
    //查询审批同意的小组数据
    @Override
    public List<Distribution> selectDistributionConsent(Page page) {
        return sqlTemplate.selectList(getNameSpace("selectDistributionConsent"), page);
    }
    //查询审批同意的所有数据
    @Override
    public List<Distribution> selectDistributionAllConsent(Page page) {
        return sqlTemplate.selectList(getNameSpace("selectDistributionAllConsent"), page);
    }

    @Override
    public Integer selectAllCount(Page page) {
        return sqlTemplate.selectOne(getNameSpace("selectAllCount"),page);
    }

    @Override
    public Integer selectDistributionNoConsentCount(Page page) {
        return sqlTemplate.selectOne(getNameSpace("selectDistributionNoConsentCount"),page);
    }
    //统计审批同意的小组数据
    @Override
    public Integer selectDistributionConsentCount(Page page) {
        return sqlTemplate.selectOne(getNameSpace("selectDistributionConsentCount"),page);
    }
    //统计审批同意的所有数据
    @Override
    public Integer selectDistributionAllConsentCount(Page page) {
        return sqlTemplate.selectOne(getNameSpace("selectDistributionAllConsentCount"),page);
    }

    @Override
    public Distribution selectOneDistribution(String distributionCode) {

        Distribution distribution  = sqlTemplate.selectOne(getNameSpace("selectOneDistribution"), distributionCode);
        return distribution;
    }

    @Override
    public void updateDistributionState(Distribution distribution) {
        sqlTemplate.update(getNameSpace("updateDistributionState"),distribution);
    }

    @Override
    public void updateDistributionNoState(Distribution distribution) {
        sqlTemplate.update(getNameSpace("updateDistributionNoState"),distribution);
    }

    //统计利润的方法
    @Override
    public List<Distribution> selectFinishTask() {
        return sqlTemplate.selectList(getNameSpace("selectFinishTask"));
    }

    @Override
    public List<Distribution> selectDistributionCar(String purchaseCode) {
        return sqlTemplate.selectList(getNameSpace("selectDistributionCar"),purchaseCode);
    }

    @Override
    public void updateDistributionCarState(String purchaseCode) {
        sqlTemplate.update(getNameSpace("updateDistributionCarState"),purchaseCode);
    }

    @Override
    public void distributionSaveLaterData(Distribution distribution) {
        sqlTemplate.update(getNameSpace("distributionSaveLaterData"),distribution);
    }


}
