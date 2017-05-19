package com.zm.mall.dao.system;

import com.zm.mall.client.PageOfRegister;
import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.system.MoneyItem;
import com.zm.mall.domain.system.MoneyManager;
import com.zm.mall.domain.system.Register;

import java.util.Date;
import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public interface RegisterDao extends BaseDao<Register> {
    void insertRegister(Register register);
    void updateRegister(Register register);
    //查询单个打卡记录
    Register selectRegister(Register register);
    List<Register> selectRegisterAll(Register register);
    //查询所有人本月记录
    List<Register> selectAllRegister(Register register);
    //分页查询
    List<Register> selectAllRegisterByPage(PageOfRegister page);
    //总条数
    Integer selectAllCount(PageOfRegister page);


}
