package com.codewithmosh.store.user_registration;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository{

    private final Map<Long, User> dbUsersStore = new HashMap<>();

    public void save(User user){
        User savedUser = dbUsersStore.put(user.id(), user);
        System.out.println("saved user: " + savedUser);
//        return savedUser;
    }

    @Override
    public User findByEmail(String email) {
       return dbUsersStore.values()
                .stream()
                .filter(u -> u.email().equals(email))
                .findFirst().orElse(null);
    }
}
