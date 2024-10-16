package myaong.popolog.psservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsServiceApplication.class, args);
    }

}
