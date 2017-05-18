package com.gao.service.impl;

import com.gao.dao.TbUsersDao;
import com.gao.entity.TbUsers;
import com.gao.service.TbUsersService;
import com.gao.util.MD5;

public class TbUsersServiceImpl implements TbUsersService{
	private TbUsersDao usersDao;
	public TbUsers queryByAccountPwd(TbUsers users) {
		// TODO Auto-generated method stub
		TbUsers temp=usersDao.findByAccount(users.getAccount());
		if(temp!=null){
			if(temp.getPassword().equals(MD5.code(users.getPassword()))){
				return temp;
			}
		}
		return null;
	}
	
	public TbUsersDao getUsersDao() {
		return usersDao;
	}
	public void setUsersDao(TbUsersDao usersDao) {
		this.usersDao = usersDao;
	}

}
