package com.travel.web.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.pojo.entity.UserInfo;
import com.travel.service.UserInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
@Profile("dev")
@Slf4j
public class UserControllerTest {


    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Test
    public void login() {
    }

    @Test
    public void search() {


        UserInfo userInfo = new UserInfo();

        Page<UserInfo> page = this.userInfoService.search(userInfo, 1, 10);

        log.info("test search,page={}", JSON.toJSON(page));
    }
}