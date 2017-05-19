package com.zm.mall.client.service.system;


import com.zm.mall.client.result.system.MoneyItemResult;
import com.zm.mall.client.result.system.MoneyManagerResult;
import com.zm.mall.client.vo.system.MoneyManagerVo;
import com.zm.mall.client.vo.user.UserInfoVo;
import com.zm.mall.domain.system.MoneyItem;
import com.zm.mall.domain.system.MoneyManager;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.util.List;
public interface MoneyManagerService {
    //根据登录用户查询报销单
//    @Transactional
//    List<MoneyManagerResult> selectMoneyManager( MoneyManagerVo moneyManagerVo);
    //根据登录用户查询报销单 chakan
    @Transactional
    List<MoneyManagerResult> selectMoneyByContion(MoneyManagerVo moneyManagerVo);
    //根据登录用户查询报销单 shenhe
    @Transactional
    List<MoneyManagerResult> selectCheckByContion(MoneyManagerVo moneyManagerVo);
    //根据传值的ids查询报销单
    @Transactional
    List<MoneyManagerResult> selectMoneyManagerById( MoneyManagerVo moneyManagerVo);
    @Transactional
    List<MoneyItemResult> selectMoneyItem(Long id);

    //删除
    @Transactional
    Integer deleteMoneyManager(String id);
    //更新部门审核意见
    @Transactional
    Integer updateMoneyDepCheck(MoneyManagerVo moneyManagerVo);
    //更新leader审核意见
    @Transactional
    Integer updateMoneyLeaCheck(MoneyManagerVo moneyManagerVo);
    //更新报销单修改信息
    @Transactional
    Integer updateMoneyManager(MoneyManagerVo moneyManagerVo);

    @Transactional
    Integer insertMoneyManager(MoneyManagerVo moneyManagerVo);
    //查询总条数
    Integer selectAllMoneyCount(MoneyManagerVo moneyManagerVo);
    Integer selectAllCheckCount(MoneyManagerVo moneyManagerVo);
//    //删除money item信息
//    @Transactional
//    Integer deleteMoneyManagerItem(MoneyManagerVo moneyManagerVo);
//    Long saveSpaceUser(UserInfoVo userInfoVo);
//    Integer insert(UserInfoVo userInfo);

}