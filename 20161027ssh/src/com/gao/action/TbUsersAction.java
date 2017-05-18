package com.gao.action;

import com.gao.entity.TbUsers;
import com.gao.service.TbUsersService;

public class TbUsersAction extends BaseAction {
	private TbUsers users;
	private String error;
	private TbUsersService usersService;
	public String login(){
		users=usersService.queryByAccountPwd(users);
		if(users==null){
			error="’À∫≈ªÚ√‹¬Î¥ÌŒÛ£¨«Î÷ÿ ‘°£°£°£°£";
			setPath("login");
			return DISPATCHER;
		}
		getSession().setAttribute("users", users);
		setPath("index");
		return REDIRECT;
	}
	public TbUsers getUsers() {
		return users;
	}
	public void setUsers(TbUsers users) {
		this.users = users;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public TbUsersService getUsersService() {
		return usersService;
	}
	public void setUsersService(TbUsersService usersService) {
		this.usersService = usersService;
	}
	
}
