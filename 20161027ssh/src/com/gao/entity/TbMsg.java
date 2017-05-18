package com.gao.entity;

import java.util.Date;

/**
 * TbMsg entity. @author MyEclipse Persistence Tools
 */

public class TbMsg implements java.io.Serializable {

	// Fields

	private Long id;
	private Long usersId;
	private String title;
	private String content;
	private Date bdate;
	private Boolean state;

	// Constructors

	/** default constructor */
	public TbMsg() {
	}

	/** full constructor */
	public TbMsg(Long id, Long usersId, String title, String content,
			Date bdate, Boolean state) {
		this.id = id;
		this.usersId = usersId;
		this.title = title;
		this.content = content;
		this.bdate = bdate;
		this.state = state;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsersId() {
		return this.usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getBdate() {
		return this.bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

}