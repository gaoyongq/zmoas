package com.zm.mall.dao.system.impl;


import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.ApplyCarSonDao;
import com.zm.mall.domain.system.ApplyCarSon;

import java.util.List;


/**
 * Created by Administrator on 2016/11/12.
 */
public class ApplyCarSonDaoImpl extends BaseDaoImpl<ApplyCarSon> implements ApplyCarSonDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.ApplyCarSonDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public void saveOrderAddCar(ApplyCarSon applyCarSon) {
        sqlTemplate.insert(getNameSpace("saveOrderAddCar"), applyCarSon);
    }

    @Override
    public List<ApplyCarSon> selectApplyCarSonByApplyCarCoder(String applyCarCode) {

        return sqlTemplate.selectList(getNameSpace("selectApplyCarSonByApplyCarCoder"), applyCarCode);
    }

    /**
     * 白洋洋   将申请车辆的订单插入车辆申请子表
     * @param applyCarSon
     * @return
     */

    @Override
    public Integer insertOrder(ApplyCarSon applyCarSon) {
        return sqlTemplate.insert(getNameSpace("insertOrder"),applyCarSon);
    }
}
