package com.zm.mall.service.system.systemCode;

import com.zm.mall.domain.system.systemCode.DeptSystemCode;

import java.util.List;

/**
 * 部门编码Service接口
 * Created by Bochao on 2017/2/23.
 */
public interface DeptSystemCodeService {

    /**
     * 如果数据库已经有记录就修改，如果没有就新增。
     * @param deptSystemCode
     * @return
     */
    int updateDeptCode(DeptSystemCode deptSystemCode);

    /**
     * 获得指定父部门下的所有子部门
     * @param platformId 平台id
     * @param deptParentId 父部门id
     * @return
     */
    List<DeptSystemCode> getDeptCodeListByDeptParentId(String platformId, Long deptParentId);
}
