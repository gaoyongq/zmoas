package com.zm.mall.dao.system.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.AlwaysProfitDao;
import com.zm.mall.dao.system.AlwaysProfitSonDao;
import com.zm.mall.domain.system.AlwaysProfit;
import com.zm.mall.domain.system.AlwaysProfitSon;

import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public class AlwaysProfitSonDaoImpl extends BaseDaoImpl<AlwaysProfitSon> implements AlwaysProfitSonDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.AlwaysProfitSonDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }


    @Override
    public void saveAlwaysProfitSon(AlwaysProfitSon alwaysProfitSon) {
        sqlTemplate.insert(getNameSpace("saveAlwaysProfitSon"),alwaysProfitSon);
    }

    @Override
    public List<AlwaysProfitSon> selectAlwaysProfitByAlwaysProfitCode(String alwaysProfitCode) {
        return sqlTemplate.selectList(getNameSpace("selectAlwaysProfitByAlwaysProfitCode"), alwaysProfitCode);
    }

    @Override
    public void updateAlwaysSonProfit(AlwaysProfitSon alwaysProfitSon) {
        sqlTemplate.update(getNameSpace("updateAlwaysSonProfit"), alwaysProfitSon);
    }
}
