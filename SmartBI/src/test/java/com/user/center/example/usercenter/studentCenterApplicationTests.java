package com.user.center.example.usercenter;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@MapperScan("com.user.center.example.usercenter.mapper")
class studentCenterApplicationTests {


    @Test
    void contextLoads() {

    }

}
