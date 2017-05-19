package com.zm.mall.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: weiqisong
 * Date: 13-12-11
 * Time: 下午4:22
 * To change this template use File | Settings | File Templates.
 */
public interface BackDao {
    List doSelect(String sql);

    int doUpdate(String sql);

    int doInsert(String sql);

    int doDelete(String sql);
}
