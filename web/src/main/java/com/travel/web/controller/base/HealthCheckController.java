package com.travel.web.controller.base;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 容器健康检查
 *
 * @author songgc
 * @date 2018/8/1 2:53 PM
 **/
@Slf4j
@RestController
public class HealthCheckController {


    @RequestMapping("/health")
    public String alived() {
        return "200";
    }

    @RequestMapping("/ready")
    public String ready(HttpServletResponse response) {
        String ret = "success";
        return ret;
    }
}
