package com.zm.mall.domain.system.coupon;

import com.zm.mall.domain.business.product.Pageable;
import com.zm.mall.domain.system.Pagination;

/**
 * Created by Bochao on 2017/4/22.
 */
public class PageableCouponSource extends CouponSource implements Pageable {
    private Long page;
    private Long rows;

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
