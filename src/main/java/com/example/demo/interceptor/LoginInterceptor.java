package com.example.demo.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            System.out.println("receive first request");
            response.setStatus(HttpStatus.NO_CONTENT.value());
        }
        Subject subject = SecurityUtils.getSubject();
        System.out.println("isRemembered:"+subject.isRemembered());
        System.out.println("isAuthenticated:"+subject.isAuthenticated());
        return true;
    }
}
