package com.zm.mall.client.service.user;



import com.zm.mall.client.result.UserInfoResult;
import com.zm.mall.client.vo.user.UserInfoVo;

import java.util.Map;

/**
 * Created by liyanshuai on 2016/9/20.
 */
public interface UserInfoService {
    Integer insert(UserInfoVo userInfo);

    /**
     * 根据用户的条件查询用户的信息
     * @param userInfoVo
     * @return
     */
    UserInfoResult selectUserInfo(UserInfoVo userInfoVo);

    /**
     * 进行用户的信息保存，返回是用的id
     * @param userInfoVo
     * @return
     */
    Long saveSpaceUser(UserInfoVo userInfoVo);


}
