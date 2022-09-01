package com.yequ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-08-30
 **/
@SpringBootApplication
@EnableSwagger2
public class CommonApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CommonApp.class);
    }
}
