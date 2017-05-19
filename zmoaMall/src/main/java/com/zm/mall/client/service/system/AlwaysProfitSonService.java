package com.zm.mall.client.service.system;



import com.zm.mall.domain.system.AlwaysProfitSon;

import java.util.List;

/**
 * Created by Administrator on 2016/11/13.
 */
public interface AlwaysProfitSonService {

    void saveAlwaysSonProfit(AlwaysProfitSon alwaysProfitson);
    List<AlwaysProfitSon> selectAlwaysProfitByAlwaysProfitCode(String alwaysProfitCode);
    void updateAlwaysSonProfit(AlwaysProfitSon alwaysProfitSon);
}
