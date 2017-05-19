package com.zm.mall.client.result.business.accounts;

import com.zm.mall.domain.business.accountsUsers.ItemSystemCode;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/11.
 */
public class SystemCodeResult implements Serializable {
    private Integer id;
    private Integer pid;
    private String code;
    private String name;
    ItemSystemCode ItemSystemCode;
    private String pluteformid;

    public ItemSystemCode getItemSystemCode() {
        return ItemSystemCode;
    }

    public void setItemSystemCode(ItemSystemCode itemSystemCode) {
        ItemSystemCode = itemSystemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
