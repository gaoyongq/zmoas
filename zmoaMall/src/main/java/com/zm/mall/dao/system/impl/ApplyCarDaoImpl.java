package com.zm.mall.dao.system.impl;


import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.ApplyCarDao;
import com.zm.mall.domain.system.ApplyCar;

import java.util.List;


/**
 * Created by Administrator on 2016/11/12.
 */
public class ApplyCarDaoImpl extends BaseDaoImpl<ApplyCar> implements ApplyCarDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.ApplyCarDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public void saveApplyCar(ApplyCar applyCar) {
        sqlTemplate.insert(getNameSpace("saveApplyCar"), applyCar);
    }

    @Override
    public List<ApplyCar> selectByLimit(Page page) {
        return sqlTemplate.selectList(getNameSpace("selectByLimit"), page);
    }

    @Override
    public Integer selectAllCount(Page page) {
        return sqlTemplate.selectOne(getNameSpace("selectAllCount"),page);
    }

    /**
     * 真正添加车辆
     * @param applyCar
     */
    @Override
    public void applyCarAddCar(ApplyCar applyCar) {
        sqlTemplate.update(getNameSpace("applyCarAddCar"), applyCar);
    }

    @Override
    public ApplyCar selectOneAppCar(String applyCarCode) {
        return sqlTemplate.selectOne(getNameSpace("selectOneAppCar"),applyCarCode);
    }
    /**
     * 白洋洋 订单添加时将申请车辆的订单插入车辆申请表
     * @param applyCar
     * @return
     */
    @Override
    public Integer insertApplyCar(ApplyCar applyCar) {
        return sqlTemplate.insert(getNameSpace("insertApplyCar"),applyCar);
    }

    @Override
    public void updateShowState(String carNumber) {
        sqlTemplate.update(getNameSpace("updateShowState"), carNumber);
    }

}
