package com.zm.mall.service.system.impl;



import com.zm.mall.client.result.system.MoneyItemResult;
import com.zm.mall.client.result.system.MoneyManagerResult;
import com.zm.mall.client.service.system.MoneyManagerService;
import com.zm.mall.client.vo.system.MoneyManagerVo;
import com.zm.mall.dao.system.MoneyManagerDao;
import com.zm.mall.domain.system.ActivityTask;
import com.zm.mall.domain.system.MoneyItem;
import com.zm.mall.domain.system.MoneyManager;
import com.zm.mall.domain.system.Role;
import com.zm.mall.service.SpaceBeanCopy;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by liyanshuai on 2016/9/20.
 */
@Service("moneyManagerService")
public class MoneyManagerServiceImpl implements MoneyManagerService {

    private static Log log = LogFactory.getLog(MoneyManagerService.class);
    @Autowired
    private MoneyManagerDao moneyManagerDao;
    //查询MoneyItem
    @Override
    public List<MoneyItemResult> selectMoneyItem(Long id) {
        List<MoneyItem> list = moneyManagerDao.selectMoneyItem(id);
        if(list != null && list.size()>0){
            List<MoneyItemResult> moneyItemResult=  SpaceBeanCopy.moneyItem2moneyItemResult(list);
            return moneyItemResult;
        }

        return null;
    }
    //查询MoneyManager
    @Override
    public Integer deleteMoneyManager(String id) {

        int result = moneyManagerDao.deleteMoneyManager(id);
        return result;
    }

    /**
     * 查询用户总条数
     * @param moneyManagerVo
     * @return
     */
    @Override
    @Transactional
    public Integer selectAllMoneyCount(MoneyManagerVo moneyManagerVo) {
        MoneyManager moneyManager  = SpaceBeanCopy.moneyManagerVo2MoneyManager(moneyManagerVo);
        int result = moneyManagerDao.selectAllMoneyCount(moneyManager);
        return result;
    }
    /**
     * 查询审核总条数
     * @param moneyManagerVo
     * @return
     */
    @Override
    public Integer selectAllCheckCount(MoneyManagerVo moneyManagerVo) {
        MoneyManager moneyManager  = SpaceBeanCopy.moneyManagerVo2MoneyManager(moneyManagerVo);
        int result = moneyManagerDao.selectAllCheckCount(moneyManager);
        return result;
    }
/**
     * 员工查询自己报销单 领导则查询需要审核的报销单
     * @param moneyManagerVo
     * @return
     */
//    @Override
//    public List<MoneyManagerResult> selectMoneyManager(MoneyManagerVo moneyManagerVo) {
//
//        //对象类型转换 moneyManagerVo 2 moneyManager
//        MoneyManager moneyManager  = SpaceBeanCopy.moneyManagerVo2MoneyManager(moneyManagerVo);
//            Set<Role> role = moneyManager.getRoles();
//             System.out.println(moneyManager.getDepartmentName());
//            for(Role ro:role){
//                    if (ro.getId() >= 3 && ro.getId() <= 10) {
//                        //设置工作流中任务的名字
//                        moneyManager.setRealName("部门经理");
//
//                        moneyManager.setBeginNumber((moneyManager.getCurrentPage()-1)*moneyManager.getPageSize());
//                        //总条数
//                        Integer pageCount = moneyManagerDao.selectAllMoneyCount(moneyManager);
//                        //总页数
//                        moneyManager.setTotalPages(pageCount % moneyManager.getPageSize() == 0 ? pageCount / moneyManager.getPageSize() : pageCount / moneyManager.getPageSize()+1);
//                        //查询部门审核
//                        List<MoneyManager> list = moneyManagerDao.selectCheckByContion(moneyManager);
//                        if(list != null && list.size()>0){
//                            List <MoneyManagerResult> moneyManagerResults =  SpaceBeanCopy.moneyManager2MoneyManagerResult(list);
//                            return moneyManagerResults;
//                        }
//                    }else if(ro.getId() >= 1 && ro.getId() <= 2){
//                        //设置工作流中任务的名字
//                        moneyManager.setRealName("总经理");
//                        moneyManager.setDepartmentName("");
//
//                        moneyManager.setBeginNumber((moneyManager.getCurrentPage()-1)*moneyManager.getPageSize());
//                        //总条数
//                        Integer pageCount = moneyManagerDao.selectAllMoneyCount(moneyManager);
//                        //总页数
//                        moneyManager.setTotalPages(pageCount % moneyManager.getPageSize() == 0 ? pageCount / moneyManager.getPageSize() : pageCount / moneyManager.getPageSize()+1);
//
//                        //查询审核
//                        List<MoneyManager> list = moneyManagerDao.selectCheckByContion(moneyManager);
//                        if(list != null && list.size()>0){
//                            List <MoneyManagerResult> moneyManagerResults =  SpaceBeanCopy.moneyManager2MoneyManagerResult(list);
//                            return moneyManagerResults;
//                        }
//                    }else{
//
//                        moneyManager.setBeginNumber((moneyManager.getCurrentPage()-1)*moneyManager.getPageSize());
//                        //总条数
//                        Integer pageCount = moneyManagerDao.selectAllMoneyCount(moneyManager);
//                        //总页数
//                        moneyManager.setTotalPages(pageCount % moneyManager.getPageSize() == 0 ? pageCount / moneyManager.getPageSize() : pageCount / moneyManager.getPageSize()+1);
//
//                        //查询自己的审核单
//                        List<MoneyManager> list = moneyManagerDao.selectMoneyByContion(moneyManager);
//                        if(list != null && list.size()>0){
//                            List <MoneyManagerResult> moneyManagerResults =  SpaceBeanCopy.moneyManager2MoneyManagerResult(list);
//                            return moneyManagerResults;
//                        }
//                    }
//            }
//        return null;
//    }

    /**
     * 更新部门审核意见
     * @param moneyManagerVo
     */

    @Override
    @Transactional
    public Integer updateMoneyDepCheck(MoneyManagerVo moneyManagerVo) {
        try {
            //对象类型转换 moneyManagerVo 2 moneyManager
            MoneyManager moneyManager = SpaceBeanCopy.moneyManagerVo2MoneyManager(moneyManagerVo);
            int result = moneyManagerDao.updateMoneyDepCheck(moneyManager);
            //查询工作流任务的id
            ActivityTask activityTask = moneyManagerDao.selectTaskId(moneyManager.getStatusName());
            System.out.println(activityTask.getId());
            //部门审核后更新工作流状态
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            //工作流变量
            Map<String, Object> variables = new HashMap<String, Object>();
            if (moneyManager.getDeparmentStatusCode().equals("部门审核通过")) {
                variables.put("checked", 1);
            } else if (moneyManager.getDeparmentStatusCode().equals("部门审核不通过")) {
                variables.put("checked", 0);
            }

            System.out.println(moneyManager.getStatusName());
            processEngine.getTaskService().complete(activityTask.getId(), variables);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 更新leader审核意见
     * @param moneyManagerVo
     */
    @Override
    @Transactional
    public Integer updateMoneyLeaCheck(MoneyManagerVo moneyManagerVo) {
        try {
            //对象类型转换 moneyManagerVo 2 moneyManager
            MoneyManager moneyManager  = SpaceBeanCopy.moneyManagerVo2MoneyManager(moneyManagerVo);
            int result = moneyManagerDao.updateMoneyLeaCheck(moneyManager);
            //查询工作流任务的id
            ActivityTask activityTask = moneyManagerDao.selectTaskId(moneyManager.getStatusName());
            System.out.println(activityTask.getId());
            //leader审核后更新工作流状态
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            processEngine.getTaskService().complete(activityTask.getId());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *通过id查询报销单
     * @param moneyManagerVo
     * @return
     */

    @Override
    public List<MoneyManagerResult> selectMoneyManagerById(MoneyManagerVo moneyManagerVo) {
        //对象类型转换 moneyManagerVo 2 moneyManager
        MoneyManager moneyManager  = SpaceBeanCopy.moneyManagerVo2MoneyManager(moneyManagerVo);
        List<MoneyManager> list = moneyManagerDao.selectMoneyManagerById(moneyManager);
        if(list != null && list.size()>0){
            List <MoneyManagerResult> moneyManagerResults =  SpaceBeanCopy.moneyManager2MoneyManagerResult(list);
            return moneyManagerResults;
        }

        return null;
    }
    /**
     * 用户修改报销单信息
     */
    @Override
    @Transactional
    public Integer updateMoneyManager(MoneyManagerVo moneyManagerVo) {
        //对象类型转换 moneyManagerVo 2 moneyManager
        MoneyManager moneyManager  = SpaceBeanCopy.moneyManagerVo2MoneyManager(moneyManagerVo);
        //修改前删除 moneyitem信息
        int resulttemp = moneyManagerDao.deleteMoneyManagerItem(moneyManagerVo.getId());

        //新增报销单对应的item信息
        for(MoneyItem item: moneyManager.getItemSet()){
            int results = moneyManagerDao.insertMoneyManagerItem(item);
        }
        //修改报销单信息
        int result = moneyManagerDao.updateMoneyManager(moneyManager);


        return result;
    }

    /**
     * 新增报销信息
     */
    @Override
    @Transactional
    public Integer insertMoneyManager(MoneyManagerVo moneyManagerVo) {
        MoneyManager moneyManager  = SpaceBeanCopy.moneyManagerVo2MoneyManager(moneyManagerVo);
        //工作流-新增
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String processDefinitionId = "MoneyCheck:1:42504";// 流程定义id
        // 启动流程实例
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinitionId);
        moneyManager.setStatusName(processInstance.getId());
        //修改报销单信息
        int result = moneyManagerDao.insertMoneyManager(moneyManager);
        //根据工作流的id查询money id
        MoneyManager moneyManag = moneyManagerDao.selectMoneyManager(moneyManager.getStatusName());

        //新增报销单对应的item信息
        for(MoneyItem item: moneyManager.getItemSet()){
            item.setMoneyManageId(moneyManag.getId());
            int results = moneyManagerDao.insertMoneyManagerItem(item);
        }
        return result;
    }

    @Override
    public List<MoneyManagerResult> selectMoneyByContion(MoneyManagerVo moneyManagerVo) {
        MoneyManager moneyManager  = SpaceBeanCopy.moneyManagerVo2MoneyManager(moneyManagerVo);
        //查询自己的审核单
        List<MoneyManager> list = moneyManagerDao.selectMoneyByContion(moneyManager);
        if(list != null && list.size()>0){
            List <MoneyManagerResult> moneyManagerResults =  SpaceBeanCopy.moneyManager2MoneyManagerResult(list);
            return moneyManagerResults;
        }
        return null;
    }

    @Override
    public List<MoneyManagerResult> selectCheckByContion(MoneyManagerVo moneyManagerVo) {
        MoneyManager moneyManager  = SpaceBeanCopy.moneyManagerVo2MoneyManager(moneyManagerVo);
        List<MoneyManager> list = moneyManagerDao.selectCheckByContion(moneyManager);
            if(list != null && list.size()>0){
                List <MoneyManagerResult> moneyManagerResults =  SpaceBeanCopy.moneyManager2MoneyManagerResult(list);
                return moneyManagerResults;
            }
        return null;
    }
}
