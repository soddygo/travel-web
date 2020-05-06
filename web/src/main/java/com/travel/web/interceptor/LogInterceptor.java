package com.travel.web.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;


/**
 * 日志拦截处理
 *
 * @author soddy
 */
@Component
@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 此部分为日志拦截处理，可自定义
        UUID uuid = UUID.randomUUID();
        MDC.put("uuid", uuid.toString().replaceAll("-", ""));
        Map<String, String[]> paramsMap = request.getParameterMap();
        String uri = request.getRequestURI();
        if ("/ready".equals(uri) || "/health".equals(uri)) {
            log.debug("请求参数记录:uri:{},paramsMap:{}", uri, JSON.toJSONString(paramsMap));
        } else {
            log.info("请求参数记录:uri:{},paramsMap:{}", uri, JSON.toJSONString(paramsMap));
        }

        return super.preHandle(request, response, handler);
    }
}
