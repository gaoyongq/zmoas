package com.zm.mall.service.system.impl;



import com.zm.mall.client.service.system.DistributionSonService;
import com.zm.mall.dao.system.DistributionSonDao;
import com.zm.mall.domain.system.DistributionSon;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2016/11/13.
 */

@Service("DistributionSonService")
public class DistributionSonServiceImpl implements DistributionSonService {
    private static Log log= LogFactory.getLog(DistributionSonService.class);

    @Autowired
    private DistributionSonDao distributionSonDao;
    @Override
    public void distributionSonSave(DistributionSon distributionSon) {
        distributionSonDao.distributionSonSave(distributionSon);
    }

    @Override
    public void distributionSonUpdate(DistributionSon distributionSon) {
        distributionSonDao.distributionSonUpdate(distributionSon);
    }

    @Override
    public List<DistributionSon> selectAllDistributionSon(String distributionCode) {
        return distributionSonDao.selectAllDistributionSon(distributionCode);
    }

    @Override
    public void distributionSonSaveLaterData(DistributionSon distributionSon) {
         distributionSonDao.distributionSonSaveLaterData(distributionSon);
    }


}
