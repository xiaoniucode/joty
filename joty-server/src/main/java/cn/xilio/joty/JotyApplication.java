package cn.xilio.joty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableAsync;
/**
 * @author xilio
 */
@EnableAsync
@SpringBootApplication
public class JotyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JotyApplication.class, args);
    }
}
