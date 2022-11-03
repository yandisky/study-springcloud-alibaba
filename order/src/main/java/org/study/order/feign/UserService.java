package org.study.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.study.bean.bean.User;
import org.study.order.feign.fallback.UserServiceFallBack;
import org.study.order.feign.fallback.factory.UserServiceFallBackFactory;

//@FeignClient(value = "server-user", fallback = UserServiceFallBack.class)
@FeignClient(value = "server-user", fallbackFactory = UserServiceFallBackFactory.class)
public interface UserService {
    @GetMapping(value = "/user/get/{uid}")
    User getUser(@PathVariable("uid") Long uid);
}
