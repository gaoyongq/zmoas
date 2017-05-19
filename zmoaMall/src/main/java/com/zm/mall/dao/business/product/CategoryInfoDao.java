package com.zm.mall.dao.business.product;

import com.zm.mall.dao.BaseDao;
import com.zm.mall.domain.business.product.CategoryInfo;

import java.util.List;

/**
 * Created by lp on 2016/11/14.
 * 分类的集合
 */
public interface CategoryInfoDao extends BaseDao<CategoryInfo> {
    /**
     * 查询全部类型
     * @return
     */
    List<CategoryInfo> selectAll(CategoryInfo categoryInfo);

    /**
     * 获得统一调整价
     * 根据路径找分类
     * @param categoryInfo
     * @return
     */
    CategoryInfo price (CategoryInfo categoryInfo);

    /**
     * 根据id查同等级分类
     * @param categoryInfo
     * @return
     */
    List<CategoryInfo> categoryInfoListById(CategoryInfo categoryInfo);


    /**
     * in语句查询分类 参数list
     */
    List<CategoryInfo> categoryInfoListByIdOnIn(String[] CategoryId);
    /**
     * 查询分类分级结构  wecart
     */
    List<CategoryInfo> weCartAddAppSelectCategory(CategoryInfo categoryInfo);
}
