package com.codewithmosh.store;

import com.codewithmosh.store.entities.Address;
import com.codewithmosh.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(StoreApplication.class, args);

        var user = new User(1L, "John", "me@me.com", "password", null);
        /*var user = new User();*/
        user.setName("John");

        User user2 = User.builder()
                .name("John")
                .email("me@me.com")
                .password("password")
                .build();

        var address = Address.builder()
                .street("street")
                .city("city")
                .state("state")
                .zipCode("zip")
                .build();

/*        user2.getAddresses().add(address);//Now the User Knows about this Address Object
        //But the Address, doesn't know anything about the User
        //SO, we need to:
        address.setUser(user2);*/

        /**
         * Now, these 2 Lines are Highly related we should always Write them Together
         * So it's better to Encapsulate this logic inside the User Class
         * */
        user2.addAddress(address);

        System.out.println(user2);
    }

}
