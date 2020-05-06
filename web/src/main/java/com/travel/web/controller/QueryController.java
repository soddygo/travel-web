package com.travel.web.controller;

import com.travel.pojo.HttpResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author soddy
 */
@RestController
@RequestMapping("/query")
public class QueryController {


    @RequestMapping("/test")
    public HttpResult test(HttpServletRequest request, String userName, String password) throws Exception {
        HttpResult httpResult = HttpResult.success();


        //TODO test

        return httpResult;
    }


}
