package com.demo.aop;


import com.alibaba.fastjson.JSONObject;
import com.demo.anno.Log;
import com.demo.mapper.OperateLogMapper;
import com.demo.pojo.OperateLog;
import com.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect//指定切面类
public class LogAspect {

    @Autowired
    private HttpServletRequest req;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.demo.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //获取令牌解析出操作人id
        String jwt = req.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");
        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long start = System.currentTimeMillis();
        //操作方法返回值
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        String returnValue = JSONObject.toJSONString(result);
        //操作耗时
        Long costTime = end - start;

        //记录操作日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志:{}",operateLog);

        return result;


    }

}
