package com.travel.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 日志拦截处理
 */
@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        HttpSession httpSession = request.getSession();
//        Object loginValue = httpSession.getAttribute("login");
//        if (loginValue == null) {
//            //登录验证不通过,进行拦截
//            // 响应前端json
//            response.setContentType("text/html; charset=utf-8");
//            // 设置跨域响应的站点
//            // 设置response响应内容
//            PrintWriter writer = response.getWriter();
//            writer.write(JSON.toJSONString(HttpResult.create("-2", "用户未登录,本系统独立发布,需要再登陆次")));
//            return false;
//        }


        return super.preHandle(request, response, handler);
    }
}
