package com.travel.web.controller;

import com.travel.pojo.HttpResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author soddy
 */
@RestController
@RequestMapping("/login")
public class LoginController {


    @RequestMapping("/login")
    public HttpResult login(HttpServletRequest request, String userName, String password) throws Exception {
        HttpResult httpResult = HttpResult.success();


        //TODO login

        return httpResult;
    }


}
