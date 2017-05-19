package com.zm.mall.domain.business.product;

/**
 * Created by Bochao on 2017/3/22.
 */
public class PaginationCategoryInfo extends CategoryInfo {
    /**
     * 用于EasyUI树形展现异步加载子分类
     */
    private Long id;
    /**
     * 页码
     */
    private Long page;

    /**
     * 每页显示行数
     */
    private Long rows;

    /**
     * MySQL分页语法limit所需第一个参数，不需要计算
     */
    private Long getStart() {
        if (page == null || page < 1) return 0L;
        return (page - 1) * rows;
    }

    /**
     * MySQL分页语法limit所需第二个参数，不需要计算
     */
    private Long getSize() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
