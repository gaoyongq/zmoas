package com.zm.mall.service.system.impl;


import com.zm.mall.client.service.system.AlwaysProfitService;
import com.zm.mall.client.service.system.AlwaysProfitSonService;

import com.zm.mall.dao.system.AlwaysProfitSonDao;
import com.zm.mall.domain.system.AlwaysProfitSon;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyanshuai on 2016/9/20.
 */
@Service("AlwaysProfitSonService")
public class AlwaysProfitSonServiceImpl implements AlwaysProfitSonService {

    private static Log log = LogFactory.getLog(AlwaysProfitService.class);
    @Autowired
    private AlwaysProfitSonDao alwaysProfitSonDao;



    @Override
    public void saveAlwaysSonProfit(AlwaysProfitSon alwaysProfitson) {
        alwaysProfitSonDao.saveAlwaysProfitSon(alwaysProfitson);
    }

    @Override
    public List<AlwaysProfitSon> selectAlwaysProfitByAlwaysProfitCode(String alwaysProfitCode) {
        return alwaysProfitSonDao.selectAlwaysProfitByAlwaysProfitCode(alwaysProfitCode);
    }

    @Override
    public void updateAlwaysSonProfit(AlwaysProfitSon alwaysProfitSon) {
        alwaysProfitSonDao.updateAlwaysSonProfit(alwaysProfitSon);
    }
}
