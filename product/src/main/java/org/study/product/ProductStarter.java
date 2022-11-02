package org.study.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = {"org.study.product.mapper"})
public class ProductStarter {
    public static void main(String[] args) {
        SpringApplication.run(ProductStarter.class, args);
    }
}
