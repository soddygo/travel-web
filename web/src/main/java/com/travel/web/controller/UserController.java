package com.travel.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.pojo.HttpResult;
import com.travel.pojo.entity.UserInfo;
import com.travel.service.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author soddy
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @RequestMapping("/login")
    public HttpResult login(HttpServletRequest request, String userName, String password) throws Exception {
        HttpResult httpResult = HttpResult.success();


        //TODO login

        return httpResult;
    }

    @RequestMapping("/search")
    public HttpResult search(HttpServletRequest request, UserInfo userInfo, Integer pageNo, Integer pageSize) throws Exception {
        HttpResult httpResult = HttpResult.success();

        Page<UserInfo> page = this.userInfoService.search(userInfo, pageNo, pageSize);
        httpResult.setData(page);

        return httpResult;
    }

}
