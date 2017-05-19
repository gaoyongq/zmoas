/**
 * File Name:BaseDaoImpl.java
 * Package Name:com.jd.ecc.mall.center.dao.impl
 * Date:2013-11-22下午1:48:01
 * Copyright (c) 2013, jd.com All Rights Reserved.
 *
 */
package com.zm.mall.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * ClassName: BaseDaoImpl
 * date: 2013-11-22 下午1:48:01
 * @author zhangjianyong
 * @version 
 * @since JDK 1.6
 * lastmodify 
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	protected SqlSessionTemplate sqlTemplate;

	protected SqlSessionTemplate batchSqlTemplate;
	

	public abstract String getNameSpace(String statement);

	public int insert(T t) {
		return sqlTemplate.insert(getNameSpace("insertEntry"), t);
	}

	public int delete(T t) {
//		if(key.getClass().isArray()) {
//            return sqlTemplate.delete(getNameSpace("deleteEntryByArrayKeys"), key);
//	    } else if(key instanceof List) {
//	        return sqlTemplate.delete(getNameSpace("deleteEntryByListKeys"), key);
//	    }
		return sqlTemplate.delete(getNameSpace("deleteEntryByKey"), t);
	}

	public int update(T t) {
		return sqlTemplate.update(getNameSpace("updateEntryByKey"), t);
	}

	@SuppressWarnings("unchecked")
	public T selectOne(T t) {
		return (T)sqlTemplate.selectOne(getNameSpace("selectEntryByKey"), t);
	}

	public List<T> select(T t) {
		return sqlTemplate.selectList(getNameSpace("selectEntryByWhere"), t);
	}

	public int selectCount(T t) {
		return (Integer)sqlTemplate.selectOne(getNameSpace("selectEntryByWhereCount"), t);
	}

	public List<T> selectPage(T t) {
		return sqlTemplate.selectList(getNameSpace("selectEntryByWherePage"), t);
	}

	/**
	 * @param sqlTemplate the sqlTemplate to set
	 */
	public void setSqlTemplate(SqlSessionTemplate sqlTemplate) {
		this.sqlTemplate = sqlTemplate;
	}

	/**
	 * @param batchSqlTemplate the batchSqlTemplate to set
	 */
	public void setBatchSqlTemplate(SqlSessionTemplate batchSqlTemplate) {
		this.batchSqlTemplate = batchSqlTemplate;
	}
}
