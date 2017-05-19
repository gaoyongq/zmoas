package com.zm.mall.dao.system.systemCode.impl;

import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.systemCode.DeptSystemCodeDao;
import com.zm.mall.domain.system.systemCode.DeptSystemCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门编码Dao实现类
 * Created by Bochao on 2017/2/23.
 */
public class DeptSystemCodeDaoImpl extends BaseDaoImpl<DeptSystemCode> implements DeptSystemCodeDao {

    private final static String NAMESPACE = "com.zm.mall.dao.system.systemCode.DeptSystemCodeDao.";

    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

    @Override
    public int updateDeptCode(DeptSystemCode deptSystemCode) {
        return sqlTemplate.update(getNameSpace("updateDeptCode"), deptSystemCode);
    }

    @Override
    public int insertDeptCode(DeptSystemCode deptSystemCode) {
        return sqlTemplate.insert(getNameSpace("insertDeptCode"), deptSystemCode);
    }

    @Override
    public int updateDept(DeptSystemCode deptSystemCode) {
        return sqlTemplate.update(getNameSpace("updateDept"), deptSystemCode);
    }

    @Override
    public List<DeptSystemCode> getDeptCodeListByDeptParentId(String platformId, Long deptParentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformId", platformId);
        map.put("deptParentId", deptParentId);
        return sqlTemplate.selectList(getNameSpace("getDeptCodeListByDeptParentId"), map);
    }


}
