package com.pcgrw.mqdemo.rabbitmq.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyConsumer {
    public static final Logger LOGGER = LoggerFactory.getLogger(MyConsumer.class);

    public void test(String message) {
        LOGGER.info("接收到消息：" + message);
    }
}
