package com.zm.mall.dao.user.impl;

import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.user.UserInfoDao;
import com.zm.mall.domain.user.UserInfo;
import java.util.List;

/**
 * Created by liyanshuai on 2016/11/9.
 */
public class UserDaoInfoImpl extends BaseDaoImpl<UserInfo> implements UserInfoDao {
    private final static String NAMESPACE = "com.zm.mall.dao.user.UserInfoDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public List<UserInfo> selectByContion(UserInfo userInfo) {
        return sqlTemplate.selectList(getNameSpace("selectByContion"), userInfo);
    }

}
