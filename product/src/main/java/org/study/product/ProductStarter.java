package org.study.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "org.study.*")
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = {"org.study.product.mapper"})
@EnableDiscoveryClient
public class ProductStarter {
    public static void main(String[] args) {
        SpringApplication.run(ProductStarter.class, args);
    }
}
