package com.codewithmosh.store;

import com.codewithmosh.store.notificaiton.NotificationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration - this tells Spring that it is a Source of Beans Definitions
 */
@Configuration
public class AppConfig {


    @Value("${payment-gateway-default}")
    private String defaultPaymentGateway;

    //call this method 'stripe', that is the name that will be assigned to our bean
    @Bean /* This Annotation Tells Spring this is a Bean Producer*/
    public PaymentService stripe() {
        return new StripePaymentService();
    }

    @Bean
    public OrderService orderService(
            @Qualifier("paypal") PaymentService paypalService,
            NotificationManager notificationManager) {

        if ("stripe".equals(defaultPaymentGateway)) {
            return new OrderService(stripe(), notificationManager);
        }
        return new OrderService(paypalService, notificationManager);
    }


}
