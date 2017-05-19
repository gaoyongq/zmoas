package com.zm.mall.dao.system.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.DepartmentDao;
import com.zm.mall.domain.system.Department;

import java.util.List;

/**
 * Created by Administrator on 2016/11/19.
 */
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {
	private final static String NAMESPACE = "com.zm.mall.dao.system.DepartmentDao.";

	@Override
	public String getNameSpace(String statement) {
		return NAMESPACE+statement;
	}
	@Override
	public List<Department> select(Department department) {
		return sqlTemplate.selectList(getNameSpace("select"), department);
	}

	@Override
	public List<Department> selectTop(Department department) {
		return sqlTemplate.selectList(getNameSpace("selectTop"), department);
	}
	@Override
	public List<Department> selectNext(Department department) {
		return sqlTemplate.selectList(getNameSpace("selectNext"), department);
	}

	@Override
	public List<Department> selectRoleByDepartment(Department department) {
		return sqlTemplate.selectList(getNameSpace("selectRoleByDepartment"),department);
	}

	@Override
	public Department selectWX(Department department) {
		return sqlTemplate.selectOne(getNameSpace("selectWX"),department);
	}


	@Override
	public Department selectOne(Department department) {
		return sqlTemplate.selectOne(getNameSpace("selectOne"),department);
	}

	@Override
	public int update(Department department) {
		return sqlTemplate.update(getNameSpace("update"),department);
	}

	@Override
	public int insert(Department department) {
		return sqlTemplate.insert(getNameSpace("insert"),department);
	}

	@Override
	public int delete(Department department) {
		sqlTemplate.update(getNameSpace("updateDepartmentId"),department);
		sqlTemplate.update(getNameSpace("updateUser"),department);
		sqlTemplate.update(getNameSpace("updateRole"),department);
		return sqlTemplate.delete(getNameSpace("delete"),department);
	}

}
