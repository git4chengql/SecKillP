/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ysusoft.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by qlcheng on 2017/7/20.
 */
public class MqConsumer {
    private static String url = "tcp://localhost:61616";
    private static String destnation = "test";

    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);

        //获取连接
        Connection connection = factory.createConnection();

        //启动连接
        connection.start();

        //创建回话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //创建目标
        Destination destination = session.createQueue(destnation);

        //创建一个消费者
        MessageConsumer messageConsumer = session.createConsumer(destination);

        //创建监听器
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("接收消息："+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //关闭连接
        //connection.close();
    }
}
