package com.zm.mall.client.service.system;


import com.zm.mall.client.Page;
import com.zm.mall.domain.system.Distribution;

import java.util.List;


/**
 * Created by Administrator on 2016/11/13.
 */
public interface DistributionService {

    void distributionSave(Distribution distribution);
    void distributionUpdate(Distribution distribution);
    void distributionAddStartTime(Distribution distribution);
    void distributionAddEndTime(Distribution distribution);
    Page selectDistributionSP(Page page,Distribution distribution);
    Page selectDistributionNoConsent(Page page);
    /*查询审批同意的小组数据*/
    Page selectDistributionConsent(Page page,Distribution distribution );
    /*查询审批同意的所有数据*/
    Page selectDistributionAllConsent(Page page,Distribution distribution );
    Distribution selectOneDistribution(String distributionCode);

    void updateDistributionState(Distribution distribution);
    void updateDistributionNoState(Distribution distribution);


    //统计利润的方法
    List<Distribution> selectFinishTask();
    String selectDistributionCar(String purchaseCode);
    List<Distribution> selectDistributionByPurchaseCode(String purchaseCode);

    void updateDistributionCarState(String purchaseCode);

    //保存后续的数据
    void distributionSaveLaterData(Distribution distribution);
}
