package com.tony.test.httpipv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Httpipv2Application {

    public static void main(String[] args) {
        SpringApplication.run(Httpipv2Application.class, args);
    }
}
