package com.user.center.example.usercenter.config;


import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolExecutorConfig {

    @Bean
    public ThreadPoolExecutor getThreadPoolExecutor(){

        ThreadFactory threadFactory = new ThreadFactory() {
            private int id = 1 ;
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("线程"+id++ % 1000000);
                return thread ;
            }
        };

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,4,
                60,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(9),
                threadFactory
        );
        return threadPoolExecutor ;
    }
}
