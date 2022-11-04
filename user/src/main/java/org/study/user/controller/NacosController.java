package org.study.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
public class NacosController {
    @Autowired
    private ConfigurableApplicationContext context;
    @Value("${test-nacos.name}")
    private String nacosTestName;

    @GetMapping("/nacos/test")
    public String nacosTest() {
        return context.getEnvironment().getProperty("test-nacos.name");
    }

    @GetMapping("/nacos/name")
    public String nacosName() {
        return nacosTestName;
    }
}
