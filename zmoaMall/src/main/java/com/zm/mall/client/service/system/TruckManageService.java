package com.zm.mall.client.service.system;






import com.zm.mall.client.Page;
import com.zm.mall.domain.system.TruckManage;

import java.util.List;

/**
 * Created by liyanshuai on 2016/9/20.
 */
public interface TruckManageService {



    /**
     * 根据用户的条件查询用户的信息
     * @param userInfoVo
     * @return
     */

   // List<TruckManage> selectAllSystemCode()throws Exception;
    List<TruckManage> selectAllNoEmploy()throws Exception;
    List<TruckManage> selectAllStateUsable()throws Exception;
    Page selectAllSystemCode(Page page);
    void updateTruck(TruckManage truckManage);
    void deleteTruck(TruckManage truckManage);

    //*************************************************
    /**
     * 车辆管理 分页
     */
    Page selectAllTruck(Page page,TruckManage truckManage);

    /**
     * 添加车辆
     * @param truckManage
     */
    void addTruck(TruckManage truckManage);

    /**
     * 通过id查询车辆
     * @param id
     * @return
     */
    TruckManage selectOneTruckManage(Integer id);

    /**
     * 车辆排号
     * @param truckManage
     */
    void truckSort(TruckManage truckManage);

    /**
     * 车辆请假
     * @param truckManage
     */
    void truckLeave(TruckManage truckManage);

    /**
     * 查询等待的车辆
     * @param state
     * @return
     */
    List<TruckManage> selectCar(String state);
    void updateTruckState(TruckManage truckManage);
}
