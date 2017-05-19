package com.zm.mall.service.system.impl;




import com.zm.mall.client.Page;
import com.zm.mall.client.service.system.TruckManageService;
import com.zm.mall.dao.system.TruckManageDao;
import com.zm.mall.domain.system.TruckManage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liyanshuai on 2016/9/20.
 */
@Service("TruckManageService")
public class TruckManageServiceImpl implements TruckManageService {

    private static Log log = LogFactory.getLog(TruckManageService.class);
    @Resource
    private TruckManageDao truckManageDao;




    @Override
    public Page selectAllSystemCode(Page page) {
        page.setBeginNumber((page.getCurrentPage() - 1) * page.getPageSize());
        List<TruckManage> list = truckManageDao.selectByLimit(page);
        Integer pageCount = truckManageDao.selectAllCount(page);
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
        if (list !=null && list.size()>0){

            page.setResultTruckManage(list);
            return page;
        }
        return null;
    }

    /**
     * 根据条件查询用户的信息
     * @param TruckManage
     * @return
     */


   /* public List<TruckManage>selectAllSystemCode() throws Exception{


        //查询数据库是否存在该用户。
        List<TruckManage> list = truckManageDao.selectAllByContion();
        if(list != null && list.size()>0){

            return list;
        }
        return null;
    }*/

    @Override
    public void updateTruck(TruckManage truckManage) {
        truckManageDao.updateTruck(truckManage);
    }

    @Override
    public void deleteTruck(TruckManage truckManage) {
        truckManageDao.deleteTruck(truckManage);
    }

    //*******************************************************************************************

    /**
     * 车辆管理分页查询
     * @param page
     * @return
     */
    @Override
    public Page selectAllTruck(Page page,TruckManage truckManage) {
        page.setTruckManage(truckManage);
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        List<TruckManage> list = truckManageDao.selectByLimit(page);
        //遍历数据，修改页面展示数据（状态）
        for (int i=0;i<list.size();i++){
            if (list.get(i).getState().equals("1")){
                list.get(i).setState("空闲");
            }
            if (list.get(i).getState().equals("2")){
                list.get(i).setState("等待");
            }
                if (list.get(i).getState().equals("3")){
                list.get(i).setState("请假");
            }
            if (list.get(i).getState().equals("4")){
                list.get(i).setState("分配");
            }
            if (list.get(i).getState().equals("5")){
                list.get(i).setState("运行");
            }
        }
        Integer pageCount = truckManageDao.selectAllCount(page);
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize()+1);
        if (list !=null && list.size()>0){

            page.setResultTruckManage(list);
            return page;
        }
        return null;
    }

    /**
     * 添加车辆
     * @param truckManage
     */
    @Override
    public void addTruck(TruckManage truckManage) {

        truckManageDao.addTruck(truckManage);
    }

    /**
     * 根据id查询车辆
     * @param id
     * @return
     */
    @Override
    public TruckManage selectOneTruckManage(Integer id) {
       return truckManageDao.selectOneTruckManage(id);
    }

    /**
     * 车辆排号
     * @param truckManage
     */
    @Override
    public void truckSort(TruckManage truckManage) {
        truckManageDao.truckSort(truckManage);
    }

    @Override
    public void truckLeave(TruckManage truckManage) {
        truckManageDao.truckLeave(truckManage);
    }

    @Override
    public List<TruckManage> selectCar(String state) {
        return truckManageDao.selectCar(state);
    }

    @Override
    public void updateTruckState(TruckManage truckManage) {
        truckManageDao.updateTruckState(truckManage);
    }


    //*************************************************************************************************
    @Override
    public List<TruckManage> selectAllNoEmploy() throws Exception {
        List<TruckManage> list = truckManageDao.selectAllNoEmploy();
        if(list != null && list.size()>0){

            return list;
        }
        return null;
    }

    @Override
    public List<TruckManage> selectAllStateUsable() throws Exception {
        List<TruckManage> list = truckManageDao.selectAllStateUsable();
        if(list != null && list.size()>0){

            return list;
        }
        return null;
    }
}
