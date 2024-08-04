package com.example.helm_postgres;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MyController {
    private final MyService service;
    @GetMapping("/users")
    public List<User> findAll(){
        return service.findAll();
    }
    @PostMapping("/add")
    public User save(@RequestBody User user){
        return service.save(user);
    }
}
