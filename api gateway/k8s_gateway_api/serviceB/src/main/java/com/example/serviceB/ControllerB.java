package com.example.serviceB;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerB {
    @GetMapping("/get")
    public String get(){
        return "service B";
    }
}
