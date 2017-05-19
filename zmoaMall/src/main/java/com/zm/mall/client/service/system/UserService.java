package com.zm.mall.client.service.system;



import com.zm.mall.client.result.system.MallConfigResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.vo.system.MallConfigVo;
import com.zm.mall.client.vo.system.UserVo;
import com.zm.mall.domain.system.MallConfig;
import com.zm.mall.domain.system.User;
import com.zm.mall.domain.system.userPage;

import java.util.List;

/**
 * Created by Administrator on 2016/11/13.
 */
public interface UserService {

    UserResult login(UserVo userVo);
    List<UserResult> select(UserVo userVo);
    UserResult selectOne(UserVo userVo);
    int updateUser(UserVo userVo);
    int updatePass(UserVo userVo);
    int insertUser(UserVo userVo);
    int addOpenShop(UserVo userVo);
    int updateState(UserVo userVo);
    int updateShopState(UserVo userVo);
    List <User> selectPurchaser(UserVo userVo);
    List<User> selectAllPhysicalNoEmploy();
    Integer userCountAll(userPage userPage);
    List<User> getUsers(userPage userPage);
    //微信查询
    User selectWX(User  user);
    //微信注册商城用户
    Integer insertUserWX(User user)throws  Exception;
    int checkPhoneNum(UserVo userVo);
    //开启商户完善资料
    UserResult openAddShopData(MallConfigVo mallConfigVo)throws Exception;
    //根据Appid查询Secretkey
    MallConfigResult selSecretkeyByAppId(MallConfigVo mallConfigVo);
    //修改密码
    int updatePassWord(UserVo userVo);
    //开店铺增加手机号
    Integer addPhone(UserVo userVo);
    //微信注册店铺用户
    Integer insertUserPartShop(User user)throws  Exception;
    //查询用户信息
    User selUserBypId(MallConfig mallConfig);
}
