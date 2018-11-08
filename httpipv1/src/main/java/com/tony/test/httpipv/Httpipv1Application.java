package com.tony.test.httpipv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = { "com.tony.test.httpipv" })
public class Httpipv1Application {

    public static void main(String[] args) {
        SpringApplication.run(Httpipv1Application.class, args);
    }
}
