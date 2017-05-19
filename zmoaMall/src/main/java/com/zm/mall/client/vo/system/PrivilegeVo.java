package com.zm.mall.client.vo.system;

import com.zm.mall.domain.system.Privilege;
import com.zm.mall.domain.system.Role;

import java.util.HashSet;
import java.util.Set;

public class PrivilegeVo {
	 private Long id ;
	 private String name;
	 private String url;
	 private String cion;
	 private Privilege privilege;
	 private Set<Privilege> privileges = new HashSet<Privilege>();
	 private Set<Role> roles = new HashSet<Role>();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCion() {
		return cion;
	}

	public void setCion(String cion) {
		this.cion = cion;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
