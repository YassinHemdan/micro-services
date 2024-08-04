package com.example.helm_postgres;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MyRepository extends JpaRepository<User, Integer> {
}
