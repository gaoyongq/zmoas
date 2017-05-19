package com.zm.mall.dao.system;

import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.system.ApplyCar;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12.
 */
public interface ApplyCarDao extends BaseDao<ApplyCar> {

    void saveApplyCar(ApplyCar applyCar);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<ApplyCar> selectByLimit(Page page);
    Integer selectAllCount(Page page);
    void applyCarAddCar(ApplyCar applyCar);
    ApplyCar selectOneAppCar(String applyCarCode);
    Integer insertApplyCar(ApplyCar applyCar);
    void updateShowState(String carNumber);
}
