package com.codewithmosh.store.notificaiton;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmailNotification implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Notification Sent via Email");
    }
}
