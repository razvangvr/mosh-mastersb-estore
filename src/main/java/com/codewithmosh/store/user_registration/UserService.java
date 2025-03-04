package com.codewithmosh.store.user_registration;

import com.codewithmosh.store.notificaiton.NotificationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void registerUser(User user) {


        if(userRepository.findByEmail(user.email()) !=null){
            throw new IllegalArgumentException("Email already in use");
        }

        userRepository.save(user);

        String msg = "User Was Registered";

        notificationService.send(msg, user.email());
    }
}
