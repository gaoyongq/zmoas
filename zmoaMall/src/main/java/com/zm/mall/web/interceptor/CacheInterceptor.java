package com.zm.mall.web.interceptor;

import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.domain.system.User;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: WQ
 * Date: 16-12-22
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
public class CacheInterceptor implements HandlerInterceptor {
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       response.setContentType("text/html;charset=UTF-8");
       request.setCharacterEncoding("UTF-8");
       String uri = request.getRequestURI();
//       String url = request.getRequestURL().toString();
       UserResult user=(UserResult)request.getSession().getAttribute("userResult");
       if (uri.endsWith("/toLogin.action")||uri.endsWith("/login.action")||uri.endsWith("/MenuList.action")||uri.endsWith("/toExit.action")) { // "/user_loginUI", "/user_login"
           // 如果是去登录，就放行
           return true;
       }else{
           if (user == null) {
               request.getRequestDispatcher("/WEB-INF/vm/login.vm").forward(request, response);
//               response.sendRedirect("/WEB-INF/vm/login.vm");
               return false;

           }else{
               if(user.hasPrivilegeByUri(uri,request)){
                   return true;
               }else {
                   request.getRequestDispatcher("/WEB-INF/vm/system/noPrivilege.vm").forward(request, response);
                   return false;
               }
           }
       }
   }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
