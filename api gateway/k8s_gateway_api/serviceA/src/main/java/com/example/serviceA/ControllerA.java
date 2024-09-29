package com.example.serviceA;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerA {
    @GetMapping("/get")
    public String get(){
        return "service A";
    }
}
