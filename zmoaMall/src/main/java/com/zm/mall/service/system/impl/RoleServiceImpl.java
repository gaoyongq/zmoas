package com.zm.mall.service.system.impl;

import com.zm.mall.client.result.system.RoleResult;
import com.zm.mall.client.service.system.RoleService;
import com.zm.mall.client.vo.system.RoleVo;
import com.zm.mall.dao.system.MallConfigDao;
import com.zm.mall.dao.system.MenusDao;
import com.zm.mall.dao.system.PrivilegeDao;
import com.zm.mall.dao.system.RoleDao;
import com.zm.mall.domain.system.*;
import com.zm.mall.service.SpaceBeanCopy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	private static Log log= LogFactory.getLog(RoleService.class);
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PrivilegeDao privilegeDao;
	@Autowired
	private MenusDao menusDao;
	@Autowired
	private MallConfigDao mallConfigDao;
	@Override
	public int updateRole(RoleVo roleVo) {
		Role role= SpaceBeanCopy.roleVoToRole(roleVo);
		return roleDao.update(role);
	}

	@Override
	public int insertRole(RoleVo roleVo) {
		Role role= SpaceBeanCopy.roleVoToRole(roleVo);
		return roleDao.insert(role);
    }


	@Override
	public List<RoleResult> select(RoleVo roleVo) {
		Role role= SpaceBeanCopy.roleVoToRole(roleVo);
		List<Role> roles= roleDao.select(role);
		if(roles!=null&&roles.size()>0){
			List<RoleResult> roleResults=new ArrayList<RoleResult>();
			for(Role rr:roles){
				RoleResult roleResult= SpaceBeanCopy.roleToResult(rr);
				roleResults.add(roleResult);
			}
			return roleResults;
		}
		return null;
	}

	@Override
	public int deleteEntryByKey(RoleVo roleVo) {
		Role role=SpaceBeanCopy.roleVoToRole(roleVo);
		return roleDao.delete(role);
	}

	/**
	 * 权限修改
	 * @return
	 */
	@Override
	@Transactional
	public List<Privileges> updatePrivilegeRole(RoleVo roleVo) {
		Role role=SpaceBeanCopy.roleVoToRole(roleVo);
		List<Privileges> list;
		//全部权限集合
		if("-1".equals(role.getPluteformid())){
			list = privilegeDao.updatePrivilegeRoleByAll(role);
		}else{
			MallConfig mallConfig = new MallConfig();
			mallConfig.setPid(role.getPluteformid());
			MallConfig mallConfigResult = mallConfigDao.selSecretkeyByAppId(mallConfig);
			Department Department = new Department();
			Department.setPluteformid(mallConfigResult.getParentpId());
			Department.setCode("ZM_SH");
			list = privilegeDao.updatePrivilegeRoleByCode(Department);
		}
		//已有权限集合
		List<Privileges> roleList = menusDao.selectAllByContion(role);
		for(int i =0;i<list.size();i++){
			for(int j = 0;j<roleList.size();j++){
				//版断ID是否相同 ，回显已有权限
				if(list.get(i).getId().equals(roleList.get(j).getId())){
					list.get(i).setFlag(true);
					list.get(i).setOpen(true);
				}
			}
		}
		return list;
	}
	/**
	 * 权限修改
	 * @return
	 */
	@Override
	public Integer privilegeRole(Long roleId, String Id,String Pluteformid) {
		try{
			//删除原有权限
			Role role = new Role();
			role.setId(roleId);
//			role.setPluteformid(Pluteformid);
			roleDao.delPrivilegeRole(role);
			//添加新权限

			if(!"".equals(Id) && Id!=null){
				String[] id = Id.split(",");
				for(int i=0;i<id.length;i++){
					PrivilegeRole privilegeRole = new PrivilegeRole();
					privilegeRole.setRolePrivilege(roleId);
					privilegeRole.setPrivilegeId(Long.parseLong(id[i]));
					privilegeRole.setPluteformid(Pluteformid);
					roleDao.insertPrivilegeRole(privilegeRole);
				}
			}
			return 1;
		}catch (Exception e){
			return 0;
		}
	}
	@Override
	public RoleResult selectOne(RoleVo roleVo) {
		Role role=SpaceBeanCopy.roleVoToRole(roleVo);
		Role r=roleDao.selectOne(role);
		RoleResult roleResult=SpaceBeanCopy.roleToResult(r);
		return roleResult;
	}
}
