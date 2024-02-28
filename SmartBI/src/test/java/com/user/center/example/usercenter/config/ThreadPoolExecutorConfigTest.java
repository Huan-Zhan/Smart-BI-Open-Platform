package com.user.center.example.usercenter.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;


@SpringBootTest
class ThreadPoolExecutorConfigTest {

    @Resource
    ThreadPoolExecutor threadPoolExecutor;

    @Test
    void getThreadPool() throws InterruptedException {

        System.out.println(threadPoolExecutor.getMaximumPoolSize());

        for (int i = 0; i < 10; i++) {

            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("here is : " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        Thread.sleep(15000);
    }
}