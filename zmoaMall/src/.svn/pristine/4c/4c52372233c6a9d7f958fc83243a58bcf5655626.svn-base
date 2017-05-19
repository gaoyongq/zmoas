package com.zm.mall.service.user.impl;



import com.zm.mall.client.result.UserInfoResult;
import com.zm.mall.client.service.user.UserInfoService;
import com.zm.mall.client.vo.user.UserInfoVo;
import com.zm.mall.dao.user.UserInfoDao;
import com.zm.mall.domain.user.UserInfo;
import com.zm.mall.service.SpaceBeanCopy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyanshuai on 2016/9/20.
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    private static Log log = LogFactory.getLog(UserInfoService.class);
    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public Integer insert(UserInfoVo userInfoVo) {
        UserInfo userInfo =  SpaceBeanCopy.userInfoVoToUserInfo(userInfoVo);


        return userInfoDao.insert(userInfo);
    }

    /**
     * 根据条件查询用户的信息
     * @param userInfoVo
     * @return
     */
    @Override
    public UserInfoResult selectUserInfo(UserInfoVo userInfoVo) {
        //对象类型转换
        UserInfo userInfo =  SpaceBeanCopy.userInfoVoToUserInfo(userInfoVo);

        //查询数据库是否存在该用户。
        List<UserInfo> list = userInfoDao.selectByContion(userInfo);
        if(list != null && list.size()>0){
            UserInfoResult userInfoResult = SpaceBeanCopy.userInfoToResult(list.get(0));
            return userInfoResult;
        }
        return null;
    }

    /**
     * 保存用户的信息。并返回用户的id
     * @param userInfoVo
     * @return
     */
    @Override
    public Long saveSpaceUser(UserInfoVo userInfoVo) {
        UserInfo userInfo =  SpaceBeanCopy.userInfoVoToUserInfo(userInfoVo);
        if(userInfo != null){
            userInfoDao.insert(userInfo);
            Long userId = userInfo.getId();
            log.debug("保存用户的信息返回用户ID："+userId);
            return userId;
        }
        return null;
    }


}
