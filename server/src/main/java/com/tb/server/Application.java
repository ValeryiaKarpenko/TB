package com.tb.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages = {"com.tb.model"})
@ComponentScan({"com.tb.dal.impl", "com.tb.service.impl", "com.tb.server"})
public class Application  {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
