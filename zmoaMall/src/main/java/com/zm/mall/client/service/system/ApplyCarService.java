package com.zm.mall.client.service.system;


import com.zm.mall.client.Page;
import com.zm.mall.domain.system.ApplyCar;

/**
 * Created by Administrator on 2016/11/13.
 */
public interface ApplyCarService {

    void saveApplyCar(ApplyCar applyCar);
    Page selectApplyCarList(Page page,ApplyCar applyCar);
    void applyCarAddCar(ApplyCar applyCar);
    ApplyCar selectOneAppCar(String applyCarCode);
    void updateShowState(String carNumber);
}
