package com.example.zettaonline.restapi.dbconnector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfiguration {
    @Bean
    public DataBaseConnectorInterface dataBaseConnector() {
        return new MockDataBaseConnector();
    }

}
