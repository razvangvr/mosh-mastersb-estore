package com.codewithmosh.store;

import com.codewithmosh.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(StoreApplication.class, args);

        var user = User.builder()
                .name("John")
                .email("me@me.com")
                .password("password")
                .build();
        /*var user = new User();*/
        user.setName("John");

        user.addTag("tag1");

        System.out.println(user);
    }

}
