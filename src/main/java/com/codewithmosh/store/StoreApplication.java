package com.codewithmosh.store;

import com.codewithmosh.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(StoreApplication.class, args);

        var user = new User(1L, "John", "me@me.com", "password");
        /*var user = new User();*/
        user.setName("John");

        User user2 = User.builder()
                .name("John")
                .email("me@me.com")
                .password("password")
                .build();
    }

}
