package com.forum.common.config.rabbitMq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  RabbitMq: 主题交换机(通配符)
 *  @author Mr Zhang
 *  @since 2020-06-08
 */
@Configuration
public class TopicRabbitConfig {

    // 绑定键
    public final static String man = "topic.man";
    public final static String woman = "topic.woman";

    /**
     *  消息第一队列
     */
    @Bean
    public Queue firstQueue(){
        return new Queue(TopicRabbitConfig.man,true);
    }

    /**
     *  消息第二队列
     */
    @Bean
    public Queue secondQueue(){
        return new Queue(TopicRabbitConfig.woman);
    }

    /**
     *  主题交换机
     */
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }

    /**
     *  将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
     *  这样只要是消息携带的路由键是topic.man,才会分发到该队列
     */
    @Bean
    public Binding bindingExchangeMessage(){
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(man);
    }

    /**
     *  将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
     *  这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
     */
    @Bean
    public Binding bindingExchangeMessage2(){
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }
}
