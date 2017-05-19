package com.zm.mall.dao.system;

import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.system.TruckManage;


import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public interface TruckManageDao extends BaseDao<TruckManage> {

    /**
     * 根据条件查询用户的信息
     * @return
     */

   // List<TruckManage> selectAllByContion();
    List<TruckManage> selectAllNoEmploy();
    List<TruckManage> selectAllStateUsable();
    void addTruck(TruckManage truckManage);
    void updateTruck(TruckManage truckManage);
    void deleteTruck(TruckManage truckManage);
    List<TruckManage> selectByLimit(Page page);
    Integer selectAllCount(Page page);
    TruckManage selectOneTruckManage(Integer id);
    /**
     * 车辆排号
     * @param truckManage
     */
    void truckSort(TruckManage truckManage);
    //请假
    void truckLeave(TruckManage truckManage);
    /**
     * 查询等待的车辆
     * @param state
     * @return
     */
    List<TruckManage> selectCar(String state);
    void updateTruckState(TruckManage truckManage);
}
