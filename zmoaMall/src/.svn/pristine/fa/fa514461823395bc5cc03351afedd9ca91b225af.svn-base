package com.zm.mall.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: weiqisong
 * Date: 13-12-11
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
public class BackDaoImpl extends BaseDaoImpl implements BackDao {
    private static final String NAMESPACE = "com.zm.mall.dao.BackDao.";

    public List doSelect(String sql) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("sql",sql);
        return sqlTemplate.selectList(getNameSpace("doSelect"), map);
    }

    public int doUpdate(String sql) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("sql",sql);
        return  sqlTemplate.update(getNameSpace("doUpdate"), map);
    }

    public int doInsert(String sql) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("sql",sql);
        sqlTemplate.insert(getNameSpace("doInsert"), map);
        return  1;
    }

    public int doDelete(String sql) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("sql",sql);
        return sqlTemplate.delete(getNameSpace("doDelete"), map);
    }

    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }
}
