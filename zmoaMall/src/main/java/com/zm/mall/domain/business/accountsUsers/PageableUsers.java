package com.zm.mall.domain.business.accountsUsers;

import com.zm.mall.domain.business.product.Pageable;

/**
 * Created by HP on 2017/5/8.
 */
public class PageableUsers extends Users implements Pageable {
    /**
     * 页码
     */
    private Long page;

    /**
     * 每页显示行数
     */
    private Long rows;

    private String filterName;

    private String filterValue;

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    @Override
    public Long getStart() {
        if (page == null || page < 1) return 0L;
        return (page - 1) * rows;
    }

    @Override
    public Long getSize() {
        if (rows == null) return Long.MAX_VALUE;
        return rows;
    }
}
