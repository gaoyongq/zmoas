package com.zm.mall.service.system.impl;

import com.zm.mall.client.service.system.ApplyCarSonService;
import com.zm.mall.dao.system.ApplyCarSonDao;
import com.zm.mall.domain.system.ApplyCarSon;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2016/11/13.
 */

@Service("applyCarSonService")
public class ApplyCarSonServiceImpl implements ApplyCarSonService{
    private static Log log= LogFactory.getLog(ApplyCarSonService.class);

    @Autowired
    private ApplyCarSonDao applyCarSonDao;
    @Override
    public void saveOrderAddCar(ApplyCarSon applyCarSon) {
        applyCarSonDao.saveOrderAddCar(applyCarSon);
    }

    @Override
    public List<ApplyCarSon> selectApplyCarSonByApplyCarCoder(String applyCarCode) {
        return applyCarSonDao.selectApplyCarSonByApplyCarCoder(applyCarCode);
    }
}
