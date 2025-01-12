package com.demo.filter;


import com.alibaba.fastjson.JSONObject;
import com.demo.pojo.Result;
import com.demo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//过滤器
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse resp=(HttpServletResponse)response;

        String url = req.getRequestURL().toString();
        log.info("url为:{}",url);

        if(url.contains("login")){
            log.info("登录操作放行");
            chain.doFilter(request,response);
            return;
        }

        String jwt = req.getHeader("token");

        if(!StringUtils.hasLength(jwt)){
            log.info("token为空，返回未登陆信息");
            Result error=Result.error("NOT_LOGIN");

            String noLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(noLogin);
            return;
        }

        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("token错误，返回错误信息");
            Result error=Result.error("NOT_LOGIN");

            String noLogin = JSONObject.toJSONString(error);//将错误信息转成json格式
            resp.getWriter().write(noLogin);
            return;
        }

        log.info("令牌正确放行");
        chain.doFilter(request,response);
    }
}
