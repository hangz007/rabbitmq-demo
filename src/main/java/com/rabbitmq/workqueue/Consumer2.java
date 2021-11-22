package com.rabbitmq.workqueue;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

public class Consumer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",true,false,false,null);
        channel.basicConsume("work",true,new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2："+new String(body));
            }
        });
        
    }
}
