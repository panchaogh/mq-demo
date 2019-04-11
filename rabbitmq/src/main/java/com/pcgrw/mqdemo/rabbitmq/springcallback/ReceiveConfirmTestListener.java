package com.pcgrw.mqdemo.rabbitmq.springcallback;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("receiveConfirmTestListener")
public class ReceiveConfirmTestListener implements ChannelAwareMessageListener {
    public static final Logger LOGGER = LoggerFactory.getLogger(ReceiveConfirmTestListener.class);

    /**
     * 收到消息的时候执行的监听
     *
     * @param message
     * @param channel
     * @throws Exception
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            LOGGER.info("消费者收到了消息：" + new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
