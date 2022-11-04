package org.study.user.rocketmq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.study.bean.bean.Order;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "user-group", topic = "order-topic")
public class RocketConsumeListener implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.info("user service consumer message:{}", JSONObject.toJSONString(order));
    }
}
