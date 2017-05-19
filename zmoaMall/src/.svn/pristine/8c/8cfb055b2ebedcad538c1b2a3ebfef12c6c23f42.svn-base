package com.zm.mall.dao.system;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.system.*;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */
public interface PrivilegeDao extends BaseDao<Privilege> {
    public List<Privilege> selectTop(Privilege privilege);
    public List<Privilege> selectNext(Privilege privilege);
    /**
     * 修改权限
     * @return
     */
    public List<Privileges> updatePrivilegeRoleByAll(Role role);
    public List<Privileges> updatePrivilegeRoleByCode(Department department);


    /**
     * 查询公用权限
     * @return
     */
    public List<Publicprivilege> selPublicprivilegelist();
}
