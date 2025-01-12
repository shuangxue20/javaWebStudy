package com.demo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.demo.pojo.Result;
import com.demo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//拦截器
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        //String url = req.getRequestURL().toString();
        //log.info("url为:{}", url);

        String jwt = req.getHeader("token");

        if (!StringUtils.hasLength(jwt)) {
            log.info("token为空，返回未登陆信息");
            Result error = Result.error("NOT_LOGIN");

            String noLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(noLogin);
            return false;
        }

        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("token错误，返回错误信息");
            Result error = Result.error("NOT_LOGIN");

            String noLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(noLogin);
            return false;
        }

        log.info("令牌正确放行");
        return true;


    }
}
