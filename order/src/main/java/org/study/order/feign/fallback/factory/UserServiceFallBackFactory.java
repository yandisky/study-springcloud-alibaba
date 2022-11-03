package org.study.order.feign.fallback.factory;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.study.bean.bean.User;
import org.study.order.feign.UserService;

@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public User getUser(Long uid) {
                User user = new User();
                user.setId(-1L);
                return user;
            }
        };
    }
}
