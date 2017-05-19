package com.zm.mall.client.result.system;

/**
 * Created by Administrator on 2016/11/30.
 */
public class ActivityTaskResult {
    private String id;
    private String executionid;
    private String name;
    private String assignname;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExecutionid() {
        return executionid;
    }

    public void setExecutionid(String executionid) {
        this.executionid = executionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignname() {
        return assignname;
    }

    public void setAssignname(String assignname) {
        this.assignname = assignname;
    }
}
