package com.rabbitmq.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        // 获取通道对象
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",true,false,false,null);
        // 生产消息
        for (int i=0;i<10;i++) {
            channel.basicPublish("","work", null,(i+"hello work queue").getBytes());
        }
        // 关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
