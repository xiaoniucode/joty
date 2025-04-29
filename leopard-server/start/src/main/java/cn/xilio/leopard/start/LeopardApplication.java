package cn.xilio.leopard.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableJpaRepositories("cn.xilio.leopard.*.infrastructure.database") // 指定Repository包路径
@EntityScan("cn.xilio.leopard.*.domain.*.model") // 指定实体类包路径
@SpringBootApplication(scanBasePackages = {"cn.xilio.leopard"})
public class LeopardApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeopardApplication.class, args);
    }
}
