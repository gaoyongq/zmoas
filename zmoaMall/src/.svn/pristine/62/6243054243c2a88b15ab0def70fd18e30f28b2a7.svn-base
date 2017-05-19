package com.zm.mall.dao.system.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.SystemCodeDao;
import com.zm.mall.domain.system.SystemCode;

import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public class SystemCodeDaoImpl extends BaseDaoImpl<SystemCode> implements SystemCodeDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.SystemCodeDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public List<SystemCode> selectByContion(SystemCode systemCode) {
        return sqlTemplate.selectList(getNameSpace("selectByContion"), systemCode);
    }


    @Override
    public void addMenu(SystemCode systemCode) {
        sqlTemplate.insert(getNameSpace("insertByPid"), systemCode);

        /*List<SystemCode> list= sqlTemplate.selectList(getNameSpace("selectByCode"), systemCode);
        int id= list.get(0).getId();
        String page ="/shopBase/menu.action?id="+id;
        systemCode.setPage(page);
        sqlTemplate.insert(getNameSpace("insertPage"), systemCode);*/
    }

    @Override
    public void updateMenu(SystemCode systemCode) {

        sqlTemplate.update(getNameSpace("updateByKey"), systemCode);
    }

    @Override
    public void deleteMenu(SystemCode systemCode) {

        sqlTemplate.delete(getNameSpace("deleteByKey"), systemCode
        );
    }

    public List<SystemCode> selectAllByContion(SystemCode systemCode) {
        List<SystemCode> list =  sqlTemplate.selectList(getNameSpace("selectAllByContion"), systemCode);

        return list;
    }

    @Override
    public SystemCode getCodeUserId(String code) throws Exception {
        return sqlTemplate.selectOne(getNameSpace("getCodeUserId"),code);
    }

}
