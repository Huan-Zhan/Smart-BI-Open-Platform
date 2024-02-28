package com.user.center.example.usercenter.service.impl;

import com.user.center.example.usercenter.model.domain.Apicenter;
import com.user.center.example.usercenter.service.ApicenterService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApicenterServiceImplTest {

    @Resource
    ApicenterService apicenterService ;

    @Test
    void checkUserExist() {

        Apicenter apicenter = new Apicenter();
        apicenter.setUserName("admin");
        apicenter.setUserPassword("123456789");
        System.out.println(apicenterService.CheckUserExist(apicenter));
    }
}