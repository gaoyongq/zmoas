package com.zm.mall.client.vo.business.product;

import java.util.Date;

/**
 * 收藏类
 * Created by lp on 2017/3/6.
 */
public class FavoriteInfoVo {
    private Integer favoriteId;
    private Integer type;
    private Long targetId;
    private Integer userId;
    private String tags;
    private String remark;
    private Date createdDate;
    private String[] favoritestr;

    public String[] getFavoritestr() {
        return favoritestr;
    }

    public void setFavoritestr(String[] favoritestr) {
        this.favoritestr = favoritestr;
    }

    private String pluteformid;

    public FavoriteInfoVo() {
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
