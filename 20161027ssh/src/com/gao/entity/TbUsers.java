package com.gao.entity;

/**
 * TbUsers entity. @author MyEclipse Persistence Tools
 */

public class TbUsers implements java.io.Serializable {

	// Fields

	private Long id;
	private String account;
	private String name;
	private String password;

	// Constructors

	/** default constructor */
	public TbUsers() {
	}

	/** full constructor */
	public TbUsers(Long id, String account, String name, String password) {
		this.id = id;
		this.account = account;
		this.name = name;
		this.password = password;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}