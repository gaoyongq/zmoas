package com.zm.mall.dao.system;


import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.system.AlwaysProfit;

import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public interface AlwaysProfitDao extends BaseDao<AlwaysProfit> {

    /**
     * 根据条件查询用户的信息
     * @param
     * @return
     */
    void saveAlwaysProfit(AlwaysProfit alwaysProfit);
    List<AlwaysProfit>selectAlwaysProfitLimit(Page page );
    Integer selectAlwaysProfitCount(Page page);
    List<AlwaysProfit>selectAlwaysProfit(String alwaysProfitCode);
    List<AlwaysProfit> selectOneProfit(String purchaseCode);
    void updateAlwaysProfit(AlwaysProfit alwaysProfit);
}
