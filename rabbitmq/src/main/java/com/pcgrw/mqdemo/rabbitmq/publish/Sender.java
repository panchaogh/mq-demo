package com.pcgrw.mqdemo.rabbitmq.publish;

import com.pcgrw.mqdemo.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Sender {
    public static final String EXCHANGE_NAME = "testexchange";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明交换机 定义一个交换机，类型是fanout，也就是发布订阅模式
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        for (int i = 0; i < 10; i++) {
            //发布订阅模式的，因为消息是先发送到交换机中，而交换机是没有保存功能的，所以如果没有消费者，消息会丢失
            channel.basicPublish(EXCHANGE_NAME, "", null, ("发布订阅模式的消息" + i).getBytes());
        }
        channel.close();
        connection.close();
    }
}
