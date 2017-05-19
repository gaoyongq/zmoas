package com.zm.mall.dao.system.systemCode;

import com.zm.mall.domain.system.systemCode.DeptSystemCode;
import com.zm.mall.client.Page;
import java.util.List;

/**
 * 部门编码Dao接口
 * Created by Bochao on 2017/2/23.
 */
public interface DeptSystemCodeDao {

    /**
     * 更新部门代码记录 b2c_item_system_code
     * @param deptSystemCode 部门代码实体类
     * @return 更新的记录数
     */
    int updateDeptCode(DeptSystemCode deptSystemCode);

    /**
     * 新增部门代码记录 b2c_item_system_code
     * @param deptSystemCode 部门代码实体类
     * @return 更新的记录数
     */
    int insertDeptCode(DeptSystemCode deptSystemCode);

    /**
     * 更新部门code值
     * @param deptSystemCode 部门代码实体类
     * @return 更新的记录数
     */
    int updateDept(DeptSystemCode deptSystemCode);

    /**
     * 按照部门的上级部门id获取部门编码
     * @param platformId
     * @param deptParentId
     * @return
     */
    List<DeptSystemCode> getDeptCodeListByDeptParentId(String platformId, Long deptParentId);

}
