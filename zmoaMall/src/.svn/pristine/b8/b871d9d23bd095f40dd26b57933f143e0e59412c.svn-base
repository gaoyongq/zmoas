package com.zm.mall.web.exception;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2017/3/23.
 */
public class ExceptionHandler implements HandlerExceptionResolver{
	@Override
	public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception exception) {
		exception.printStackTrace();
		if (handler instanceof HandlerMethod) {
			String message = "";
			if(exception.getClass().equals(Exception.class)){
				message = "系统出现错误，请稍后再试";
			}else if(exception.getClass().equals(FileNotFoundException.class)){
				message = "文件不存在";
			}else if(exception.getClass().equals(NullPointerException.class)){
				message = "数据丢失";
			}
		}
			ModelAndView m = new ModelAndView();
		m.setViewName("redirect:http://www.baidu.com");
		return m;
	}
}
