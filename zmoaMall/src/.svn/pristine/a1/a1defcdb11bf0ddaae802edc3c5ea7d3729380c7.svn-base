package com.zm.mall.client;

/**
 * User: bjyanglin
 * Date: 14-5-12
 * Time: 上午11:37
 */
public class PageQuery {

    /**
     * 前台传递过来的页码
     */
    private int page;

    /**
     * 前台传递过来的 页面多少条记录
     */
    private int pageSize;

    /**
     * 计算mysql 的偏移量
     * @return
     */
    public int getOffset() {
       return (page-1)*pageSize;
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageQuery{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
