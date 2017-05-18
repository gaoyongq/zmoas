package com.gao.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gao.dao.TbUsersDao;
import com.gao.entity.TbUsers;

public class TbUsersDaoImpl extends HibernateDaoSupport implements TbUsersDao {
	
	public TbUsers findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TbUsers> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int findResultCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<TbUsers> findInterzone(int pageIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public TbUsers save(TbUsers arg) {
		// TODO Auto-generated method stub
		return null;
	}

	public TbUsers delete(TbUsers arg) {
		// TODO Auto-generated method stub
		return null;
	}

	public TbUsers update(TbUsers arg) {
		// TODO Auto-generated method stub
		return null;
	}

	public TbUsers findByAccount(String account) {
		// TODO Auto-generated method stub
		List<TbUsers> list=getHibernateTemplate().find("from TbUsers where account=?",account);
		return list.size()>0?list.get(0):null;
	}

}
