package org.study.order.feign.fallback;

import org.springframework.stereotype.Component;
import org.study.bean.bean.User;
import org.study.order.feign.UserService;

@Component
public class UserServiceFallBack implements UserService {
    @Override
    public User getUser(Long uid) {
        User user = new User();
        user.setId(-1L);
        return user;
    }
}
