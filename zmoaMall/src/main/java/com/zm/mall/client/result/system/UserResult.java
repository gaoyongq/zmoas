package com.zm.mall.client.result.system;



import com.zm.mall.domain.system.Department;
import com.zm.mall.domain.system.Privileges;
import com.zm.mall.domain.system.Role;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class UserResult implements Serializable{

	private Long id;
    private Set<Role> roles =new HashSet<Role>();
	private Department department;
	private String name;
	private String password;
	private String realName;
	private String gender;
	private String phone;
	private String email;
	private String state;
	private String description;
	private String newPassword;
	private Long roleId;
	private Long depaedmentId;
	private Long loginState;
	private Long shopId;
	private String pluteformid;
	private  String ParentPluteformId;
	private  Integer PluteformStatus;
	private String url;

	public String getPluteformid() {
		return pluteformid;
	}

	public void setPluteformid(String pluteformid) {
		this.pluteformid = pluteformid;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getDepaedmentId() {
		return depaedmentId;
	}

	public void setDepaedmentId(Long depaedmentId) {
		this.depaedmentId = depaedmentId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * 检测用户有没有指定权限
	 * @param
	 * @return
	 */
	public boolean hasPrivilegeByName(String priname){
		if(isAdmin()){
			return true;
		}
		for(Role role :roles){
			for(Privileges privileges :role.getPrivileges()){
				if(privileges.getName().equals(priname)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 *该方法是检测访问的url是不是在权限的控制下
	 * @param url
	 * @return
	 */
	public boolean hasPrivilegeByUri(String url,HttpServletRequest request){
		if(isAdmin()){
			return true;
		}

		/**
		 * 该代码功能是把没在权限控制下的超链接显示出来
		 */
		/**
		 * wq获取servlet中的所有url
		 */
//		UserResult user=(UserResult)request.getSession().getAttribute("userResult");
//		List<String> allPrivilegeUrls = (List<String>)request.getSession().getAttribute("allPrivilegeUrls");
		for(Role role :roles){
			for(Privileges privileges :role.getPrivileges()){
				if(privileges.getUrl()!=null){
					if(privileges.getUrl().equals(url)) {
						return true;
					}
				}
			}
		}
		return false;

	}

	/**
	 * 验证登陆的用户是不是超级管理员
	 * @return
	 */
	public boolean isAdmin(){
		return "admin".equals(name);
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getId() {return id;}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Long getLoginState() {
		return loginState;
	}

	public void setLoginState(Long loginState) {
		this.loginState = loginState;
	}

	public String getParentPluteformId() {
		return ParentPluteformId;
	}

	public void setParentPluteformId(String parentPluteformId) {
		ParentPluteformId = parentPluteformId;
	}

	public Integer getPluteformStatus() {
		return PluteformStatus;
	}

	public void setPluteformStatus(Integer pluteformStatus) {
		PluteformStatus = pluteformStatus;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
