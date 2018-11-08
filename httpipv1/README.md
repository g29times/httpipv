
httpipv 父项目
httpipv1 子项目1 - 客户端
httpipv2 子项目2 - 服务端

web正确跳转需要
1 依赖 spring-boot-starter-thymeleaf
2 welcome.html
3 @RestController不跳转页面， @Controller跳转页面
4 spring.thymeleaf.prefix=classpath:/templates/ https://blog.csdn.net/rocling/article/details/81676928