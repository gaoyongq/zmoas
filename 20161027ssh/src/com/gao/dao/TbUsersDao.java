package com.gao.dao;

import com.gao.entity.TbUsers;

public interface TbUsersDao extends Dao<TbUsers> {
	public TbUsers findByAccount(String account);
}
