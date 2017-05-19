package com.zm.mall.dao.system.impl;

import com.zm.mall.client.PageOfRegister;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.RegisterDao;
import com.zm.mall.domain.system.Register;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */
public class RegisterDaoImpl extends BaseDaoImpl<Register> implements RegisterDao{
    private final static String NAMESPACE = "com.zm.mall.dao.system.RegisterDao.";

    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public void insertRegister(Register register) {
        sqlTemplate.insert(getNameSpace("insertRegister"),register);
    }

    @Override
    public void updateRegister(Register register) {
        sqlTemplate.update(getNameSpace("updateRegister"), register);
    }

    @Override
    public Register selectRegister(Register register) {
//        List<MoneyManager> list =  sqlTemplate.selectList(getNameSpace("selectAllByContion"), moneyManager);
//        return list;
//getNameSpace("selectAllByContion"),register);
        Register reg = sqlTemplate.selectOne(getNameSpace("selectRegister"),register);
        return reg;
    }

    @Override
    public List<Register> selectRegisterAll(Register register) {
        List<Register> list = sqlTemplate.selectList(getNameSpace("selectRegisterAll"),register);
        return list;
    }

    @Override
    public List<Register> selectAllRegister(Register register) {
        List<Register> list = sqlTemplate.selectList(getNameSpace("selectAllRegister"),register);
        return list;
    }

    /**
     * 查询总条数
     * @param page
     * @return
     */
    @Override
    public Integer selectAllCount(PageOfRegister page) {

        return sqlTemplate.selectOne(getNameSpace("selectAllCount"), page);
    }
    /**
     * 查询总一页的信息
     * @param page
     * @return
     */
    @Override
    public List<Register> selectAllRegisterByPage(PageOfRegister page) {
        return sqlTemplate.selectList(getNameSpace("selectAllRegisterByPage"), page);
    }
}
