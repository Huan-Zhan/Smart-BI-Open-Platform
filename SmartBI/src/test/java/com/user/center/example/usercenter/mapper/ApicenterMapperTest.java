package com.user.center.example.usercenter.mapper;

import com.user.center.example.usercenter.model.domain.Apicenter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApicenterMapperTest {

    @Resource
    ApicenterMapper apicenterMapper;

    @Test
    void checkUserExist() {
        Apicenter apicenter = new Apicenter();
        apicenter.setUserName("admin");
        System.out.println(apicenterMapper.CheckUserExist(apicenter));
    }
}