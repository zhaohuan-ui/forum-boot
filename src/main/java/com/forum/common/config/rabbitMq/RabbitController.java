package com.forum.common.config.rabbitMq;

import com.forum.common.utils.result.HttpResult;
import com.forum.common.utils.result.HttpResultUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     *  ================================================================================================================
     *  测试以下四种情况时,消息确认触发回调函数的打印情况:
     *  1.消息推送到server，但是在server里找不到交换机
     *  2.消息推送到server，找到交换机了，但是没找到队列
     *  3.消息推送到sever，交换机和队列啥都没找到
     *  4.消息推送成功
     */
    // 1.消息推送到server，但是在server里找不到交换机
    @PostMapping("/TestMessageAck1")
    public HttpResult<Object> TestMessageAck1(){
        Map<String,Object> map=new HashMap<>();
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend("non-existent-exchange","TestDirectRouting",map);
        return HttpResultUtil.success("发送成功!");
    }
    // 2.消息推送到sever，交换机和队列啥都没找到
    @PostMapping("/TestMessageAck2")
    public HttpResult<Object> TestMessageAck2(){
        Map<String,Object> map=new HashMap<>();
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend("lonelyDirectExchange","TestDirectRouting",map);
        return HttpResultUtil.success("发送成功!");
    }
    // 3.消息推送到sever，交换机和队列啥都没找到
    @PostMapping("/TestMessageAck3")
    public HttpResult<Object> TestMessageAck3(){
        Map<String,Object> map=new HashMap<>();
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend("non-existent-exchange","TestDirectRouting",map);
        return HttpResultUtil.success("发送成功!");
    }
    // 3.消息推送到sever，交换机和队列啥都没找到
    @PostMapping("/TestMessageAck4")
    public HttpResult<Object> TestMessageAck4(){
        Map<String,Object> map=new HashMap<>();
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRouting",map);
        return HttpResultUtil.success("发送成功!");
    }

    /**
     *  ================================================================================================================
     *  FanoutRabbitConfig => 扇形交换机
     */
    @PostMapping("/sendFanoutMessage")
    public HttpResult<Object> sendFanoutMessage(){
        // 将消息携带绑定键值: TestDirectRouting 发送到交换机TestDirectExchange
        Map<String,Object> map=new HashMap<>();
        String messageData = "Fanout Message Hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend("fanoutExchange",null,map);
        return HttpResultUtil.success("发送成功!");
    }
    @RabbitHandler
    @RabbitListener(queues = "fanout.A")
    public void consumerA(@Payload Map message){
        System.out.println("fanout.A=>Fanout消息者收到消息: "+ message.toString());
    }
    @RabbitHandler
    @RabbitListener(queues = "fanout.B")
    public void consumerB(@Payload Map message){
        System.out.println("fanout.B=>Fanout消息者收到消息: "+ message.toString());
    }
    @RabbitHandler
    @RabbitListener(queues = "fanout.C")
    public void consumerC(@Payload Map message){
        System.out.println("fanout.C=>Fanout消息者收到消息: "+ message.toString());
    }

    /**
     *  ================================================================================================================
     *  TopicRabbitConfig => 主题交换机
     */
    @PostMapping("/sendTopicMessage1")
    public HttpResult<Object> sendTopicessage1(){
        // 将消息携带绑定键值: TestDirectRouting 发送到交换机TestDirectExchange
        Map<String,Object> map=new HashMap<>();
        String messageData = "Topic1 Message, Hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend("topicExchange","topic.man",map);
        return HttpResultUtil.success("发送成功!");
    }

    @PostMapping("/sendTopicMessage2")
    public HttpResult<Object> sendTopicessage2(){
        // 将消息携带绑定键值: TestDirectRouting 发送到交换机TestDirectExchange
        Map<String,Object> map=new HashMap<>();
        String messageData = "Topic2 Message, Hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend("topicExchange","topic.woman",map);
        return HttpResultUtil.success("发送成功!");
    }

    @RabbitHandler
    @RabbitListener(queues = "topic.man")
    public void process1(@Payload Map message){
        System.out.println("topic.man=>Topic消息者收到消息: "+ message.toString());
    }

    @RabbitHandler
    @RabbitListener(queues = "topic.woman")
    public void consumer1(@Payload Map message){
        System.out.println("topic.woman=>Topic消息者收到消息: "+ message.toString());
    }

    /**
     *  ================================================================================================================
     *  DirectRabbitConfig => 直连交换机
     */
    @PostMapping("/sendDirectMessage")
    public HttpResult<Object> sendDirectMessage(){
        // 将消息携带绑定键值: TestDirectRouting 发送到交换机TestDirectExchange
        Map<String,Object> map=new HashMap<>();
        String messageData = "Test Message Hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRouting",map);
        return HttpResultUtil.success("发送成功!");
    }

    @RabbitHandler
    @RabbitListener(queues = "TestDirectQueue")
    public void consumer2(@Payload Map message){
        System.out.println("第一个=>Direct消息者收到消息: "+ message.toString());
    }

    @RabbitHandler
    @RabbitListener(queues = "TestDirectQueue")
    public void process2(@Payload Map message){
        System.out.println("第二个=>Direct消息者收到消息: "+ message.toString());
    }
}
