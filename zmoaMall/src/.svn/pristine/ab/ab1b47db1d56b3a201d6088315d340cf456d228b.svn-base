package com.zm.mall.dao.system.impl;



import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.ItemSystemCodeDao;
import com.zm.mall.domain.system.ItemSystemCode;

import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public class ItemSystemCodeDaoImpl extends BaseDaoImpl<ItemSystemCode> implements ItemSystemCodeDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.ItemSystemCodeDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public List<ItemSystemCode> selectByContion(ItemSystemCode itemSystemCode) {
        return sqlTemplate.selectList(getNameSpace("selectByFid"), itemSystemCode);
    }


    public List<ItemSystemCode> selectByLimit(Page page) {
        return sqlTemplate.selectList(getNameSpace("selectByLimit"), page);
    }




    public Integer selectAllCount(Page page) {

        return sqlTemplate.selectOne(getNameSpace("selectAllCount"),page);
    }

    @Override
    public void addItemSystemCode(ItemSystemCode itemSystemCode) {
        sqlTemplate.insert(getNameSpace("insertItemSystemCode"), itemSystemCode);
    }
    @Override
    public void updateItemSystemCode(ItemSystemCode itemSystemCode) {
        sqlTemplate.update(getNameSpace("updateItemSystemCode"), itemSystemCode);
    }



    /*@Override
    public void addMenu(SystemCode systemCode) {
        sqlTemplate.insert(getNameSpace("insertByPid"), systemCode);

        List<SystemCode> list= sqlTemplate.selectList(getNameSpace("selectByCode"), systemCode);
       int id= list.get(0).getId();
        String page ="/shopBase/menu.action?id="+id;
        systemCode.setPage(page);
        sqlTemplate.insert(getNameSpace("insertPage"), systemCode);
    }

    @Override
    public void updateMenu(SystemCode systemCode) {
        sqlTemplate.update(getNameSpace("updateByKey"), systemCode);
    }

    @Override
    public void deleteMenu(SystemCode systemCode) {
        sqlTemplate.delete(getNameSpace("deleteByKey"), systemCode);
    }

    public List<SystemCode> selectAllByContion(SystemCode systemCode) {
        List<SystemCode> list =  sqlTemplate.selectList(getNameSpace("selectAllByContion"), systemCode);

        return list;
    }*/


}
