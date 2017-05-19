package com.zm.mall.client.service.system;

import com.zm.mall.domain.system.ApplyCarSon;

import java.util.List;

/**
 * Created by Administrator on 2016/11/13.
 */
public interface ApplyCarSonService {

    void saveOrderAddCar(ApplyCarSon applyCarSon);
    List<ApplyCarSon> selectApplyCarSonByApplyCarCoder(String applyCarCode);
}
