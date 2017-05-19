package com.zm.mall.domain.system;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @author liyanshuai
 * @since 2016-02-01
 * @param <T> 需要分页的实体
 *
 * @modify liyanshuai(zhaobaoxin)
 *
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -8895742541737938908L;
    /**
     * 总数
     */
    private int totalCount;
    /**
     * 页大小
     */
    private int pageSize;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 内容对象
     */
    private T t;

    private List<T> result;

    public Page() {

    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Page(List<T> result) {
        this.result = result;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}