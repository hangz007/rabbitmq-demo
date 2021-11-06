package com.rabbitmq.helloworld;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
//        // 创建连接工厂
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        // 设置mq的ip地址
//        connectionFactory.setHost("192.168.31.210");
//        // 设置端口号
//        connectionFactory.setPort(5672);
//        // 设置用户名密码
//        connectionFactory.setUsername("ems");
//        connectionFactory.setPassword("123");
//        // 设置连接的虚拟主机
//        connectionFactory.setVirtualHost("/ems");
//        // 获取连接对象
//        Connection connection = connectionFactory.newConnection();
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 通道去绑定对应的消息队列
        // 参数1：队列名称，如果队列不存在则自动创建
        // 参数2：用来定义队列是否要持久化，true，持久化队列，false，不持久化
        // 参数3：是否独占队列，true当前连接独占队列，false不独占队列
        // 参数4： 是否在消费完成后自动删除队列，true自动删除，false不自动删除
        // 参数5： 额外参数，附加参数
        channel.queueDeclare("hello",false,false,false,null);
        // 消费消息
        // 参数1 消费哪个队列的消息，即消息队列的名称
        // 参数2 开启消息的自动确认机制
        // 参数3 消费时的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel) {
            @Override // 最后一个参数，消息队列中的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("new String(body) = "+new String(body));
            }
        });
       // channel.close();
       // connection.close();
    }

}
