package com.example.order.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class OrderController {
    @GetMapping("/get")
    public String get() throws UnknownHostException {
        System.out.println("hi");
        InetAddress localHost = InetAddress.getLocalHost();
        return localHost.getHostAddress();
    }
}
