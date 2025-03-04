package com.codewithmosh.store.notificaiton;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmailNotificationService implements NotificationService {


    @Value("${mail.host}")
    private String host;

    @Override
    public void send(String message) {
        System.out.println("Notification Sent via Email");
    }

    @Override
    public void send(String message, String recipientEmail) {
        System.out.println("User-REGISTRATION Notification Sent via Email");
    }
}
