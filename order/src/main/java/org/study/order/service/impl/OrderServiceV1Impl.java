package org.study.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.study.bean.bean.Order;
import org.study.bean.bean.OrderItem;
import org.study.bean.bean.Product;
import org.study.bean.bean.User;
import org.study.bean.dto.OrderParams;
import org.study.order.mapper.OrderItemMapper;
import org.study.order.mapper.OrderMapper;
import org.study.order.service.OrderService;
import org.study.utils.constants.HttpCode;
import org.study.utils.resp.Result;

import java.math.BigDecimal;

@Service("orderServiceV1")
@Slf4j
public class OrderServiceV1Impl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {
        if (orderParams.isEmpty()) {
            throw new RuntimeException("参数异常：" + JSONObject.toJSONString(orderParams));
        }
        User user = restTemplate.getForObject("http://localhost:8060/user/get/" + orderParams.getUserId(), User.class);
        if (user == null) {
            throw new RuntimeException("用户不存在：" + JSONObject.toJSONString(orderParams));
        }
        Product product = restTemplate.getForObject("http://localhost:8070/product/get/" + orderParams.getProductId(), Product.class);
        if (product == null) {
            throw new RuntimeException("商品不存在：" + JSONObject.toJSONString(orderParams));
        }
        if (product.getProStock() < orderParams.getCount()) {
            throw new RuntimeException("商品库存不足：" + JSONObject.toJSONString(orderParams));
        }
        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setTotalPrice(product.getProPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);

        Result<Integer> result = restTemplate.getForObject("http://localhost:8070/product/update_count/" + orderParams.getProductId() + "/" + orderParams.getCount(), Result.class);
        if (result.getCode() != HttpCode.SUCCESS) {
            throw new RuntimeException("库存扣减失败");
        }
    }
}
