package com.iss.springcloudconfigclient.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Value("${my.message}")
    private String message;

    @GetMapping("/message")
    public String getMessage() {
        return message;
    }
}
