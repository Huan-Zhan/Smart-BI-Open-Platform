package com.user.center.example.usercenter.mq;


import com.user.center.example.usercenter.model.BIMessage;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Data
public class BIClient {

    @Resource
    private RabbitTemplate rabbitTemplate ;

    public void SendMessage(BIMessage message){

//        message.setThreadName(Thread.currentThread().getName());

        rabbitTemplate.convertAndSend(MqUtils.MQ_EXCHANGE_NAME
                                        ,MqUtils.MQ_QUEUE_NAME_FIRST_ROUTING_KEY
                                        ,message);

    }

}
