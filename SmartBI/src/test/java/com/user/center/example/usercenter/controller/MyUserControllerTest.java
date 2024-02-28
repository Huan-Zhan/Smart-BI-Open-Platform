package com.user.center.example.usercenter.controller;

import com.user.center.example.usercenter.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyUserControllerTest {

    @Test
    void getHistoryCharts() {

        User user = new User();
        user.setUserPassword("123456789");
        user.setUserAccount("admin");



    }
}