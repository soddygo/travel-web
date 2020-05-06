package com.travel.web.controller.base;

import com.travel.pojo.BetweenDateVo;
import com.travel.web.bind.BetweenDateBinder;
import com.travel.web.bind.DateBinder;
import com.travel.web.bind.LocalDateTimeBinder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 属性绑定,另外获取当前登录用户id
 *
 * @author soddygo
 * @since 2018/5/17
 */
@Slf4j
public class BaseController {


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // 注册时间绑定器
        binder.registerCustomEditor(Date.class, new DateBinder(true));
        binder.registerCustomEditor(LocalDateTime.class, new LocalDateTimeBinder());
        binder.registerCustomEditor(BetweenDateVo.class, new BetweenDateBinder(true));


    }


    protected void setHeader() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String origin = request.getHeader("Origin");
        if (StringUtils.isNotBlank(origin) && origin.contains("dmall.com")) {
            // 允许访问的域
            response.setHeader("Access-Control-Allow-Origin", origin);
            // 允许GET、POST的外域请求
            response.setHeader("Access-Control-Allow-Methods", "POST,GET");
            // 允许请求带cookie到服务器
            response.setHeader("Access-Control-Allow-Credentials", "true");
            // 设定JSON格式标准输出、及编码
            response.setContentType("application/json; charset=utf-8");
        }
    }


}
