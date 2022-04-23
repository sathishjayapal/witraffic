package me.sathish.witrafficconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class WitrafficConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WitrafficConfigServerApplication.class, args);
    }

}
