package com.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;

    static {
        // 创建连接工厂
        connectionFactory = new ConnectionFactory();
        // 设置mq的ip地址
        connectionFactory.setHost("192.168.31.210");
        // 设置端口号
        connectionFactory.setPort(5672);
        // 设置用户名密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
        // 设置连接的虚拟主机
        connectionFactory.setVirtualHost("/ems");
    }

    public static Connection getConnection() {
        try{
            // 获取连接对象
            Connection connection = connectionFactory.newConnection();
            return connection;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 关闭通道和连接
    public static void closeConnectionAndChannel(Channel channel,Connection connection) {
        try{
            if(channel!=null) {
                channel.close();
            }
            if(connection!=null) {
                connection.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
