package com.codewithmosh.store;


import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService, PaymentService paymentService1) {
        this.paymentService = paymentService1;
    }

    public void placeOrder() {

        paymentService.processPayment(10d);

    }
}
