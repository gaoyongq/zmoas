/**
 * File Name:BaseDao.java
 * Package Name:com.jd.ecc.mall.center.dao
 * Date:2013-11-22上午11:33:09
 * Copyright (c) 2013, jd.com All Rights Reserved.
 *
 */
package com.zm.mall.dao;

import java.util.List;

/**
 * ClassName: BaseDao date: 2013-11-22 上午11:33:09
 *
 * @author zhangjianyong
 * @version
 * @since JDK 1.6 last modify
 */
public interface BaseDao<T> {

	/**
	 * getNameSpace:获取命名空间
	 *
	 * @param statement
	 * @return
	 */
	String getNameSpace(String statement);

	/**
	 * insert:添加对象
	 *
	 * @param t
	 * @return
	 */
	int insert(T t);

	/**
	 * delete:删除对象,通过主键
	 *
	 * @param t
	 * @return
	 */
	int delete(T t);

	/**
	 * update:更新对象,条件主键
	 *
	 * @param t
	 * @return
	 */
	int update(T t);

	/**
	 * select:查询对象,条件主键
	 *
	 * @param t
	 * @return
	 */
	T selectOne(T t);

	/**
	 * select:查询对象,只要不为NULL与空则为条件
	 *
	 * @param t
	 * @return
	 */
	List<T> select(T t);

	/**
	 * selectCount:查询对象总数
	 *
	 * @param t
	 * @return
	 */
	int selectCount(T t);

	/**
	 * selectPage:查询对象列表
	 *
	 * @param t
	 * @return
	 */
	List<T> selectPage(T t);
}
