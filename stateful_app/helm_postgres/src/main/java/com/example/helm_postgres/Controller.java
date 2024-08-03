package com.example.helm_postgres;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Controller {
    private final JdbcTemplate jdbcTemplate;
    @GetMapping("/get")
    public String get(){
        String query = """
                SELECT distinct(client_addr) FROM pg_stat_activity
                WHERE client_addr = inet_client_addr()
                       """
                ;
        return jdbcTemplate.queryForObject(query, String.class);
    }
}
