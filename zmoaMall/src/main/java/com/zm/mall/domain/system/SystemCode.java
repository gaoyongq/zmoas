package com.zm.mall.domain.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
public class SystemCode implements Serializable {
    private Integer id;
    private Integer pid;
    private String name;
    private String code;
    private String url;
    private String description;
    private String page;
    private String pluteformid;
    private List<ItemSystemCode> itemSystemCodes=new ArrayList<ItemSystemCode>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<ItemSystemCode> getItemSystemCodes() {
        return itemSystemCodes;
    }

    public void setItemSystemCodes(List<ItemSystemCode> itemSystemCodes) {
        this.itemSystemCodes = itemSystemCodes;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
