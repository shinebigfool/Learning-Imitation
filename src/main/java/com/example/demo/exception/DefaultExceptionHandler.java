package com.example.demo.exception;

import com.example.demo.Result.Result;
import com.example.demo.Result.ResultFactory;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleAuthorizationException(UnauthorizedException e){
        String message="权限认证失败";
        System.out.println(e);
        return ResultFactory.buildFailResult(message);
    }

}
