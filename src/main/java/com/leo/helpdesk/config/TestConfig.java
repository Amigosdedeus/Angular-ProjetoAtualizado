package com.leo.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.leo.helpdesk.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
    
    @Autowired
    private DBService dbservice;

    @Bean
    public Object instanciaDB() {
        this.dbservice.instanciaDB();
        return null;
    }
}