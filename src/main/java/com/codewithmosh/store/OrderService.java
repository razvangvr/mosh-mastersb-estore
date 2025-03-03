package com.codewithmosh.store;


import com.codewithmosh.store.notificaiton.NotificationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//@Service
public class OrderService {

    private final NotificationManager notificationManager;
    private PaymentService paymentService;

    /**
     * If you have Multiple Constructors AutoWired is required
     * */
//    public OrderService(NotificationManager notificationManager) {
//        this.notificationManager = notificationManager;
//    }

    /**
     * In Older Versions of Spring, we had to tell Spring
     * To AutoWire the Objects with its dependencies.
     * But This is no longer necessary if our Class has a Single
     * Constructor
     */
//    @Autowired
    public OrderService(
           /* @Qualifier("paypal")*/ PaymentService paymentService,
            NotificationManager notificationManager) {
        this.paymentService = paymentService;

        this.notificationManager = notificationManager;
    }

    public void placeOrder() {

        paymentService.processPayment(10d);

        notificationManager.sendNotification("Order placed");

    }
}
