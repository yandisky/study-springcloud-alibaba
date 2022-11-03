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

    @Autowired
    @Qualifier(value = "orderServiceV3")
    private OrderService orderServiceV3;

    @Autowired
    @Qualifier(value = "orderServiceV4")
    private OrderService orderServiceV4;

    @Autowired
    @Qualifier(value = "orderServiceV5")
    private OrderService orderServiceV5;

    @Autowired
    @Qualifier(value = "orderServiceV6")
    private OrderService orderServiceV6;

    @GetMapping(value = "/submit_order_v1")
    public String submitOrderV1(OrderParams orderParams) {
        orderServiceV1.saveOrder(orderParams);
        return "success1";
    }

    @GetMapping(value = "/submit_order_v2")
    public String submitOrderV2(OrderParams orderParams) {
        orderServiceV2.saveOrder(orderParams);
        return "success2";
    }

    @GetMapping(value = "/submit_order_v3")
    public String submitOrderV3(OrderParams orderParams) {
        orderServiceV3.saveOrder(orderParams);
        return "success3";
    }

    @GetMapping(value = "/submit_order_v4")
    public String submitOrderV4(OrderParams orderParams) {
        orderServiceV4.saveOrder(orderParams);
        return "success4";
    }

    @GetMapping(value = "/submit_order_v5")
    public String submitOrderV5(OrderParams orderParams) {
        orderServiceV5.saveOrder(orderParams);
        return "success5";
    }

    @GetMapping(value = "/submit_order_v6")
    public String submitOrderV6(OrderParams orderParams) {
        orderServiceV6.saveOrder(orderParams);
        return "success6";
    }

    @GetMapping(value = "/test/request")
    public String testRequest() {
        return "test request";
    }

    @GetMapping(value = "/test/sentinel")
    public String testSentinel() {
        return "test sentinel";
    }
}
