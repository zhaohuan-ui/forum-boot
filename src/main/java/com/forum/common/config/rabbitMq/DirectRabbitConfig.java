package com.forum.common.config.rabbitMq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  RabbitMq: 直连交换机(默认)
 * @author Mr Zhang
 * @since 2020-06-08
 */
@Configuration
public class DirectRabbitConfig {

    // 队列 名称: TestDirectQueue
    @Bean
    public Queue TestDirectQueue(){
        // durable: 是否持久化,默认是false,持久化队列: 会被储存在磁盘上,当消息代理重启时仍然存在, 暂时队列: 当前连接有效
        // exclusive: 默认是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        // return new Queue("TestDirectQueue",true,true,false);

        // 一般设置一下队列的持久化就好,其余两个都是默认false
        return new Queue("TestDirectQueue",true);
    }

    // 交换机 名称: TestDirectExchange
    @Bean
    public DirectExchange TestDirectExchange(){
        return new DirectExchange("TestDirectExchange",true,false);
    }

    // 绑定 将队列与交换机绑定,并设置用于匹配键: TestDirectRouting
    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }

    // 另一个交换机
    @Bean
    public DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }
}
