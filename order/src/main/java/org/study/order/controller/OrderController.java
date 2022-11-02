package org.study.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.bean.dto.OrderParams;
import org.study.order.service.OrderService;

@Slf4j
@RestController
public class OrderController {
    @Autowired
    @Qualifier(value = "orderServiceV1")
    private OrderService orderServiceV1;

    @Autowired
    @Qualifier(value = "orderServiceV2")
    private OrderService orderServiceV2;

    @GetMapping(value = "/submit_order_v1")
    public String submitOrderV1(OrderParams orderParams) {
        orderServiceV1.saveOrder(orderParams);
        return "success";
    }

    @GetMapping(value = "/submit_order_v2")
    public String submitOrderV2(OrderParams orderParams) {
        orderServiceV2.saveOrder(orderParams);
        return "success";
    }
}
