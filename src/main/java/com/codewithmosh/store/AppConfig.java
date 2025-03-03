package com.codewithmosh.store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration - this tells Spring that it is a Source of Beans Definitions
 * */
@Configuration
public class AppConfig {

    //call this method 'stripe', that is the name that will be assigned to our bean
    @Bean /* This Annotation Tells Spring this is a Bean Producer*/
    public PaymentService stripe () {
        return new StripePaymentService();
    }


}
