package com.zm.mall.dao.system;


import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.system.MallConfig;
import com.zm.mall.domain.system.User;
import com.zm.mall.domain.system.userPage;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12.
 */
public interface UserDao extends BaseDao<User> {
    User login(User user);
    Integer updatePass(User user);
    Integer updateState(User user);
    Integer updateShopState(User user);
    List  getPurchaser(User user);
    List<User> selectAllPhysicalNoEmploy();
    Integer insertRoleUser(User user);
    //分页
    Integer selectUserCount(userPage userPage);
    List<User> selectByLimit(userPage userPage);
    //微信查询
    User selectWX(User  User);
    //微信注册
    Integer insertUserWX (User user)throws  Exception;
    //手机号校验
    User checkPhoneNum(User user);
    //根据商户查询用户
    User selUserBypId(MallConfig mallConfig);
    //修改密码
    Integer updatePassWord(User  user);
    //开店铺增加手机号
    Integer addPhone(User user);
    //完善店普下用户的资料
    Integer perfectUser(User user);
}
