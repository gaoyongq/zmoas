package com.zm.mall.dao.system.impl;


import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.SonDistributionDao;
import com.zm.mall.domain.system.SonDistribution;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public class SonDistributionDaoImpl extends BaseDaoImpl<SonDistribution> implements SonDistributionDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.SonDistributionDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }


}
