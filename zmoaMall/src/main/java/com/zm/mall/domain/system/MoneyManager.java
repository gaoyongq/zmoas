package com.zm.mall.domain.system;

import com.zm.mall.client.result.system.ActivityTaskResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 财务报销管理实体对象
 * @author Administrator
 *
 */
public class MoneyManager {
	
	private Long id ;
	//费用名称
	private String name;
	//费用的编码
	private String code;
	//费用申请部门
	private String departmentName;
	//申请部门的编码
	private String departmentCode;
	//申请人
	private String userName;
	//上级领导（部门负责人）
	private String deparmentUserName;

	//上级领导（部门负责人）
	private Long deparmentUserCode;
	//单据状态
	private String deparmentStatusName;//等待审核、已经审核、正在进行
	//单据状态
	private String deparmentStatusCode;//等待审核、已经审核、正在进行 拼音代码
	//部门审批意见
	private String dpartCheckConment;
	
	//领导审批
	private Long leaderCode;
	
	//领导审批
	private String leaderName;
	//单据状态
	private String leaderStatusName;//等待审核、已经审核、正在进行
	//单据状态
	private String leaderStatusCode;//等待审核、已经审核、正在进行 拼音代码
	//部门审批意见
	private String leaderCheckConment;
	//申请事由
	private String description;
	
	//金额
	private BigDecimal money;
	
	//提前预支
	private String moneyType; //分正常申请与、借款
	//单据状态
	private String statusName;//等待审核、已经审核、正在进行
	//单据状态
	private String statusCode;//等待审核、已经审核、正在进行 拼音代码
	
	//单据的日期
	
	private Date createDate;
	//临时的参数 子费用想
	private String moneyItemParamer;
	//临时的参数 子费用想
	private String checkConment;
	//逻辑删除状态
	private String status;
	//当前登录的用户名
	private String realName;
	//保存文件的路径
	private String fileURL;
	//当前登录用户的角色
	private Set<Role> roles =new HashSet<Role>();

	private Set<MoneyItem> itemSet = new HashSet<MoneyItem>();
	private ActivityTaskResult activityTaskResult;

	private int currentPage =1;
	private int totalPages;
	private Integer beginNumber;
	private int pageSize = 10;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeparmentUserName() {
		return deparmentUserName;
	}

	public void setDeparmentUserName(String deparmentUserName) {
		this.deparmentUserName = deparmentUserName;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public Long getDeparmentUserCode() {
		return deparmentUserCode;
	}

	public void setDeparmentUserCode(Long deparmentUserCode) {
		this.deparmentUserCode = deparmentUserCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<MoneyItem> getItemSet() {
		return itemSet;
	}

	public void setItemSet(Set<MoneyItem> itemSet) {
		this.itemSet = itemSet;
	}

	public String getMoneyItemParamer() {
		return moneyItemParamer;
	}

	public void setMoneyItemParamer(String moneyItemParamer) {
		this.moneyItemParamer = moneyItemParamer;
	}

	public String getDpartCheckConment() {
		return dpartCheckConment;
	}

	public void setDpartCheckConment(String dpartCheckConment) {
		this.dpartCheckConment = dpartCheckConment;
	}

	public Long getLeaderCode() {
		return leaderCode;
	}

	public void setLeaderCode(Long leaderCode) {
		this.leaderCode = leaderCode;
	}

	public String getLeaderCheckConment() {
		return leaderCheckConment;
	}

	public void setLeaderCheckConment(String leaderCheckConment) {
		this.leaderCheckConment = leaderCheckConment;
	}

	public String getCheckConment() {
		return checkConment;
	}

	public void setCheckConment(String checkConment) {
		this.checkConment = checkConment;
	}

	public String getDeparmentStatusName() {
		return deparmentStatusName;
	}

	public void setDeparmentStatusName(String deparmentStatusName) {
		this.deparmentStatusName = deparmentStatusName;
	}

	public String getDeparmentStatusCode() {
		return deparmentStatusCode;
	}

	public void setDeparmentStatusCode(String deparmentStatusCode) {
		this.deparmentStatusCode = deparmentStatusCode;
	}

	public String getLeaderStatusName() {
		return leaderStatusName;
	}

	public void setLeaderStatusName(String leaderStatusName) {
		this.leaderStatusName = leaderStatusName;
	}

	public String getLeaderStatusCode() {
		return leaderStatusCode;
	}

	public void setLeaderStatusCode(String leaderStatusCode) {
		this.leaderStatusCode = leaderStatusCode;
	}


	public ActivityTaskResult getActivityTaskResult() {
		return activityTaskResult;
	}

	public void setActivityTaskResult(ActivityTaskResult activityTaskResult) {
		this.activityTaskResult = activityTaskResult;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getBeginNumber() {
		return beginNumber;
	}

	public void setBeginNumber(Integer beginNumber) {
		this.beginNumber = beginNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
}
