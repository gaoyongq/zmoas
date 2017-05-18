package com.gao.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class BaseAction {
	/**
	 * 转发
	 */
	public static final String DISPATCHER="dispatcher";
	/**
	 * 重定向
	 */
	public static final String REDIRECT="redirect";
	
	private String path;
	private Map<String, Object> requestMap;
	private Map<String, Object> sessiontMap;
	private Map<String, Object> applicationMap;
	
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	public HttpServletResponse getResponse() {
		if(response==null){
			response=ServletActionContext.getResponse();
		}
		return response;
	}
	public HttpServletRequest getRequest() {
		if(request==null){
			request=ServletActionContext.getRequest();
		}
		return request;
	}
	public HttpSession getSession() {
		if(session==null){
			session=getRequest().getSession();
		}
		return session;
	}
	public ServletContext getApplication() {
		if(application==null){
			application=getSession().getServletContext();
		}
		return application;
	}
	public Map<String, Object> getRequestMap() {
		if(requestMap==null){
			requestMap=(Map<String, Object>) ActionContext.getContext().get("request");
		}
		return requestMap;
	}
	public Map<String, Object> getSessiontMap() {
		if(sessiontMap==null){
			sessiontMap=ActionContext.getContext().getSession();
		}
		return sessiontMap;
	}
	public Map<String, Object> getApplicationMap() {
		if(applicationMap==null){
			applicationMap=ActionContext.getContext().getApplication();
		}
		return applicationMap;
	}
	
	public void sendMsg(Object obj){
		HttpServletResponse response=getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.print(obj);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}
	

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
