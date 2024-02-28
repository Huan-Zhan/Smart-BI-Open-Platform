package com.user.center.example.usercenter.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.user.center.example.usercenter.model.BIMessage;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqUtils {
    /**
     * 一个交换机
     */
    public static final String MQ_EXCHANGE_NAME = "bi_exchange";
    /**
     * 第一个队列
     */
    public static final String MQ_QUEUE_NAME_FIRST_HOST = "bi_queue_first_host";
    public static final String MQ_QUEUE_NAME_FIRST_ROUTING_KEY = "bi_exchange";

    /**
     * 第二个队列
     */
    public static final String MQ_QUEUE_NAME_SECOND_HOST = "bi_exchange";
    public static final String MQ_QUEUE_NAME_SECOND_ROUTING_KEY = "bi_exchange";

    public static void ConsumerACK(String QueueName , BIMessage message) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();


    }

    public static void ConsumerNACK(){

    }

}
