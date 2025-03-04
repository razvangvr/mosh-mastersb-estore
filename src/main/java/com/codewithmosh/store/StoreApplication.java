package com.codewithmosh.store;

import com.codewithmosh.store.user_registration.User;
import com.codewithmosh.store.user_registration.UserService;
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

        var heavyResource = applicationContext.getBean(HeavyResource.class);

        UserService userService = applicationContext.getBean(UserService.class);

        userService.registerUser(new User(1L, "Razvan Gaston", "123", "raz@me.com"));
        userService.registerUser(new User(1L, "Razvan Gaston", "123", "raz@me.com"));
    }

}
