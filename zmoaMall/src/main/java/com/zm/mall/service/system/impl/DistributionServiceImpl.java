package com.zm.mall.service.system.impl;


import com.zm.mall.client.Page;
import com.zm.mall.client.service.system.DistributionService;
import com.zm.mall.dao.system.DistributionDao;
import com.zm.mall.domain.system.Distribution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Administrator on 2016/11/13.
 */

@Service("DistributionService")
public class DistributionServiceImpl implements DistributionService {
    private static Log log= LogFactory.getLog(DistributionService.class);
    @Autowired
    private DistributionDao distributionDao;

    @Override
    public void distributionSave(Distribution distribution) {
        distributionDao.distributionSave(distribution);
    }

    @Override
    public void distributionUpdate(Distribution distribution) {
        distributionDao.distributionUpdate(distribution);
    }

    @Override
    public void distributionAddStartTime(Distribution distribution) {
        distributionDao.distributionAddStartTime(distribution);
    }

    @Override
    public void distributionAddEndTime(Distribution distribution) {
        distributionDao.distributionAddEndTime(distribution);

    }


    @Override
    @Transactional
    public Page selectDistributionSP(Page page,Distribution distribution) {page.setDistribution(distribution);
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        List<Distribution> list = distributionDao.selectByLimit(page);

        Integer pageCount = distributionDao.selectAllCount(page);
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
        if (list !=null && list.size()>0){
            page.setResultDistribution(list);
            return page;
        }
        return null;
    }

    @Override
    public Page selectDistributionNoConsent(Page page) {
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        List<Distribution> list = distributionDao.selectDistributionNoConsent(page);

        Integer pageCount = distributionDao.selectDistributionNoConsentCount(page);
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
        if (list !=null && list.size()>0){
            page.setResultDistribution(list);
            return page;
        }
        return null;
    }
    //查询审批同意的小组数据
    @Override
    public Page selectDistributionConsent(Page page,Distribution distribution) {
        page.setDistribution(distribution);
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        List<Distribution> list = distributionDao.selectDistributionConsent(page);

        Integer pageCount = distributionDao.selectDistributionConsentCount(page);
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
        if (list !=null && list.size()>0){
            page.setResultDistribution(list);
            return page;
        }
        return null;
    }
    //查看审批同意的所有数据
    @Override
    public Page selectDistributionAllConsent(Page page,Distribution distribution) {
        page.setDistribution(distribution);
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        List<Distribution> list = distributionDao.selectDistributionAllConsent(page);

        Integer pageCount = distributionDao.selectDistributionAllConsentCount(page);
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
        if (list !=null && list.size()>0){
            page.setResultDistribution(list);
            return page;
        }
        return null;
    }

    @Override
    public Distribution selectOneDistribution(String distributionCode) {
        return distributionDao.selectOneDistribution(distributionCode);
    }

    @Override
    public void updateDistributionState(Distribution distribution) {
        distributionDao.updateDistributionState(distribution);
    }

    @Override
    public void updateDistributionNoState(Distribution distribution) {
        distributionDao.updateDistributionNoState(distribution);
    }

    @Override
    public List<Distribution> selectFinishTask() {
        return distributionDao.selectFinishTask();
    }

    @Override
    public String selectDistributionCar(String purchaseCode) {
        List<Distribution> list= distributionDao.selectDistributionCar(purchaseCode);
        for (int i=0;i<list.size();i++){
            if (!list.get(i).getState().equals("4")){
                return "0";
            }
        }
        return "1";
    }

    @Override
    public List<Distribution> selectDistributionByPurchaseCode(String purchaseCode) {
        return  distributionDao.selectDistributionCar(purchaseCode);
    }

    @Override
    public void updateDistributionCarState(String purchaseCode) {
        distributionDao.updateDistributionCarState(purchaseCode);
    }

    @Override
    public void distributionSaveLaterData(Distribution distribution) {
        distributionDao.distributionSaveLaterData(distribution);
    }
}
