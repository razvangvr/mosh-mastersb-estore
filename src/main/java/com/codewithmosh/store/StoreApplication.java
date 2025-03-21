package com.codewithmosh.store;

import com.codewithmosh.store.entities.Address;
import com.codewithmosh.store.entities.Tag;
import com.codewithmosh.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(StoreApplication.class, args);

        var user = new User(1L, "John", "me@me.com", "password", null, null);
        /*var user = new User();*/
        user.setName("John");


        var aTag = new Tag("tag1");

        user.getTags().add(aTag);//User knows about the Tag
        aTag.getUsers().add(user);//Now we should tell Tag about the User
        /**
         * Once again, these 2 Lines are highly related, they go 'hand-in-hand'
         * So it's better to Encapsulate this Logic inside a Helper Method in the User Class
         * */

    }

}
