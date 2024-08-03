package com.example.order_service;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class controller {
    @GetMapping("/get")
    public String get() throws UnknownHostException {
        System.out.println("Hi There :)");
        InetAddress localHost = InetAddress.getLocalHost();
        return localHost.getHostAddress();
    }
}
