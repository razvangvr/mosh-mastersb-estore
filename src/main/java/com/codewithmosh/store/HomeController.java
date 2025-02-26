package com.codewithmosh.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * - The Controller, Like an Air Traffic Controller,
 * it Handles the Traffic: incoming Requests, Outgoing Resp
 * */
@Controller
public class HomeController {


    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}
