package com.example.wxpush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WxPushApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxPushApplication.class, args);
    }

}
