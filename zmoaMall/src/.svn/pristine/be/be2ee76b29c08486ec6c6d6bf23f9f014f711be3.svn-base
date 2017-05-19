package com.zm.mall.dao.system.impl;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.RoleDao;
import com.zm.mall.domain.system.PrivilegeRole;
import com.zm.mall.domain.system.Role;

import java.util.List;
/**
 * Created by Administrator on 2016/11/15.
 */
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
	private final static String NAMESPACE = "com.zm.mall.dao.role.RoleDao.";
	@Override
	public String getNameSpace(String statement) {
		return  NAMESPACE+statement;
	}
	@Override
	public List<Role> select(Role role) {
		try{
			return sqlTemplate.selectList(getNameSpace("select"), role);
		}catch(Throwable t){
			t.printStackTrace();
		}
		return null;

	}
	@Override
	public Role selectOne(Role role) {
		return sqlTemplate.selectOne(getNameSpace("selectOne"),role);
	}
	@Override
	public int update(Role role) {
		return sqlTemplate.update(getNameSpace("update"), role);
	}
	@Override
	public int insert(Role role) {
		return sqlTemplate.insert(getNameSpace("insertRole"), role);
	}
	@Override
	public int delete(Role role) {
		sqlTemplate.delete(getNameSpace("deleteRoleUser"), role);
		sqlTemplate.delete(getNameSpace("deleteRolePrivilege"),role);
		return sqlTemplate.delete(getNameSpace("deleteEntryByKey"), role);
	}
	@Override
	public int delPrivilegeRole(Role role) {
		return sqlTemplate.delete(getNameSpace("deleteRolePrivilege"),role);
	}

	@Override
	public int insertPrivilegeRole(PrivilegeRole privilegeRole) {
		return sqlTemplate.insert(getNameSpace("insertPrivilegeRole"), privilegeRole);
	}

}
