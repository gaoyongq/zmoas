package com.zm.mall.dao.business.accountsUsers.impl;

import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.accountsUsers.TradingRecordDao;
import com.zm.mall.domain.business.accountsUsers.TradingRecord;

import java.util.List;

/**
 * Created by lp on 2017/3/24.
 */
public class TradingRecordDaoImpl extends BaseDaoImpl<TradingRecord> implements TradingRecordDao {

    private static final String NAMESPACE="com.zm.mall.dao.business.accountsUsers.TradingRecordDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    /**
     * 查询交易记录
     * @param page
     * @return
     */
    @Override
    public List<TradingRecord> selTradingRecordResultByPage(Page page) {
        return sqlTemplate.selectList(getNameSpace("selTradingRecordResultByPage"),page);
    }
    /**
     * 增加余额交易记录
     */
    @Override
    public Integer insertTrading(TradingRecord tradingRecord) {
        return sqlTemplate.insert(getNameSpace("insertTrading"),tradingRecord);
    }

    /**
     * 根据OrderCode查询记录
     * @param tradingRecord
     * @return
     */
    @Override
    public TradingRecord selTradingRecordByOrderCode(TradingRecord tradingRecord) {
        return sqlTemplate.selectOne(getNameSpace("selTradingRecordByOrderCode"), tradingRecord);
    }

    @Override
    public Integer updateSuccessState(TradingRecord tradingRecord) {
        return sqlTemplate.update(getNameSpace("updateSuccessState"),tradingRecord);
    }
}
