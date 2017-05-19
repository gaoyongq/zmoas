package com.zm.mall.dao.system.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.PrivilegeDao;
import com.zm.mall.domain.system.*;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements PrivilegeDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.PrivilegeDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }


    @Override
    public List<Privilege> selectTop(Privilege privilege) {
        return sqlTemplate.selectList(getNameSpace("selectTop"), privilege);
    }
    @Override
    public List<Privilege> selectNext(Privilege privilege) {
        return sqlTemplate.selectList(getNameSpace("selectNext"), privilege);
    }
    @Override
    public List<Privileges> updatePrivilegeRoleByAll(Role role) {
        return sqlTemplate.selectList(getNameSpace("updatePrivilegeRoleByAll"),role);
    }

    @Override
    public List<Privileges> updatePrivilegeRoleByCode(Department department) {
        return sqlTemplate.selectList(getNameSpace("updatePrivilegeRoleByCode"),department);
    }

    @Override
    public List<Publicprivilege> selPublicprivilegelist() {
        return sqlTemplate.selectList(getNameSpace("selPublicprivilegelist"));
    }

    @Override
    public List<Privilege> select(Privilege privilege) {
        return sqlTemplate.selectList(getNameSpace("select"), privilege);
    }

    @Override
    public int insert(Privilege privilege) {
        return sqlTemplate.insert(getNameSpace("insert"),privilege);
    }

    @Override
    public Privilege selectOne(Privilege privilege) {
        return sqlTemplate.selectOne(getNameSpace("selectOne"),privilege);
    }

    @Override
    public int update(Privilege privilege) {
        return sqlTemplate.update(getNameSpace("update"),privilege);
    }

    @Override
    public int delete(Privilege privilege) {
        sqlTemplate.update(getNameSpace("updatePrivilegeId"),privilege);
        sqlTemplate.delete(getNameSpace("deleteRolePrivilege"),privilege);
        return sqlTemplate.delete(getNameSpace("delete"),privilege);
    }
}

