package com.codewithmosh.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * - The Controller, Like an Air Traffic Controller,
 * it Handles the Traffic: incoming Requests, Outgoing Resp
 * */
@Controller
public class HomeController {

    @Value("${spring.application.name}")
    private  String appName;

    @RequestMapping("/")
    public String index() {
        System.out.println(appName);
        return "index.html";
    }
}
