package com.zm.mall.dao.system.impl;


import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.DistributionSonDao;
import com.zm.mall.domain.system.DistributionSon;

import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public class DistributionSonDaoImpl extends BaseDaoImpl<DistributionSon> implements DistributionSonDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.DistributionSonDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }


    @Override
    public void distributionSonSave(DistributionSon distributionSon) {
        sqlTemplate.insert(getNameSpace("insertDistributionSon"),distributionSon);
    }

    @Override
    public void distributionSonUpdate(DistributionSon distributionSon) {
        sqlTemplate.update(getNameSpace("distributionSonUpdate"),distributionSon);
    }

    @Override
    public List<DistributionSon> selectAllDistributionSon(String distributionCode) {
        return sqlTemplate.selectList(getNameSpace("selectAllDistributionSon"), distributionCode);
    }

    @Override
    public void distributionSonSaveLaterData(DistributionSon distributionSon) {
        sqlTemplate.update(getNameSpace("distributionSonSaveLaterData"),distributionSon);
    }
}
