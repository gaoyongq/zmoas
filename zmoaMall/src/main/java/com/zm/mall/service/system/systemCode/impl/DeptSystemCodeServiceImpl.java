package com.zm.mall.service.system.systemCode.impl;

import com.zm.mall.dao.system.systemCode.DeptSystemCodeDao;
import com.zm.mall.domain.system.systemCode.DeptSystemCode;
import com.zm.mall.service.system.systemCode.DeptSystemCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门编码Service实现类
 * Created by Bochao on 2017/2/23.
 */
@Service("deptSystemCodeService")
public class DeptSystemCodeServiceImpl implements DeptSystemCodeService {
    @Resource
    private DeptSystemCodeDao deptSystemCodeDao;

    @Transactional
    @Override
    public int updateDeptCode(DeptSystemCode deptSystemCode) {
        int i;
        if (deptSystemCode.getCodeId() == null) {
            i = deptSystemCodeDao.insertDeptCode(deptSystemCode);
        } else {
            i = deptSystemCodeDao.updateDeptCode(deptSystemCode);
        }
        int j = deptSystemCodeDao.updateDept(deptSystemCode);

        return i&j;
    }

    @Override
    public List<DeptSystemCode> getDeptCodeListByDeptParentId(String platformId, Long deptParentId) {
        List<DeptSystemCode> deptSystemCodes =
                deptSystemCodeDao.getDeptCodeListByDeptParentId(platformId, deptParentId);

        for (DeptSystemCode deptSystemCode : deptSystemCodes) {
            if (deptSystemCode.getCode() == null) {
                deptSystemCode.setCode("[未设置]");
            }
            if (deptSystemCode.getDescription() == null) {
                deptSystemCode.setDescription("[未设置]");
            }
            if (deptSystemCode.getSubDeptSystemCodeList() != null
                    && !deptSystemCode.getSubDeptSystemCodeList().isEmpty()) {
                deptSystemCode.setState("closed");
            }
        }
        return deptSystemCodes;
    }

}
