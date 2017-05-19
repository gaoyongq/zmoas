package com.zm.mall.dao.system.impl;

import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.AlwaysProfitDao;
import com.zm.mall.domain.system.AlwaysProfit;

import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public class AlwaysProfitDaoImpl extends BaseDaoImpl<AlwaysProfit> implements AlwaysProfitDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.AlwaysProfitDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }


    @Override
    public void saveAlwaysProfit(AlwaysProfit alwaysProfit) {
        sqlTemplate.insert(getNameSpace("saveAlwaysProfit"),alwaysProfit);
    }

    @Override
    public List<AlwaysProfit> selectAlwaysProfitLimit(Page page) {
        return sqlTemplate.selectList(getNameSpace("selectAlwaysProfitLimit"), page);
    }

    @Override
    public Integer selectAlwaysProfitCount(Page page) {
        return sqlTemplate.selectOne(getNameSpace("selectAlwaysProfitCount"), page);

    }

    @Override
    public List<AlwaysProfit> selectAlwaysProfit(String alwaysProfitCode) {
        return sqlTemplate.selectList(getNameSpace("selectAlwaysProfit"),alwaysProfitCode);
    }

    @Override
    public List<AlwaysProfit> selectOneProfit(String purchaseCode) {
        return sqlTemplate.selectList(getNameSpace("selectOneProfit"), purchaseCode);
    }

    @Override
    public void updateAlwaysProfit(AlwaysProfit alwaysProfit) {
        sqlTemplate.update(getNameSpace("updateAlwaysProfit"), alwaysProfit);
    }
}
