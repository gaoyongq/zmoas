package com.zm.mall.domain.business.product;

/**
 * Created by Bochao on 2017/4/11.
 */
public interface Pageable {
    /**
     * MySQL分页语法limit所需第一个参数
     */
    Long getStart();

    /**
     * MySQL分页语法limit所需第二个参数
     */
    Long getSize();
}
