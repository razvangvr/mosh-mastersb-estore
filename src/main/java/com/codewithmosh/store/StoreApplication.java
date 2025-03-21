package com.codewithmosh.store;

import com.codewithmosh.store.entities.Profile;
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

        user.addTag("tag1");

        var profile = Profile.builder()
                .bio("my Beautiful Life")
                .build();

        user.setProfile(profile);
        profile.setUser(user);

        System.out.println(user);
    }

}
