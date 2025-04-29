package cn.xilio.leopard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableAsync;
@EnableAsync
@SpringBootApplication 
public class LeopardApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeopardApplication.class, args);
    }
}
