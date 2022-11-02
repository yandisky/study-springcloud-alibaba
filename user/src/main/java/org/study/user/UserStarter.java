package org.study.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = {"org.study.user.mapper"})
public class UserStarter {
    public static void main(String[] args) {
        SpringApplication.run(UserStarter.class, args);
    }
}
