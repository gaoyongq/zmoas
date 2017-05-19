package com.zm.mall.common.utils;

import com.zm.mall.client.result.system.UserResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lp on 2017/2/21.
 */
public class SessionGetPlUteFromId {

    public static UserResult FromSessionGetPlUteFromId(HttpServletRequest request){
        UserResult user=(UserResult)request.getSession().getAttribute("userResult");
        if ("-2".equals(user.getPluteformid())){
            user.setPluteformid(user.getParentPluteformId());
        }
        return user;
    }
}
