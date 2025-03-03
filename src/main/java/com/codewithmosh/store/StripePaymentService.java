package com.codewithmosh.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentService implements PaymentService {


    @Value("${stripe.api.enabled}")
    private boolean enabled;

    @Override
    public void processPayment(double amount) {
        System.out.println("Amount:"+amount);
        System.out.println("STRIPE");
    }
}
