package com.zm.mall.web;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by bjyanglin on 2014/5/26.
 */
public class BaseController {

    private static Log log = LogFactory.getLog(BaseController.class);

    /**
     * 我们系统内定的一个开发者用户id，针对isv的userId，后期通过isv 上传模板获取
     */
    public static final Long userId=-1L;



    /**
     * 用户是否登录的基本信息，null表示未登录，不为null 可从map里取用户id（key：id），用户姓名（key：pin）等信息
     */
    protected Map<String, String> userInfoMap;


    /**
     * 把结果转换为json格式的字符串
     * @param flag
     * @param msg
     * @return
     */
    protected String convertToJson(boolean flag,String msg){
        StringBuffer json = new StringBuffer();
        json.append("{");
        json.append("\"flag\":" + flag + ",");
        json.append("\"msg\":\"" + msg + "\"");
        json.append("}");
        return json.toString();
    }



    protected Long getShopId(){
//        Long shopId = LoginInfoContenxt.getLoginContext().getShopId();
//        ServletContextUtils context = ServletContextUtils.getContext();
//        context.setShopId(shopId);
//        return shopId;
        return null;
    }

    protected Long getPlatformId() {

        return null;
//        return 2l;
    }

    /**
     * 获得登录时用户id
     * @return
     */
    protected Long getUserId(){

        return null;
//        return 10132l;
//        return 1001085l;
        //return 88888L;
    }

    protected String getUserName() {
        return null;
    }
    /***
     * 获得店铺对应的卖家id
     * @return
     */
    protected Long getSellerId(){

        return null;
        //return 1001085l;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    protected Long getUserType(){
        return null;
    }

}
