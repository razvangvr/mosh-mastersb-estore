package com.codewithmosh.store.notificaiton;

public interface NotificationService {

     void send(String message);


    void send(String message, String recipientEmail);
}
