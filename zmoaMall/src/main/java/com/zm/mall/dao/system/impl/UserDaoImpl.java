package com.zm.mall.dao.system.impl;




import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.UserDao;
import com.zm.mall.domain.system.MallConfig;
import com.zm.mall.domain.system.User;
import com.zm.mall.domain.system.userPage;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12.
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    private final static String NAMESPACE = "com.zm.mall.dao.user.UserDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }
    @Override
    public Integer updatePass(User user) {
        return sqlTemplate.update(getNameSpace("updatePass"),user);
    }

    @Override
    public List<User> select(User user) {
        return sqlTemplate.selectList(getNameSpace("select"),user);
    }

    @Override
    public User login(User user) {
        return sqlTemplate.selectOne(getNameSpace("login"),user);
    }
    @Override
    public User selectOne(User user) {
        return sqlTemplate.selectOne(getNameSpace("selectOne"),user);
    }
    @Override
    public int update(User user) {
        return sqlTemplate.update(getNameSpace("update"), user);
    }
    @Override
    public int insert(User user) {
        return sqlTemplate.insert(getNameSpace("insertEntry"), user);
    }

    @Override
    public Integer updateState(User user) {
        return sqlTemplate.update(getNameSpace("updateState"),user);
    }
    @Override
    public Integer updateShopState(User user) {
        return sqlTemplate.update(getNameSpace("updateShopState"),user);
    }
    @Override
    public List<User> getPurchaser(User user) {
        return sqlTemplate.selectList(getNameSpace("getPurchaser"),user);
    }

    @Override
    public List<User> selectAllPhysicalNoEmploy() {
        List<User> list =  sqlTemplate.selectList(getNameSpace("selectAllPhysicalNoEmploy"));
        return list;
    }

    @Override
    public Integer insertRoleUser(User user) {
        return sqlTemplate.insert(getNameSpace("insertRoleUser"),user);
    }

    @Override
    public Integer selectUserCount(userPage userPage) {
        Integer a=sqlTemplate.selectOne(getNameSpace("selectUserCount"),userPage);
        return a;
    }

    @Override
    public List<User> selectByLimit(userPage userPage) {
        return sqlTemplate.selectList(getNameSpace("select"),userPage);
    }

    @Override
    public User selectWX(User user) {
        return sqlTemplate.selectOne(getNameSpace("selectWX"),user);
    }

    @Override
    public Integer insertUserWX(User user) throws  Exception{
        return sqlTemplate.insert(getNameSpace("insertUserWX"),user);
    }

    @Override
    public User checkPhoneNum(User user) {
        return sqlTemplate.selectOne(getNameSpace("checkPhoneNum"),user);
    }

    //根据商户查询用户
    @Override
    public User selUserBypId(MallConfig mallConfig) {
        return sqlTemplate.selectOne(getNameSpace("selUserBypId"), mallConfig);
    }
    @Override
    public Integer updatePassWord(User  user) {
        return sqlTemplate.update(getNameSpace("updatePassWord"),user);
    }
    //开店铺增加手机号
    @Override
    public Integer addPhone(User user) {
        return sqlTemplate.update(getNameSpace("addPhone"),user);
    }
    //完善店普下用户的资料
    @Override
    public Integer perfectUser(User user) {
        return sqlTemplate.update(getNameSpace("perfectUser"),user);
    }
}
