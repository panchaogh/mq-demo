package com.pcgrw.mqdemo.rabbitmq.springcallback;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component("publishUtils")
public class PublishUtils {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String exchange, String routingkey, Object message) {
        amqpTemplate.convertAndSend(exchange, routingkey, message);
    }
}
