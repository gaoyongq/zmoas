package com.zm.mall.domain.business.accountsUsers;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/9.
 */
public class UserLog {
    private Integer id;
    private Date opTime;
    private String url;
    private String opInfo;
    private String userName;
    private String userType;
    private String userIp;
    private String pluteformid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpInfo() {
        return opInfo;
    }

    public void setOpInfo(String opInfo) {
        this.opInfo = opInfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
