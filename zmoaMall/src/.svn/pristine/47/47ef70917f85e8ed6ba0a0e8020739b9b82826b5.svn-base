package com.zm.mall.service.system.impl;



import com.zm.mall.client.Page;
import com.zm.mall.client.service.system.AlwaysProfitService;

import com.zm.mall.dao.system.AlwaysProfitDao;
import com.zm.mall.domain.system.AlwaysProfit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyanshuai on 2016/9/20.
 */
@Service("AlwaysProfitService")
public class AlwaysProfitServiceImpl implements AlwaysProfitService {

    private static Log log = LogFactory.getLog(AlwaysProfitService.class);
    @Autowired
    private AlwaysProfitDao alwaysProfitDao;

    @Override
    public void saveAlwaysProfit(AlwaysProfit alwaysProfit) {
        alwaysProfitDao.saveAlwaysProfit(alwaysProfit);
    }

    @Override
    public Page selectAllAlwaysProfit(Page page ,AlwaysProfit alwaysProfit) {
        page.setAlwaysProfit(alwaysProfit);
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        List<AlwaysProfit> list = alwaysProfitDao.selectAlwaysProfitLimit(page);

        Integer pageCount = alwaysProfitDao.selectAlwaysProfitCount(page);
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
        if (list !=null && list.size()>0){
            page.setResultAlwaysProfit(list);
            return page;
        }
        return null;
    }

    @Override
    public List<AlwaysProfit> selectAllAlwaysProfit(String alwaysProfitCode) {
       return alwaysProfitDao.selectAlwaysProfit(alwaysProfitCode);
    }

    @Override
    public List<AlwaysProfit> selectOneProfit(String purchaseCode) {
        return alwaysProfitDao.selectOneProfit(purchaseCode);
    }

    @Override
    public void updateAlwaysProfit(AlwaysProfit alwaysProfit) {
        alwaysProfitDao.updateAlwaysProfit(alwaysProfit);
    }
}
