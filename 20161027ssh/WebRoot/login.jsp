<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<div style="color: red;">
  		<s:property value="#request.error"/>
  	</div>
  	<form action="${basePath }users_login" method="post">
  		<table border="1">
  			<tr>
  				<td>账号:</td>
  				<td>
  					<input type="text" name="users.account">
  				</td>
  			</tr>
  			<tr>
  				<td>密码:</td>
  				<td>
  					<input type="text" name="users.password">
  				</td>
  			</tr>
  			<tr>
  				<td colspan="2" align="right">
  					<input type="submit" value="登录">
  				</td>
  			</tr>
  		</table>
  		
  	</form>
  </body>
</html>
