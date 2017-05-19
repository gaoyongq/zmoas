package com.zm.mall.dao.system.impl;

import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.TruckManageDao;
import com.zm.mall.domain.system.TruckManage;


import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public class TruckManageDaoImpl extends BaseDaoImpl<TruckManage> implements TruckManageDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.TruckManageDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }


  /*  public List<TruckManage> selectAllByContion() {
        List<TruckManage> list =  sqlTemplate.selectList(getNameSpace("selectAllByContion"));
        return list;
    }*/



    @Override
    public void addTruck(TruckManage truckManage) {
        sqlTemplate.insert(getNameSpace("insertTruckManage"), truckManage);
    }

    @Override
    public void updateTruck(TruckManage truckManage) {
        sqlTemplate.update(getNameSpace("updateTruckManage"), truckManage);
    }

    @Override
    public void deleteTruck(TruckManage truckManage) {
        sqlTemplate.update(getNameSpace("deleteTruckManage"), truckManage);
    }

    /**
     * 车辆分页
     * @param page
     * @return
     */
    @Override
    public List<TruckManage> selectByLimit(Page page) {
        return sqlTemplate.selectList(getNameSpace("selectByLimit"), page);
    }

    @Override
    public Integer selectAllCount(Page page) {

        return sqlTemplate.selectOne(getNameSpace("selectAllCount"),page);
    }

    /**
     * 根据id查询车辆
     * @param id
     * @return
     */
    @Override
    public TruckManage selectOneTruckManage(Integer id) {
        return sqlTemplate.selectOne(getNameSpace("selectOneTruckManage"), id);
    }

    /**
     * 车辆排号
     * @param truckManage
     */
    @Override
    public void truckSort(TruckManage truckManage) {
        sqlTemplate.update(getNameSpace("truckSort"), truckManage);
    }

    @Override
    public void truckLeave(TruckManage truckManage) {
        sqlTemplate.update(getNameSpace("truckLeave"), truckManage);
    }

    @Override
    public List<TruckManage> selectCar(String state) {
        return sqlTemplate.selectList(getNameSpace("selectCar"), state);
    }

    @Override
    public void updateTruckState(TruckManage truckManage) {
        sqlTemplate.update(getNameSpace("updateTruckState"), truckManage);
    }

    @Override
    public List<TruckManage> selectAllNoEmploy() {
        List<TruckManage> list =  sqlTemplate.selectList(getNameSpace("selectAllNoEmploy"));
        return list;
    }

    @Override
    public List<TruckManage> selectAllStateUsable() {
        List<TruckManage> list =  sqlTemplate.selectList(getNameSpace("selectAllNoEmploy"));
        return list;
    }

}
