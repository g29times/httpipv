package com.tony.test.httpipv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("hello1");
        return "hello1";
    }

    @Autowired
    private FeignForNextService service2;

    @RequestMapping("/helloNext")
    public String helloNext() {
        System.out.println("---hello1 calls hello2---");
        // 控制器或服务层方法内调用
        return service2.hello();
    }


}
