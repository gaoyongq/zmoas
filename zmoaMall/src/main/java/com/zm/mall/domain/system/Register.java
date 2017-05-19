package com.zm.mall.domain.system;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/22.
 */
public class Register {
    private Long id;
    private String username;
    private Long userid;
    private Date createtime;
    private Date updatetime;
    private String mstatus;
    private String estatus;
    private String flagtime1;
    private String flagtime2;

    private String createtimepage;
    private String updatetimepage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getMstatus() {
        return mstatus;
    }

    public void setMstatus(String mstatus) {
        this.mstatus = mstatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getFlagtime1() {
        return flagtime1;
    }

    public void setFlagtime1(String flagtime1) {
        this.flagtime1 = flagtime1;
    }

    public String getFlagtime2() {
        return flagtime2;
    }

    public void setFlagtime2(String flagtime2) {
        this.flagtime2 = flagtime2;
    }

    public String getCreatetimepage() {
        return createtimepage;
    }

    public void setCreatetimepage(String createtimepage) {
        this.createtimepage = createtimepage;
    }

    public String getUpdatetimepage() {
        return updatetimepage;
    }

    public void setUpdatetimepage(String updatetimepage) {
        this.updatetimepage = updatetimepage;
    }
}
