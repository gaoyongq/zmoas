<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#session.users==null">
	<a href="${basePath }login.jsp">[登录]</a>
	<a href="${basePath }login.jsp">[注册]</a>
</s:if>
<s:else>
	欢迎
	<s:property value="#session.users.name"/>来到本系统
	<a href="">消息(<span id="v_count">0</span>)</a>
	<a href="users_exit">注销</a>
</s:else>