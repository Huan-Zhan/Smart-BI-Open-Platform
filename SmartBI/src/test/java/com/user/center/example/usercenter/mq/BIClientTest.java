package com.user.center.example.usercenter.mq;

import com.user.center.example.usercenter.model.BIMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BIClientTest {

    @Resource
    BIClient client;
    @Test
    void sendMessage() {
        BIMessage message = new BIMessage();

        for (int i = 0; i < 50; i++) {

            client.SendMessage(message);
        }
    }
}