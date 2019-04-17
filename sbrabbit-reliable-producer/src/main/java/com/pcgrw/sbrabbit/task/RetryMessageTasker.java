package com.pcgrw.sbrabbit.task;

import com.alibaba.fastjson.JSON;
import com.pcgrw.sbrabbit.constant.Constants;
import com.pcgrw.sbrabbit.dao.BrokerMessageLogMapper;
import com.pcgrw.sbrabbit.entity.BrokerMessageLog;
import com.pcgrw.sbrabbit.entity.Order;
import com.pcgrw.sbrabbit.producer.RabbitOrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetryMessageTasker {
    @Autowired
    private RabbitOrderSender rabbitOrderSender;
    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Scheduled(initialDelay = 3000, fixedDelay = 10000)
    public void reSend() {
        System.err.println("---定时任务开始---");
        List<BrokerMessageLog> brokerMessageLogs = brokerMessageLogMapper.selectInitStatusAndTimeoutMessage();
        brokerMessageLogs.forEach(brokerMessageLog -> {
            if (brokerMessageLog.getTryCount() >= 3) {
                //更新状态为失败
                BrokerMessageLog brokerMessageLog1 = new BrokerMessageLog();
                brokerMessageLog1.setMessageId(brokerMessageLog.getMessageId());
                brokerMessageLog1.setStatus(Constants.ORDER_SEND_FAILURE);
                brokerMessageLogMapper.updateByPrimaryKeySelective(brokerMessageLog1);
            } else {
                //重新发送
                brokerMessageLogMapper.updateTryCountByMessageId(brokerMessageLog.getMessageId());
                try {
                    rabbitOrderSender.send(JSON.parseObject(brokerMessageLog.getMessage(), Order.class));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("---异常处理---");
                }
            }
        });
        System.err.println("---定时任务结束---");
    }
}
