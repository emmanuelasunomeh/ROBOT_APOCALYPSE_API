package com.api.robotapocalypse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RobotApocalypseApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotApocalypseApplication.class, args);
        System.out.println("------APPLICATION STARTUP COMPLETE-------");
    }

}
