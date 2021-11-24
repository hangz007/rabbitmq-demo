package com.rabbitmq.workqueue;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

public class Consumer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1); // 每一次只能消费一个消息
        channel.queueDeclare("work",true,false,false,null);
        // 参数1：队列名称 参数2：消息自动确认，true消费者自动向RabbitMQ确认消息消费 false不会自动确认消费
        channel.basicConsume("work",false,new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者-1："+new String(body));
                // 参数1：确认的是队列中哪个具体的消息 参数2：是否开启多消息确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }

}
