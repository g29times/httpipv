package com.tony.test.httpipv;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "httpipv2", url = "http://localhost:8082")
public interface FeignForNextService {

    @RequestMapping(value = "/hello")
    String hello();

}
