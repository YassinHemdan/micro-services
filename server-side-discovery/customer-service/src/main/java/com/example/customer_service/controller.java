package com.example.customer_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class controller {
    private final RestClient restClient;

    public controller() {
        restClient = RestClient.builder()
                .baseUrl("http://order-service:80")
                .build();
    }

    @GetMapping("/get")
    public String customer(){
        return restClient.get()
                .uri("/get")
                .retrieve()
                .body(String.class);
    }
}
