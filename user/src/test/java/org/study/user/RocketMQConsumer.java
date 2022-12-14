package org.study.user;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class RocketMQConsumer {
    public static void main(String[] args) throws Exception {
        try {
            //创建消息消费者
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("studyConsumerGroup");
            //设置NameServer地址
            consumer.setNamesrvAddr("127.0.0.1:9876");
            //订阅studyTopic主题
            consumer.subscribe("studyTopic", "*");
            //设置消息监听，当收到消息时RocketMQ会回调消息监听
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    //打印消息消费者收到的RocketMQ消息
                    System.out.println("消费者收到的消息为：" + list);
                    //返回消息消费成功的标识
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            //启动消费者
            consumer.start();
            System.out.println("消费者启动成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
