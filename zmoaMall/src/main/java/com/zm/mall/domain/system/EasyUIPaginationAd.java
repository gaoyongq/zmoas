package com.zm.mall.domain.system;

/**
 * EasyUI分页接参
 * Created by Bochao on 2017/3/17.
 */
public class EasyUIPaginationAd extends Ad {
    /**
     * 当前页码
     */
    private Long page;

    /**
     * 每页记录数
     */
    private Long rows;

    /**
     * 获取MySQL分页limit第一个参数
     */
    public Long getMySqlLimitStart() {
        if (page == null || page < 1) return 0L;
        return (page - 1) * rows;
    }

    /**
     * 获取MySQL分页limit第二个参数
     */
    public Long getMySqlLimitSize() {
        if (rows == null) return Long.MAX_VALUE;
        return rows;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }
}
