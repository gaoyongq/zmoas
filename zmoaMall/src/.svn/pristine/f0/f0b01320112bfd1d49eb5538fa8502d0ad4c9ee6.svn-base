package com.zm.mall.dao.system;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.system.SystemCode;

import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public interface SystemCodeDao extends BaseDao<SystemCode> {

    /**
     * 根据条件查询用户的信息
     * @return
     */
    List<SystemCode> selectByContion(SystemCode systemCode);
    List<SystemCode> selectAllByContion(SystemCode systemCode);
    void deleteMenu(SystemCode systemCode);
    void updateMenu(SystemCode systemCode);
    void addMenu(SystemCode systemCode);
    public SystemCode getCodeUserId(String code) throws Exception;

}
