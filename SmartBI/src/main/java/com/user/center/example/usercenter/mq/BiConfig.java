package com.user.center.example.usercenter.mq;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  创建交换机
 *  创建队列
 *  绑定交换机和队列
 */
@Configuration
public class BiConfig {
    /**
     * 创建 RabbitTemplate
     * @return
     */
    public RabbitTemplate CreateRabbitTemplate(){
        return new RabbitTemplate();
    }

    /**
     * 创建 BI 交换机
     * @return
     */
    @Bean
    public FanoutExchange CreateBI_FanoutExchange(){
        return new FanoutExchange(MqUtils.MQ_EXCHANGE_NAME,true,false);
    }

    /**
     * 创建 BI 第一个队列
     * @return
     */
    @Bean
    public Queue CreateBI_QueueFirstHost(){
        return new Queue(MqUtils.MQ_QUEUE_NAME_FIRST_HOST,true);
    }

    /**
     * 创建 BI 交换机与队列绑定
     * @return
     */
    @Bean
    public Binding BindBIExchangeWithQueueFirstHost(){
        return BindingBuilder.bind(CreateBI_QueueFirstHost()).to(CreateBI_FanoutExchange());
    }

    /**
     * 将对象 转为 json 在 mq 之间传递
     * @return
     */
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }


}
