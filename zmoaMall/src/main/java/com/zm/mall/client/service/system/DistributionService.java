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
    /*��ѯ����ͬ���С������*/
    Page selectDistributionConsent(Page page,Distribution distribution );
    /*��ѯ����ͬ�����������*/
    Page selectDistributionAllConsent(Page page,Distribution distribution );
    Distribution selectOneDistribution(String distributionCode);

    void updateDistributionState(Distribution distribution);
    void updateDistributionNoState(Distribution distribution);


    //ͳ������ķ���
    List<Distribution> selectFinishTask();
    String selectDistributionCar(String purchaseCode);
    List<Distribution> selectDistributionByPurchaseCode(String purchaseCode);

    void updateDistributionCarState(String purchaseCode);

    //�������������
    void distributionSaveLaterData(Distribution distribution);
}
