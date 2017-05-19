package com.zm.mall.client.service.system;

import com.zm.mall.client.result.system.RoleResult;
import com.zm.mall.client.vo.system.RoleVo;
import com.zm.mall.domain.system.Privileges;
import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */
public interface RoleService {
	List<RoleResult> select(RoleVo roleVo);
	RoleResult selectOne(RoleVo roleVo);
	int updateRole(RoleVo roleVo);
	int insertRole(RoleVo roleVo);
	int deleteEntryByKey(RoleVo roleVo);
	/**
	 * 权限修改 回显
	 * @return
	 */
	List<Privileges> updatePrivilegeRole(RoleVo roleVo);
	/**
	 * 权限修改
	 * @return
	 */
	Integer privilegeRole(Long roleId, String id, String Pluteformid);
}
