package com.zm.mall.dao.system;

import com.zm.mall.client.result.system.MoneyManagerResult;
import com.zm.mall.client.vo.system.MoneyManagerVo;
import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.system.ActivityTask;
import com.zm.mall.domain.system.MoneyItem;
import com.zm.mall.domain.system.MoneyManager;

import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public interface MoneyManagerDao extends BaseDao<MoneyManager> {
   //查询报销单
    List<MoneyManager> selectMoneyByContion(MoneyManager moneyManager);
    List<MoneyManager> selectCheckByContion(MoneyManager moneyManager);
    List<MoneyItem>selectMoneyItem(Long id);
    Integer deleteMoneyManager(String id);
    //根据传值的ids查询报销单
    List<MoneyManager> selectMoneyManagerById(MoneyManager moneyManager);
    //更新部门审核意见
    Integer updateMoneyDepCheck(MoneyManager moneyManager);
    //更新leader审核意见
    Integer updateMoneyLeaCheck(MoneyManager moneyManager);
    //查询工作流任务的id
    ActivityTask selectTaskId(String exeId);
    //修改报销单信息
    Integer updateMoneyManager(MoneyManager moneyManager);
    //新增报销单对应的item信息
    Integer insertMoneyManagerItem(MoneyItem moneyItem);
    //删除money item信息
    Integer deleteMoneyManagerItem(Long id);
    //新增报销单
    Integer insertMoneyManager(MoneyManager moneyManager);
    //查询新增的报销单id
    MoneyManager selectMoneyManager(String statusName);
    //查询总条数
    Integer selectAllMoneyCount(MoneyManager moneyManager);
    Integer selectAllCheckCount(MoneyManager moneyManager);

}
