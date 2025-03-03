package com.codewithmosh.store.notificaiton;

import org.springframework.stereotype.Service;

@Service
public class NotificationManager {

    private final NotificationService notificationService;

    public NotificationManager(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    public String sendNotification(String message) {

        notificationService.send(message);

        return "Notification Sent";
    }
}
