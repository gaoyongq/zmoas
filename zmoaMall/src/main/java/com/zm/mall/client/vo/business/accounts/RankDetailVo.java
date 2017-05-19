package com.zm.mall.client.vo.business.accounts;

import com.zm.mall.domain.business.accountsUsers.PointsRule;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/11.
 */
public class RankDetailVo {

    private PointsRule pointsRule;
    private Integer detailId;

    private  Integer ruleId;

    private Integer userID;

    private Integer score;

    private String extData;

    private String description;

    private Date createdDate;

    private Integer type;
    private String pluteformid;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getExtData() {return extData;}

    public void setExtData(String extData) {
        this.extData = extData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public PointsRule getPointsRule() {
        return pointsRule;
    }

    public void setPointsRule(PointsRule pointsRule) {
        this.pointsRule = pointsRule;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }
}
