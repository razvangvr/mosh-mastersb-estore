package com.codewithmosh.store;


public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService, PaymentService paymentService1) {
        this.paymentService = paymentService1;
    }

    public void placeOrder() {

        paymentService.processPayment(10d);

    }
}
