package com.zm.mall.domain.system.systemCode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

/**
 * 部门代码类
 * Created by Bochao on 2017/2/23.
 */
public class DeptSystemCode {
    private Long id; //部门表唯一id
    private Long codeId; //编码表唯一id
    @JsonProperty("parentId")
    private long deptParentId; //父部门id
    private String state; //用在EasyUI中表示Tree的打开状态
    private String platformId; //平台id
    @JsonIgnore
    private Long codeParentId; //编码所属分类菜单
    private String name; //部门名称和代码名称
    private String code; //部门代码标识
    private String description; //部门代码描述
    @JsonIgnore
    private List<DeptSystemCode> subDeptSystemCodeList; //下级部门代码

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public long getDeptParentId() {
        return deptParentId;
    }

    public void setDeptParentId(long deptParentId) {
        this.deptParentId = deptParentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public Long getCodeParentId() {
        return codeParentId;
    }

    public void setCodeParentId(Long codeParentId) {
        this.codeParentId = codeParentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<DeptSystemCode> getSubDeptSystemCodeList() {
        return subDeptSystemCodeList;
    }

    public void setSubDeptSystemCodeList(List<DeptSystemCode> subDeptSystemCodeList) {
        this.subDeptSystemCodeList = subDeptSystemCodeList;
    }
}
