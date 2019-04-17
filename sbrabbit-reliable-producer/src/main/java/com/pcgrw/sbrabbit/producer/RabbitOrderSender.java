package com.pcgrw.sbrabbit.producer;

import com.pcgrw.sbrabbit.constant.Constants;
import com.pcgrw.sbrabbit.dao.BrokerMessageLogMapper;
import com.pcgrw.sbrabbit.entity.BrokerMessageLog;
import com.pcgrw.sbrabbit.entity.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            String messageId = correlationData.getId();
            if (ack) {
                BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
                brokerMessageLog.setMessageId(messageId);
                brokerMessageLog.setStatus(Constants.ORDER_SEND_SUCCESS);
                brokerMessageLogMapper.updateByPrimaryKeySelective(brokerMessageLog);
            } else {
                System.err.println("异常处理......");
            }
        }
    };

    public void send(Order order) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        //消息唯一Id
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange", "order.ABC", order, correlationData);
    }
}
