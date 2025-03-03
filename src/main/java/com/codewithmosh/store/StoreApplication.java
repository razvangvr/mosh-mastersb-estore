package com.codewithmosh.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(StoreApplication.class, args);

        OrderService orderService = applicationContext.getBean(OrderService.class);

        orderService.placeOrder();
    }

}
