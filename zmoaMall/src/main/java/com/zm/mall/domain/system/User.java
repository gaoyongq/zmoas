package com.zm.mall.domain.system;


import org.springframework.context.ApplicationContext;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



public class User implements Serializable{

	private Long id;
    private Set<Role> roles =new HashSet<Role>();
	 Department department;
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
	private Long shopId;//店铺
    private String pluteformid;
	private  String ParentPluteformId;
	private  Integer PluteformStatus;
	private String userName;
	private String nickName;
	private String openId;
	private String url;
	private Integer followWay;  //1代表公众号入口2代表店铺入口
	private  String  sex;
	private String imgUrl;//用户头像
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getPluteformid() {
		return pluteformid;
	}

	public void setPluteformid(String pluteformid) {
		this.pluteformid = pluteformid;
	}

	public Long getDepaedmentId() {
		return depaedmentId;
	}

	public void setDepaedmentId(Long depaedmentId) {
		this.depaedmentId = depaedmentId;
	}

	private static ApplicationContext applicationContext=null;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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

	public Long getLoginState() {
		return loginState;
	}

	public void setLoginState(Long loginState) {
		this.loginState = loginState;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
//	public boolean isAdmin() {
//		return "admin".equals(this.name);
//	}
//	public boolean hasPrivilegeByUri(String url)
//	{
//		if (isAdmin()) {
//			return true;
//		}
//		Set<String> allPrivilegeUrls =(Set<String>) applicationContext.getBean("allPrivilegeUrls");
//
//		if (!allPrivilegeUrls.contains(url)) {
//			return true;
//		}
//		for (Role role : this.roles) {
//			for (Privilege privilege : role.getPrivileges()) {
//				if (url.equals(privilege.getUrl())) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}

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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getFollowWay() {
		return followWay;
	}

	public void setFollowWay(Integer followWay) {
		this.followWay = followWay;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
