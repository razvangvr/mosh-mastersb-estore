package com.codewithmosh.store;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private  PaymentService paymentService;

    /**
     * If you have Multiple Constructors AutoWired is required
     * */
    public OrderService() {
    }

    /**
     * In Older Versions of Spring, we had to tell Spring
     * To AutoWire the Objects with its dependencies.
     * But This is no longer necessary if our Class has a Single
     * Constructor
     * */
    @Autowired
    public OrderService(@Qualifier("paypal") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {

        paymentService.processPayment(10d);

    }
}
