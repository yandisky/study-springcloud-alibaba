package org.study.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.bean.bean.Order;
import org.study.bean.bean.OrderItem;
import org.study.bean.bean.Product;
import org.study.bean.bean.User;
import org.study.bean.dto.OrderParams;
import org.study.order.feign.ProductService;
import org.study.order.feign.UserService;
import org.study.order.mapper.OrderItemMapper;
import org.study.order.mapper.OrderMapper;
import org.study.order.service.OrderService;
import org.study.utils.constants.HttpCode;
import org.study.utils.resp.Result;

import java.math.BigDecimal;

/**
 * fallback容错机制
 */
@Service("orderServiceV6")
@Slf4j
public class OrderServiceV6Impl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {
        if (orderParams.isEmpty()) {
            throw new RuntimeException("参数异常：" + JSONObject.toJSONString(orderParams));
        }
        User user = userService.getUser(orderParams.getUserId());
        if (user == null) {
            throw new RuntimeException("用户不存在：" + JSONObject.toJSONString(orderParams));
        }
        if (user.getId() == -1) {
            throw new RuntimeException("fallback：" + JSONObject.toJSONString(orderParams));
        }
        Product product = productService.getProduct(orderParams.getProductId());
        if (product == null) {
            throw new RuntimeException("商品不存在：" + JSONObject.toJSONString(orderParams));
        }
        if (product.getId() == -1) {
            throw new RuntimeException("fallback：" + JSONObject.toJSONString(orderParams));
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

        Result<Integer> result = productService.updateCount(orderParams.getProductId(), orderParams.getCount());
        if (result.getCode() == 1001) {
            throw new RuntimeException("fallback：" + JSONObject.toJSONString(orderParams) + JSONObject.toJSONString(result));
        }
        if (result.getCode() != HttpCode.SUCCESS) {
            throw new RuntimeException("库存扣减失败");
        }
    }
}
