package com.pcgrw.mqdemo.rabbitmq.springcallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component("returnCallbackListener")
public class ReturnCallbackListener implements RabbitTemplate.ReturnCallback {
    public static final Logger LOGGER = LoggerFactory.getLogger(ReturnCallbackListener.class);

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        LOGGER.info("失败 message==" + new String(message.getBody()) + "     replyCode==" + replyCode + "     replyText==" + replyText + "     exchange==" + exchange + "     routingKey==" + routingKey);
    }
}
