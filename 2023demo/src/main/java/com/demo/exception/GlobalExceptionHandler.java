package com.demo.exception;


import com.demo.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理器
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//指明哪个异常生效
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("操作错误！");
    }
}
