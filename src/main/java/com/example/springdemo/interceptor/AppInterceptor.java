package com.example.springdemo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AppInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String requestUri = request.getRequestURI();

        if(requestUri.equals("/login")) {
            if(isLogin(request)) {
                // already logged in
                response.sendRedirect("/");
                return false;
            }
        } else {
            if (requestUri.equals("/register")) {
                // pass request on to controller
                return true;
            }

            if(!isLogin(request)) {
                // not logged in
                response.sendRedirect("/login");
                return false;
            }
        }

        return true;
    }

    private  boolean isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("isLoggedIn") != null && (boolean) session.getAttribute("isLoggedIn");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex){

        if (ex != null){
            ex.printStackTrace();
        }
    }
}
