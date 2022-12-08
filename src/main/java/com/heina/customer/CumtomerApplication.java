package com.heina.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.heina.customer")
public class CumtomerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CumtomerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CumtomerApplication.class);
    }
}
