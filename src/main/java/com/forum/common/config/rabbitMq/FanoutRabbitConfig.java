package com.forum.common.config.rabbitMq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  扇形交换机
 *  @author Mr Zhang
 *  @since 2020-06-08
 */
@Configuration
public class FanoutRabbitConfig {
    /**
     *  创建三个队列: fanout.A  fanout.B  fanout.C
     *  将三个队列都绑定在交换机 fanoutExchange 上
     *  因为是扇形交换机, 路由键无需配置,配置也不起作用
     */

    // 依次创建三个队列
    @Bean
    public Queue queueA(){
        return new Queue("fanout.A");
    }
    @Bean
    public Queue queueB(){
        return new Queue("fanout.B");
    }
    @Bean
    public Queue queueC(){
        return new Queue("fanout.C");
    }

    // 创建扇形交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    // 将A B C三个队列依次放入扇形交换机
    @Bean
    public Binding bindingExchangeA(){
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingExchangeB(){
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingExchangeC(){
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }
}
