package com.example.helm_postgres;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MyService {
    private final MyRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User save(User user) {
        return repository.save(user);
    }
}
