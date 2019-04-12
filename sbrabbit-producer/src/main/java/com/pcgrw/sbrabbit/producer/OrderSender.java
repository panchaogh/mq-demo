package com.pcgrw.sbrabbit.producer;

import com.pcgrw.sbrabbit.entity.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send(Order order){
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange",//exchange
                "order.abcd",//routingKey
                order,//消息体内容
                correlationData);//correlationData 消息唯一Id
    }
}
