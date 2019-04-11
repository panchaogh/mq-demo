package com.pcgrw.mqdemo.rabbitmq.springcallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

@Component("confirmCallbackListener")
public class ConfirmCallbackListener implements RabbitTemplate.ConfirmCallback {
    public static final Logger LOGGER = LoggerFactory.getLogger(ConfirmCallbackListener.class);
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        LOGGER.info("确认回调 ack=="+ack+"     cause=="+cause);
    }
}
