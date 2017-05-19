package com.zm.mall.dao.system;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.system.Department;

import java.util.List;

/**
 * Created by Administrator on 2016/11/19.
 */
public interface DepartmentDao extends BaseDao<Department> {
    public List<Department> selectTop(Department department);
    public List<Department> selectNext(Department department);
    List<Department> selectRoleByDepartment(Department department);
    public Department selectWX(Department department);
}
