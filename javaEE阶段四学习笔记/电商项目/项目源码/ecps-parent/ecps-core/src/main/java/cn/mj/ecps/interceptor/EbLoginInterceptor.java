package cn.mj.ecps.interceptor;

import cn.mj.ecps.model.TsPtlUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EbLoginInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
         TsPtlUser user= (TsPtlUser) session.getAttribute("user");
         if(user==null){
             String path = request.getContextPath();
             response.sendRedirect(path+"/user/toLogin.do");
             return false;
         }else {
             return true;
         }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
