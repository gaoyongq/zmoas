package com.zm.mall.client.result.system;



import com.zm.mall.domain.system.Department;
import com.zm.mall.domain.system.Privileges;
import com.zm.mall.domain.system.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/11/15.
 */
public class RoleResult {
	private Long id ;
	private Set<User> users = new HashSet<User>();
	private String name;
	private String description;
	private Department department;
	private Set<Privileges> privileges  = new HashSet<Privileges>();
	private String pluteformid;

	public String getPluteformid() {
		return pluteformid;
	}

	public void setPluteformid(String pluteformid) {
		this.pluteformid = pluteformid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Privileges> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privileges> privileges) {
		this.privileges = privileges;
	}
}
