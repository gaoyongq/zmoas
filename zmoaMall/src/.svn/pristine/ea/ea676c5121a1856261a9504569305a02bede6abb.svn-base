package com.zm.mall.dao.business.product.impl;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.product.ThumbnailSizeDao;
import com.zm.mall.domain.business.product.ThumbnailSize;

import java.util.List;

/**
 * Created by lp on 2016/12/8.
 */
public class ThumbnailSizeDaoImpl extends BaseDaoImpl<ThumbnailSize> implements ThumbnailSizeDao {
    private final static String NAMESPACE = "com.zm.mall.dao.business.product.ThumbnailSizeDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public List<ThumbnailSize> selThumbnailSizeAllByProduct() {
        return sqlTemplate.selectList("selThumbnailSizeAllByProduct");
    }
}
