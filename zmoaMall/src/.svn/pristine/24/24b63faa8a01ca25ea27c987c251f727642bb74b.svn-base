package com.zm.mall.dao.business.accountsUsers;

import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.accountsUsers.TradingRecord;

import java.util.List;

/**
 * Created by lp on 2017/3/24.
 */
public interface TradingRecordDao extends BaseDao<TradingRecord> {

    /*
    查询交易记录
     */
    public List<TradingRecord> selTradingRecordResultByPage(Page page);
   //增加交易记录
    public Integer insertTrading(TradingRecord tradingRecord);
    //根据OrderCode查询交易记录
    public TradingRecord selTradingRecordByOrderCode(TradingRecord tradingRecord);
    //银联返回成功修改account_trading_record充值消费记录表successState状态
    public  Integer updateSuccessState(TradingRecord tradingRecord);
}
