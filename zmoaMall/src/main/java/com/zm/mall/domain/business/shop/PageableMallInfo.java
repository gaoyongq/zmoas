package com.zm.mall.domain.business.shop;

import com.zm.mall.client.vo.system.MallConfigVo;
import com.zm.mall.domain.business.product.Pageable;

/**
 * Created by hh on 2017/5/4.
 */
public class PageableMallInfo extends MallConfigVo implements Pageable {
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

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
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
