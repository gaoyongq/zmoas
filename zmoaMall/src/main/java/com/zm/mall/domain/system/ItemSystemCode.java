package com.zm.mall.domain.system;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/11.
 */
public class ItemSystemCode implements Serializable {
    private Integer id;
    private Integer fid;
    private String  bumber;
    private String  name;
    private String code;
    private String description;
    private Double value;
    private Double percentage;
    private String pluteformid;

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

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getBumber() {
        return bumber;
    }

    public void setBumber(String bumber) {
        this.bumber = bumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
