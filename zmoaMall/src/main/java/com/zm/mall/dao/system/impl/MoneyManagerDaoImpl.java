package com.zm.mall.dao.system.impl;

import com.zm.mall.client.result.system.MoneyManagerResult;
import com.zm.mall.client.vo.system.MoneyManagerVo;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.MoneyManagerDao;
import com.zm.mall.domain.system.ActivityTask;
import com.zm.mall.domain.system.MoneyItem;
import com.zm.mall.domain.system.MoneyManager;

import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public class MoneyManagerDaoImpl extends BaseDaoImpl<MoneyManager> implements MoneyManagerDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.MoneyManagerDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public List<MoneyManager> selectMoneyByContion(MoneyManager moneyManager) {
        List<MoneyManager> list =  sqlTemplate.selectList(getNameSpace("selectMoneyByContion"), moneyManager);
        return list;
    }

    @Override
    public List<MoneyManager> selectCheckByContion(MoneyManager moneyManager) {
        List<MoneyManager> list =  sqlTemplate.selectList(getNameSpace("selectCheckByContion"), moneyManager);
        return list;
    }

    @Override
    public List<MoneyItem> selectMoneyItem(Long id) {
        List<MoneyItem>itemList = sqlTemplate.selectList(getNameSpace("selectMoneyItem"), id);
        return itemList;
    }
    /**
     * ɾ�� ��������
     */
    @Override
    public Integer deleteMoneyManager(String id) {
        int result = sqlTemplate.delete(getNameSpace("deleteMoneyManager"),id);
        return result;
    }

    /**
     * ��ѯ����������¼
     * @param
     * @return
     */

    //���²��������Ϣ
    @Override
    public Integer updateMoneyDepCheck(MoneyManager moneyManager) {
       int result =  sqlTemplate.update(getNameSpace("updateMoneyDepCheck"), moneyManager);
        return result;
    }
    //����leader�����Ϣ
    @Override
    public Integer updateMoneyLeaCheck(MoneyManager moneyManager) {
        int result = sqlTemplate.update(getNameSpace("updateMoneyLeaCheck"), moneyManager);
        return result;
    }
    //��ѯ������Task id
    @Override
    public ActivityTask selectTaskId(String exeId) {
//        return sqlTemplate.selectOne(getNameSpace("selectTaskId"), exeId);
        return sqlTemplate.selectOne(getNameSpace("selectTaskId"), exeId);
    }

    /**
     * ����ids��ѯ������
     * @param
     * @return
     */
    @Override
    public List<MoneyManager> selectMoneyManagerById(MoneyManager moneyManager) {
        List<MoneyManager> list =  sqlTemplate.selectList(getNameSpace("selectMoneyManagerById"), moneyManager);
        return list;
    }
    /**
     * �޸ı�������Ϣǰ ɾ��moneyitem��
     */
    @Override
    public Integer deleteMoneyManagerItem(Long id) {
        int result = sqlTemplate.update(getNameSpace("deleteMoneyManagerItem"), id);
        return result;
    }
    /**
     * �޸ı�������Ϣ
     */
    @Override
    public Integer updateMoneyManager(MoneyManager moneyManager) {
        int result = sqlTemplate.update(getNameSpace("updateMoneyManager"), moneyManager);
        return result;
    }
    /**
     * �޸ı�������Ϣ��item��
     * @param
     * @return
     */
    @Override
    public Integer insertMoneyManagerItem(MoneyItem moneyItem) {
        int result = sqlTemplate.update(getNameSpace("insertMoneyManagerItem"), moneyItem);
        return result;
    }

    /**
     * ������������Ϣ
     * @param moneyManager
     * @return
     */
    @Override
    public Integer insertMoneyManager(MoneyManager moneyManager) {
        int result = sqlTemplate.insert(getNameSpace("insertMoneyManager"), moneyManager);
        return result;
    }

    /**
     * ��ѯ�����Ĺ�������id
     * @param statusName
     * @return
     */
    @Override
    public MoneyManager selectMoneyManager(String statusName) {
        MoneyManager moneyManager = sqlTemplate.selectOne(getNameSpace("selectMoneyManager"), statusName);
        return moneyManager;
    }
    /**
     * ��ѯ������
     * @param
     * @return
     */
    @Override
    public Integer selectAllMoneyCount(MoneyManager moneyManager) {
        return sqlTemplate.selectOne(getNameSpace("selectAllMoneyCount"), moneyManager);
    }

    @Override
    public Integer selectAllCheckCount(MoneyManager moneyManager) {
        return sqlTemplate.selectOne(getNameSpace("selectAllCheckCount"), moneyManager);
    }
}
