package com.zm.mall.dao.system.impl;






import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.MenusDao;
import com.zm.mall.domain.system.Privileges;
import com.zm.mall.domain.system.Role;

import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public class MenusDaoImpl extends BaseDaoImpl<Privileges> implements MenusDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.MenusDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }


    public List<Privileges> selectAllByContion(Role role) {
        List<Privileges> list =  sqlTemplate.selectList(getNameSpace("selectAllByContion"),role);
        return list;
    }
    @Override
    public List<String> getAllUrls() {
        return sqlTemplate.selectList(getNameSpace("getAllUrls"));
    }

    @Override
    public List<Privileges> getTopPrivilege() {
        return sqlTemplate.selectList(getNameSpace("getTopPrivilege"));
    }
}
