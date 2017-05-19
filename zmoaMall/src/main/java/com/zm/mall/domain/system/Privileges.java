package com.zm.mall.domain.system;



public class Privileges {
	private Long id ;
	private String name;
	private String url;
	private Long  pid;
	private Boolean flag;
	private Boolean open;
	private String pluteformid;

	 

	 


   


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

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getPluteformid() {
		return pluteformid;
	}

	public void setPluteformid(String pluteformid) {
		this.pluteformid = pluteformid;
	}
}
