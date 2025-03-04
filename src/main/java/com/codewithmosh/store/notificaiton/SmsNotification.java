package com.codewithmosh.store.notificaiton;

import org.springframework.stereotype.Service;

@Service
public class SmsNotification implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Notification Sent via SMS");
    }

    @Override
    public void send(String message, String recipientEmail) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
