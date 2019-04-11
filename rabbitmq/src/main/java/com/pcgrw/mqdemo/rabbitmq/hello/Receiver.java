package com.pcgrw.mqdemo.rabbitmq.hello;

import com.pcgrw.mqdemo.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Receiver {
    public static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    public static final String QUEUE = "testhello";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        /*声明队列，如果队列存在，什么都不做；如果队列不存在，则创建队列
        参数一：队列名称
        参数二：是否持久化队列，我们的队列模式是在内存中的，如果rabbitmq重启队列中的数据会丢失，如果我们设置为true,则会将数据保存到erlang自带的数据库中，重气后会重新读取
        参数三：是否排外，有两个作用，作用一 当我们连接关闭后是否会自动删除队列，作用二 是否私有当前队列，如果私有了，其他通道不可以访问当前队列，如果为true，一般是一个队列只适用于一个消费者的时候
        参数四：是否自动删除
        参数五：我们的一些其他参数
         */
        channel.queueDeclare(QUEUE, false, false, false, null);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //接收消息，参数2是 自动确认
        channel.basicConsume(QUEUE, true, consumer);
        while (true) {
            Thread.sleep(3000);
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(message);
            LOGGER.info("logger:"+message);
        }
    }
}
