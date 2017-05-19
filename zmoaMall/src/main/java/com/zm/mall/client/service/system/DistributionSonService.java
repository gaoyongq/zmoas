package com.zm.mall.client.service.system;


import com.zm.mall.domain.system.DistributionSon;

import java.util.List;

/**
 * Created by Administrator on 2016/11/13.
 */
public interface DistributionSonService {

    void distributionSonSave(DistributionSon distributionSon);
    void distributionSonUpdate(DistributionSon distributionSon);
    List<DistributionSon> selectAllDistributionSon(String distributionCode);
    //保存后续数据
    void distributionSonSaveLaterData(DistributionSon distributionSon);
}
