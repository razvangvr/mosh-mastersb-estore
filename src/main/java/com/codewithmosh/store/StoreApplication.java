package com.codewithmosh.store;

import com.codewithmosh.store.entities.Profile;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(StoreApplication.class, args);
        var userService = applicationContext.getBean(UserService.class);

//        userService.showRelatedEntities();
//        userService.fetchAddresses();

        userService.persistRelated();
    }

    private static void playWithUser() {
        var user = basicUser();

        user.addTag("tag1");

        var profile = Profile.builder()
                .bio("my Beautiful Life")
                .build();

        user.setProfile(profile);
        profile.setUser(user);

        System.out.println(user);
    }

    public static User basicUser() {
        var user = User.builder()
                .name("John")
                .email("me@me.com")
                .password("password")
                .build();
        return user;
    }

}
