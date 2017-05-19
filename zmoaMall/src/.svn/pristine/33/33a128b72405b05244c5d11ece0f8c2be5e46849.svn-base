package com.zm.mall.dao.system;

import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDao;

import com.zm.mall.domain.system.Distribution;


import java.util.List;


/**
 * Created by Administrator on 2016/11/19.
 */
public interface DistributionDao extends BaseDao<Distribution> {
    void distributionSave(Distribution distribution);
    void distributionUpdate(Distribution distribution);

    void distributionAddStartTime(Distribution distribution);
    void distributionAddEndTime(Distribution distribution);

    List<Distribution> selectByLimit(Page page);
    List<Distribution> selectDistributionNoConsent(Page page);
    //分页查询审批同意的小组数据
    List<Distribution> selectDistributionConsent(Page page);
    //分页查询审批同意的所有数据
    List<Distribution> selectDistributionAllConsent(Page page);

    Integer selectAllCount(Page page);
    Integer selectDistributionNoConsentCount(Page page);
    //统计审批同意的小组数据
    Integer selectDistributionConsentCount(Page page);
    //统计审批同意的所有数据
    Integer selectDistributionAllConsentCount(Page page);

    Distribution selectOneDistribution(String distributionCode);

    void updateDistributionState(Distribution distribution);
    void updateDistributionNoState(Distribution distribution);

    //统计利润的方法
    List<Distribution> selectFinishTask();
    List<Distribution>  selectDistributionCar(String purchaseCode);
    void updateDistributionCarState(String purchaseCode);
    //保存后续数据
    void distributionSaveLaterData(Distribution distribution);
}
